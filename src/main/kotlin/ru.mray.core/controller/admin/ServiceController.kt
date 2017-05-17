package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import ru.mray.core.repository.AccountRepository
import ru.mray.core.service.NotificationsService

@Controller
@RequestMapping("/admin/service")
open class ServiceController(val notificationsService: NotificationsService,
                             val accountRepository: AccountRepository) {
    @RequestMapping("/sendRenewNotifications")
    @ResponseBody
    fun sendRenewNotifications(): String {
        val notifiedAccounts = notificationsService.sendNotifications()

        val accountsString = notifiedAccounts.map { it.email }.joinToString("<br>")

        return "Notifications sent to ${notifiedAccounts.count()} accounts:<br>$accountsString"
    }

    @RequestMapping("/resetRenewNotifications")
    @ResponseBody
    fun resetRenewNotifications(): String {
        val resetedAccounts = accountRepository.resetNotificationDate()
        val accountsString = resetedAccounts.map { it.email }.joinToString("<br>")
        return "Reset complete for ${resetedAccounts.count()} accounts:<br>$accountsString"
    }
}