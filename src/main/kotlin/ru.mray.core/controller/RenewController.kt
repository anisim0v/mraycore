package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.model.Account

@Controller
@RequestMapping("/renew")
class RenewController {
    @RequestMapping("/{account}")
    fun renewPage(@PathVariable account: Account, model: Model): String {
        model.addAttribute("account", account)
        return "renew/index"
    }
}