package ru.mray.core.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/buy")
class BuyController {

    val logger = LoggerFactory.getLogger(BuyController::class.java)

    @RequestMapping
    fun getPage(): String {
        return "buy"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processForm(@RequestParam email: String,
                    @RequestParam country: String,
                    @RequestParam period: String,
                    redirectAttrs: RedirectAttributes): String {
        logger.info("Processing form... Email: $email. Сountry: $country. Period: $period")
        redirectAttrs.addAttribute("email", email)
        return "redirect:/"
    }
}