package ru.mray.core.controller.admin

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/admin")
class AdminIndexController {
    @RequestMapping
    fun index(@AuthenticationPrincipal principal: UserDetails): String {
        return "admin/index"
    }
}