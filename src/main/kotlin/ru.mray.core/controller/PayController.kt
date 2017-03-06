package ru.mray.core.controller;

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import ru.mray.core.component.PricesHolder
import ru.mray.core.exceptions.BadRequestException
import ru.mray.core.model.Transaction
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.service.W1Service
import ru.mray.core.util.describe
import java.net.URLEncoder
import java.time.Instant
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/pay")
class PayController(val w1Service: W1Service,
                    val pricesHolder: PricesHolder,
                    val transactionRepository: TransactionRepository) {

    val logger = LoggerFactory.getLogger(PayController::class.java)

    @RequestMapping("/{transaction}")
    fun createForm(@PathVariable transaction: Transaction,
                   model: Model): String {

        val formFields = mapOf(
                "WMI_MERCHANT_ID" to "141130336213", // TODO: Move to config
                "WMI_CURRENCY_ID" to "643",
                "WMI_PAYMENT_NO" to transaction.id,
                "WMI_PAYMENT_AMOUNT" to pricesHolder.getFormattedPrice(transaction.region, transaction.period),
                "WMI_DESCRIPTION" to "Оплата Spotify Premium на ${transaction.period.describe()}",
                "WMI_SUCCESS_URL" to "http://music-ray.ru/pay/done/${transaction.id}",
                "WMI_FAIL_URL" to "http://music-ray.ru/pay/fail/${transaction.id}"
        )

        val signature = w1Service.sign(formFields)
        model.addAllAttributes(formFields)
        model.addAttribute("WMI_SIGNATURE", signature)

        return "pay/form"
    }

    @ResponseBody
    @RequestMapping("/confirm")
    fun confirm(request: HttpServletRequest): String {
        val signature = request.getParameter("WMI_SIGNATURE") ?: throw BadRequestException("No WMI_SIGNATURE field in request")
        val params = request.parameterNames
                .toList()
                .filter { it != "WMI_SIGNATURE" }
                .map { it to request.getParameter(it) }
                .toMap()

        val actualSignature = w1Service.sign(params)
        if (signature != actualSignature) {
            logger.warn("WMI_SIGNATURE check failed: Actual: $actualSignature. Declared: $signature")
            throw BadRequestException("WMI_SIGNATURE validation failed")
        }

        val transactionId = request.getParameter("WMI_PAYMENT_NO")
                ?: throw BadRequestException("No WMI_PAYMENT_NO field in request")

        val transaction = transactionRepository.findOne(transactionId) ?: let {
            logger.warn("Transaction not found activated: $transactionId")
            val respStr = URLEncoder.encode("WMI_RESULT=RETRY&WMI_DESCRIPTION=Unknown transaction id", "UTF-8")
            return respStr
        }

        if (transaction.paidAt != null) {
            logger.warn("Transaction already activated: ${transaction.id}")
            return "WMI_RESULT=OK"
        }

        transaction.paidAt = Instant.now()
        transactionRepository.save(transaction)

        logger.info("Transaction activated: ${transaction.id}")

        return "WMI_RESULT=OK"
    }
}