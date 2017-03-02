package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/buy")
class BuyController {
    @RequestMapping
    fun buy(): String {
        return "buy"
    }
}