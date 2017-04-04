package ru.mray.core.service

import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver
import java.util.*

@Service
class MailService(val viewResolver: FreeMarkerViewResolver) {
    fun renderTemplate(templateName: String, model: Model): String? {
        val view = viewResolver.resolveViewName(templateName, Locale.forLanguageTag("RU"))
        val mockHttpServletResponse = MockHttpServletResponse()
        val mockHttpServletRequest = MockHttpServletRequest()
        view.render(model.asMap(), mockHttpServletRequest, mockHttpServletResponse)

        val result = mockHttpServletResponse.contentAsString
        return result
    }
}