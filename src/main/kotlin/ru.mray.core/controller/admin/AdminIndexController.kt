package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.TransactionRepository
import java.time.Instant
import java.time.OffsetDateTime

@Controller
@RequestMapping("/admin")
class AdminIndexController(val accountRepository: AccountRepository,
                           val transactionRepository: TransactionRepository,
                           val familyTokenRepository: FamilyTokenRepository) {
    @RequestMapping
    fun index(model: Model): String {
        val accountsCount = accountRepository.count()
        val activeCount = accountRepository.countByFamilyTokenIsNotNull()
        val accountsToNotifyCount = accountRepository.countAccountsToNotify()

        val expireIn10Days = accountRepository.countExpiring(OffsetDateTime.now().plusDays(10).toInstant())
        val expireIn3Days = accountRepository.countExpiring(OffsetDateTime.now().plusDays(3).toInstant())
        val expireIn1Day = accountRepository.countExpiring(OffsetDateTime.now().plusDays(1).toInstant())
        val expiredCount = accountRepository.countExpired()

        val regionsStats = Account.Region.values().map { region ->
            val pendingCount = accountRepository.countPending(region)
            val unassignedTokensCount = familyTokenRepository.countUnassigned(region)
            val tokenCountToAssign = arrayOf(pendingCount, unassignedTokensCount).min()!!

            RegionStats(region, pendingCount, unassignedTokensCount, tokenCountToAssign)
        }

        model.addAttribute("accountsCount", accountsCount)
        model.addAttribute("activeCount", activeCount)
        model.addAttribute("accountsToNotifyCount", accountsToNotifyCount)
        model.addAttribute("regionsStats", regionsStats)

        model.addAttribute("expireIn10Days", expireIn10Days)
        model.addAttribute("expireIn3Days", expireIn3Days)
        model.addAttribute("expireIn1Day", expireIn1Day)
        model.addAttribute("expiredCount", expiredCount)

        return "admin/index"
    }

    data class RegionStats(val region: Account.Region, val pendingCount: Int,
                           val unassignedTokenCount: Int, val tokenCountToAssign: Int)
}