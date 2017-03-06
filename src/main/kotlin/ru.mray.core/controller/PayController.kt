package ru.mray.core.controller;

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.component.PricesHolder
import ru.mray.core.model.Transaction
import ru.mray.core.service.W1Service


@Controller
@RequestMapping("/pay")
class PayController(val w1Service: W1Service, val pricesHolder: PricesHolder) {
    @RequestMapping("/{transaction}")
    fun createForm(@PathVariable transaction: Transaction,
                   model: Model): String {

        val price = pricesHolder.getPrice(transaction.region, transaction.period)

        val formFields = mapOf(
                "transactionId" to transaction.id,
                "price" to price, //TODO: price with two symbols after dot
                "key" to "value"
        )

        val signature = w1Service.sign(formFields)
        model.addAllAttributes(formFields)
        model.addAttribute("signature", signature)

        return "pay/form"
    }
}