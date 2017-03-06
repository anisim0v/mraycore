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
        var description :String
        var price = pricesHolder.getPrice(transaction.region, transaction.period)
        when (price){
            80.0 -> description = "Оплата Spotify Premium на 1 месяц"
            240.0 -> description = "Оплата Spotify Premium на n месяцев"
            else -> description = "Оплата Spotify Premium"
        }
        val formFields = mapOf(
                "transactionId" to transaction.id,
                "price" to price, //TODO: price with two symbols after dot
                "description" to description,
                "key" to "value"
        )

        val signature = w1Service.sign(formFields)
        model.addAllAttributes(formFields)
        model.addAttribute("signature", signature)

        return "pay/form"
    }
}