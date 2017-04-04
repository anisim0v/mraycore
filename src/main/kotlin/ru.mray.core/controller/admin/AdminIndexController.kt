package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
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
        val pendingCount = transactionRepository.countAccountInactivePaidTransactions()
        val unassignedTokens = familyTokenRepository.countByAccountIsNull()

        model.addAttribute("accountsCount", accountsCount)
        model.addAttribute("pendingCount", pendingCount)
        model.addAttribute("unassignedTokens", unassignedTokens)

        return "admin/index"
    }
}