package ru.mray.core.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.ui.ExtendedModelMap
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.mongo.MongoAccountRepository
import java.time.Instant
import java.time.OffsetDateTime

@Service class NotificationsService(val accountRepository: AccountRepository,
                                    val mailService: MailService,
                                    val environment: Environment) {

    final val logger: Logger = LoggerFactory.getLogger(NotificationsService::class.java)
    val notificationsEnabled: Boolean = environment.getProperty("mray.notifications", Boolean::class.java, false)

    init {
        logger.warn("Renew notifications are disabled")
    }

    @Scheduled(fixedDelay = 10 * 1000)
    fun sendNotifications(): List<Account> {
        if (!notificationsEnabled) {
            return listOf()
        }
        val accountsToNotify = accountRepository.findAccountsToNotify()

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