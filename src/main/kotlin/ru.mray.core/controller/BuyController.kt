package ru.mray.core.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/buy")
class BuyController {

    val logger = LoggerFactory.getLogger(BuyController::class.java)

    @RequestMapping
    fun getPage(): String {
        return "buy/buy"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processForm(@RequestParam email: String,
                    @RequestParam country: String,
                    @RequestParam period: String,
                    model: Model): String {
        logger.info("New user: Email: $email. Сountry: $country. Period: $period")
        model.addAttribute("message", "Готово! Мы отправили письмо со ссылкой на оплату на $email")
        return "buy/done"
    }
}