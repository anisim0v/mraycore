package ru.mray.core.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.ui.ExtendedModelMap
import ru.mray.core.model.Account
import ru.mray.core.repository.mongo.AccountRepository
import java.time.Instant
import java.time.OffsetDateTime

@Service
open class NotificationsService(val accountRepository: AccountRepository, val mailService: MailService) {

    val logger: Logger = LoggerFactory.getLogger(NotificationsService::class.java)

    fun sendNotifications(): List<Account> {
        val now = OffsetDateTime.now()
        val accountsToNotify = accountRepository.findAccountsToNotify(now.plusDays(3).toInstant())

        accountsToNotify.forEach { account ->
            logger.info("Sending renew notification to ${account.email} (ID: ${account.id})")
            val model = ExtendedModelMap()
            model.addAttribute("account", account)
            mailService.sendMail(account, "MusicRay: Оповещение о продлении", "mail/renew", model)

            account.renewNotificationSentAt = Instant.now()
            accountRepository.save(account)
        }

        return accountsToNotify
    }
}