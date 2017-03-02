package ru.mray.core.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/buy")
class BuyController {

    val logger = LoggerFactory.getLogger(BuyController::class.java)

    @RequestMapping
    fun getPage(): String {
        return "buy"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processForm() {
        logger.info("Processing form...")
//        TODO: Get user input and print it to log
    }
}