package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.TransactionRepository

@Controller
@RequestMapping("/admin")
class AdminIndexController(val accountRepository: AccountRepository,
                           val transactionRepository: TransactionRepository,
                           val familyTokenRepository: FamilyTokenRepository) {
    @RequestMapping
    fun index(model: Model): String {
        val accountsCount = accountRepository.count()
        val activeCount = accountRepository.countByFamilyTokenIsNotNull()
        val expiredCount = accountRepository.countExpired()
        val accountsToNotifyCount = accountRepository.countAccountsToNotify()

        val pendingAccounts = accountRepository.findPending()

        val regionsStats = Account.Region.values().map { region ->
            val pendingCount = pendingAccounts.filter { it.region == region }.count()
            val unassignedTokensCount = familyTokenRepository.countUnassigned(region)
            val tokenCountToAssign = arrayOf(pendingCount, unassignedTokensCount).min()!!

            RegionStats(region, pendingCount, unassignedTokensCount, tokenCountToAssign)
        }

        model.addAttribute("accountsCount", accountsCount)
        model.addAttribute("activeCount", activeCount)
        model.addAttribute("expiredCount", expiredCount)
        model.addAttribute("accountsToNotifyCount", accountsToNotifyCount)
        model.addAttribute("regionsStats", regionsStats)

        return "admin/index"
    }

    data class RegionStats(val region: Account.Region, val pendingCount: Int,
                           val unassignedTokenCount: Int, val tokenCountToAssign: Int)
}