package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/donate")
class DonateController {
    @RequestMapping
    fun donatepage(): String {
        return "donate"
    }
}