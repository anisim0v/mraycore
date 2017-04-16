package ru.mray.core.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/faq")
class FaqController {
    val logger: Logger = LoggerFactory.getLogger(FaqController::class.java)

    @RequestMapping
    fun faqPage(): String {
        logger.info("Serving /faq")
        return "faq"
    }
}