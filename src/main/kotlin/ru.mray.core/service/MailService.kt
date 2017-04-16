package ru.mray.core.service

import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.retry.backoff.ExponentialBackOffPolicy
import org.springframework.retry.policy.TimeoutRetryPolicy
import org.springframework.retry.support.RetryTemplate
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver
import ru.mray.core.model.Account
import java.util.*

@Service
open class MailService(private val viewResolver: FreeMarkerViewResolver,
                  private val environment: Environment) {

    private var apiKey: String? = environment.getProperty("mray.mailhandler-key")

    val logger = LoggerFactory.getLogger(MailService::class.java)
    val restTemplate = RestTemplate()
    private val retryTemplate = RetryTemplate().let {
        it.setBackOffPolicy(ExponentialBackOffPolicy())
        it.setRetryPolicy(
                TimeoutRetryPolicy().let { it.timeout = 30000L; it }
        )
        return@let it
    }

    private val taskExecutor = ThreadPoolTaskExecutor().let {
        it.initialize()
        it
    }

    init {
        if (apiKey == null) {
            logger.warn("Email service is disabled due to mray.mailhandler-key has not been provided")
        }
    }


    open fun renderTemplate(templateName: String, model: Model): String {
        val view = viewResolver.resolveViewName(templateName, Locale.forLanguageTag("RU"))
        val mockHttpServletResponse = MockHttpServletResponse()
        val mockHttpServletRequest = MockHttpServletRequest()
        view.render(model.asMap(), mockHttpServletRequest, mockHttpServletResponse)

        val result = mockHttpServletResponse.contentAsString
        return result
    }

    open fun sendMail(account: Account, subject: String, templateName: String, model: Model) {

         if (apiKey == null) {
            logger.warn("Email service is disabled due to mray.mailhandler-key has not been provided")
            return
        }

        val renderedTemplate = renderTemplate(templateName, model)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.set("X-Secure-Token", apiKey)

        val body = mapOf(
                "from" to "mail@music-ray.ru",
                "to" to listOf(account.email),
                "subject" to subject,
                "html_body" to renderedTemplate
        )

        val request = HttpEntity<Map<String, Any>>(body, headers)

        taskExecutor.execute {
            retryTemplate.execute<Unit, Exception> {
                logger.info("Sending email to ${account.email}: $subject")
                restTemplate.postForEntity("https://api.mailhandler.ru/message/send/", request,
                        String::class.java, emptyMap<String, String>())
                logger.info("Email sent to ${account.email}: $subject")
            }
        }
    }
}