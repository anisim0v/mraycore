package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
open class IndexController {
    @RequestMapping
    fun index(model: Model): String {
        model.addAttribute("name", "world")
        return "index"
    }

    @RequestMapping("/{name}")
    fun name(@PathVariable name: String, model: Model): String {
        model.addAttribute("name", name)
        return "index"
    }
}