package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import ru.mray.core.service.NotificationsService

@Controller
@RequestMapping("/admin/service")
open class ServiceController(val notificationsService: NotificationsService) {
    @RequestMapping("/sendRenewNotifications")
    @ResponseBody
    fun sendRenewNotifications(): String {
        val notifiedAccounts = notificationsService.sendNotifications()
        return "Notifications sent to ${notifiedAccounts.count()} accounts"
    }
}