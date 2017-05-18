package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.repository.AccountRepository
import ru.mray.core.service.ConfigService
import ru.mray.core.service.NotificationsService

@Controller
@RequestMapping("/admin/service")
open class ServiceController(val notificationsService: NotificationsService,
                             val accountRepository: AccountRepository,
                             val configService: ConfigService) {
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
        val resetAccounts = accountRepository.resetNotificationDate()
        val accountsString = resetAccounts.map { it.email }.joinToString("<br>")
        return "Reset complete for ${resetAccounts.count()} accounts:<br>$accountsString"
    }

    @RequestMapping("/config/toggle/{name}")
    fun switchConfigParam(@PathVariable name: String): String {
        when (name) {
            "registrationEnabled" -> configService.registrationEnabled = !configService.registrationEnabled
            else -> NotFoundException("Unknown param")
        }
        return "redirect:/admin"
    }


}