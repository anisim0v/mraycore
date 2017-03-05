package ru.mray.core.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Region
import ru.mray.core.repository.AccountRepository

@Controller
@RequestMapping("/buy")
class BuyController(val accountRepository: AccountRepository) {

    val logger = LoggerFactory.getLogger(BuyController::class.java)

    @RequestMapping
    fun getPage(): String {
        return "buy/buy"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processForm(@RequestParam email: String,
                    @RequestParam region: Region,
                    @RequestParam period: Int,
                    model: Model): String {

        val account = Account(email, region, period)
        accountRepository.save(account)

        logger.info("New user: Email: $email. Region: $region. Period: $period")
        model.addAttribute("message", "Готово! Мы отправили письмо со ссылкой на оплату на $email")
        return "buy/done"
    }
}