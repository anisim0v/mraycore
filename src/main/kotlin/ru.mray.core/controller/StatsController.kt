package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.controller.admin.AdminIndexController
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.TransactionRepository

@Controller
@RequestMapping("/stats")
class StatsController(val accountRepository: AccountRepository,
                      val transactionRepository: TransactionRepository,
                      val familyTokenRepository: FamilyTokenRepository) {
    @RequestMapping
    fun statsPage(model: Model): String {
        val activeCount = accountRepository.countByFamilyTokenIsNotNull()
        val totalCount = accountRepository.count()

        val pendingAccounts = accountRepository.findPending()

        val regionsStats = Account.Region.values().map { region ->
            val pendingCount = pendingAccounts.filter { it.region == region }.count()
            val unassignedTokensCount = familyTokenRepository.countUnassigned(region)
            val tokenCountToAssign = arrayOf(pendingCount, unassignedTokensCount).min()!!

            AdminIndexController.RegionStats(region, pendingCount, unassignedTokensCount, tokenCountToAssign)
        }

        model.addAttribute("totalCount", totalCount)
        model.addAttribute("activeCount", activeCount)
        model.addAttribute("regionsStats", regionsStats)
        return "stats"
    }
}