package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/admin")
class AdminIndexController {
    @RequestMapping
    @ResponseBody
    fun index(): String {
        return "Hello admin"
    }
}