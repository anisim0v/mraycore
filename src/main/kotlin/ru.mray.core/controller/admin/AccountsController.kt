package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.service.FamilyTokenService
import ru.mray.core.service.TransactionService
import java.time.Instant
import java.time.Period


@Controller
@RequestMapping("/admin/accounts")
class AccountsController(val accountRepository: AccountRepository,
                         val transactionRepository: TransactionRepository,
                         val familyTokenRepository: FamilyTokenRepository,
                         val familyRepository: FamilyRepository,
                         val transactionService: TransactionService,
                         val familyTokenService: FamilyTokenService) {
    @RequestMapping
    fun users(model: Model): String {
        val accounts = accountRepository.findAll()
        model.addAttribute("title", "All accounts")
        model.addAttribute("accounts", accounts)

        return "admin/accountList"
    }

    @RequestMapping("/pending")
    fun pendingAccounts(model: Model): String {
        val accounts = accountRepository.findPending()
        model.addAttribute("title", "Pending accounts")
        model.addAttribute("accounts", accounts)

        return "admin/accountList"
    }

    @RequestMapping("/expired")
    fun expiredAccounts(model: Model): String {
        val accounts = accountRepository.findExpired()
        model.addAttribute("title", "Expired accounts")
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
    fun refreshAccountTransactions(@PathVariable account: Account, @RequestParam(defaultValue = "false") force: Boolean): String {
        transactionService.refreshAccountTransactions(account, force = force)
        return "redirect:/admin/accounts/${account.id}"
    }

    @RequestMapping("/{account}/assignToken")
    fun assignToken(@PathVariable account: Account): String {
        familyTokenService.assignTokenToAccount(account)
        return "redirect:/admin/accounts/${account.id}"
    }

    @RequestMapping("/assignTokens", method = arrayOf(RequestMethod.POST))
    fun assignTokens(@RequestParam tokenCountToAssign: Int): String {
        familyTokenService.assignTokens(tokenCountToAssign)
        return "redirect:/admin"
    }

    @RequestMapping("/{account}/unlink")
    fun unlinkPage(@PathVariable account: Account, model: Model): String {
        val familyToken = familyTokenRepository.findOne(account.familyToken)!!
        val family = familyRepository.findOne(familyToken.family)!!
        model.addAttribute("account", account)
        model.addAttribute("token", familyToken)
        model.addAttribute("family", family)
        return "admin/accountUnlink"
    }

    @RequestMapping("/{account}/unlink", method = arrayOf(RequestMethod.POST))
    fun unlink(@PathVariable account: Account, @RequestParam newToken: String): String {
        familyTokenService.unlinkAccount(account, newToken)
        return "redirect:/admin/accounts/${account.id}"
    }

    @RequestMapping("/{account}/emailInvite")
    fun emailInvite(@PathVariable account: Account): String {
        familyTokenService.emailInvite(account)
        return "redirect:/admin/accounts/${account.id}"
    }

    @RequestMapping("/{account}/createTransaction")
    fun createTransactionPage(@PathVariable account: Account, model: Model): String {
        val defaultStartTime = Instant.now().toString()

        model.addAttribute("account", account)
        model.addAttribute("defaultStartTime", defaultStartTime)
        return "admin/transactionCreate"
    }

    @RequestMapping("/{account}/createTransaction", method = arrayOf(RequestMethod.POST))
    fun createTransaction(@PathVariable account: Account,
                          @RequestParam type: Transaction.TransactionType,
                          @RequestParam period: Period,
                          @RequestParam(required = false) startInstant: Instant?,
                          @RequestParam(value = "paid", defaultValue = "off") paidFlag: String,
                          @RequestParam(value = "forceRefresh", defaultValue = "off") forceRefreshFlag: String): String {
        val paid = when (paidFlag) {
            "on" -> true
            "off" -> false
            else -> throw IllegalArgumentException("Incorrect paid value")
        }
        val forceRefresh = when (forceRefreshFlag) {
            "on" -> true
            "off" -> false
            else -> throw IllegalArgumentException("Incorrect paid value")
        }

        val transaction = Transaction(account.id, account.region, period, type)

        if (paid) {
            transaction.paidAt = Instant.now()
        }

        transactionRepository.save(transaction)

        transactionService.refreshAccountTransactions(account, startInstant, forceRefresh)

        return "redirect:/admin/accounts/${account.id}"
    }
}