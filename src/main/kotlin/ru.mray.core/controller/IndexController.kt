package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
open class IndexController {
    @RequestMapping
    fun index(model: Model): String {
        return "redirect:/buy"
    }
}