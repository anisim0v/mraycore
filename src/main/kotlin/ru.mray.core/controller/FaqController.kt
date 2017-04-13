package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/faq")
class FaqController {
    @RequestMapping
    fun faqpage(): String {
        return "faq"
    }
}