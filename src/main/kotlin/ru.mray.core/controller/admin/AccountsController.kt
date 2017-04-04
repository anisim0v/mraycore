package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.service.TransactionService


@Controller
@RequestMapping("/admin/accounts")
class AccountsController(val accountRepository: AccountRepository,
                         val transactionRepository: TransactionRepository,
                         val transactionService: TransactionService) {
    @RequestMapping
    fun users(model: Model): String {
        val accounts = accountRepository.findAll()
        model.addAttribute("title", "All accounts")
        model.addAttribute("accounts", accounts)

        return "admin/accountList"
    }

    @RequestMapping("/pending")
    fun pendingAccounts(model: Model): String {
        val accounts = accountRepository.findByFamilyTokenIsNull()
        model.addAttribute("title", "Pending accounts")
        model.addAttribute("accounts", accounts)

        return "admin/accountList"
    }

    @RequestMapping("/{account}")
    fun accountDetails(@PathVariable account: Account, model: Model): String {
        val accountTransactions = transactionRepository.findByAccountId(account.id)
        model.addAttribute("account", account)
        model.addAttribute("transactions", accountTransactions)
        return "admin/accountDetails"
    }

    @RequestMapping("/{account}/refresh")
    fun refreshAccountTransactions(@PathVariable account: Account): String {
        transactionService.refreshAccountTransactions(account)
        return "redirect:/admin/accounts/${account.id}"
    }

}