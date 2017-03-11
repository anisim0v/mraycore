package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/login")
class LoginController {
    @RequestMapping
    fun login(): String {
        return "login"
    }
}