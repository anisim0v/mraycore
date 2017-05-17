package ru.mray.core.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.controller.admin.AdminIndexController
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.repository.mongo.MongoAccountRepository
import ru.mray.core.repository.mongo.MongoFamilyTokenRepository
import ru.mray.core.repository.mongo.MongoTransactionRepository

@Controller
@RequestMapping("/stats")
class StatsController(val accountRepository: AccountRepository,
                      val transactionRepository: TransactionRepository,
                      val familyTokenRepository: FamilyTokenRepository) {
    val logger: Logger = LoggerFactory.getLogger(StatsController::class.java)

    @RequestMapping
    fun statsPage(model: Model): String {
        val activeCount = accountRepository.countByFamilyTokenIsNotNull()
        val totalCount = accountRepository.count()

        val regionsStats = Account.Region.values().map { region ->
            val pendingCount = accountRepository.countPending(region)
            val unassignedTokensCount = familyTokenRepository.countUnassigned(region)
            val tokenCountToAssign = arrayOf(pendingCount, unassignedTokensCount).min()!!

            AdminIndexController.RegionStats(region, pendingCount, unassignedTokensCount, tokenCountToAssign)
        }

        logger.info("Serving /stats")

        model.addAttribute("totalCount", totalCount)
        model.addAttribute("activeCount", activeCount)
        model.addAttribute("regionsStats", regionsStats)
        return "stats"
    }
}