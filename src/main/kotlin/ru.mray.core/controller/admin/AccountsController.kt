package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.FamilyToken
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.service.FamilyTokenService
import ru.mray.core.service.TransactionService
import java.time.Instant
import java.time.OffsetDateTime
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

    @RequestMapping("/pending/{region}")
    fun pendingAccounts(@PathVariable region: Account.Region, model: Model): String {
        val accounts = accountRepository.findPending(region)
        model.addAttribute("title", "Pending accounts")
        model.addAttribute("accounts", accounts)

        return "admin/accountList"
    }

    @RequestMapping("/active")
    fun activeAccounts(model: Model): String {
        val accounts = accountRepository.findByFamilyTokenIsNotNull()
        model.addAttribute("title", "Active accounts")
        model.addAttribute("accounts", accounts)

        return "admin/accountList"
    }

    @RequestMapping("/expiring/{days}")
    fun expiringAccounts(model: Model, @PathVariable days: Long): String {
        val accounts = accountRepository.findExpiring(OffsetDateTime.now().plusDays(days).toInstant())
        model.addAttribute("title", "Expiring in $days days accounts")
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

    @RequestMapping("/{account}/assignToken/manual")
    fun assignTokenManuallyPage(@PathVariable account: Account, model: Model): String {
        model.addAttribute("account", account)
        return "admin/accountAssignToken"
    }

    @RequestMapping("/{account}/assignToken/manual", method = arrayOf(RequestMethod.POST))
    fun assignTokenManually(@PathVariable account: Account, @RequestParam token: FamilyToken): String {
        familyTokenService.assignTokenToAccount(account, token)
        return "redirect:/admin/accounts/${account.id}"
    }

    @RequestMapping("/assignTokens/{region}", method = arrayOf(RequestMethod.POST))
    fun assignTokens(@PathVariable region: Account.Region, @RequestParam tokenCountToAssign: Int): String {
        familyTokenService.assignTokens(region, tokenCountToAssign)
        return "redirect:/admin"
    }

    @RequestMapping("/{account}/unlink")
    fun unlinkPage(@PathVariable account: Account, model: Model): String {
        val familyToken = account.familyToken!!
        val family = familyToken.family
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

        val transaction = Transaction(account, account.region, period, type)

        if (paid) {
            transaction.paidAt = Instant.now()
        }

        transactionRepository.save(transaction)

        transactionService.refreshAccountTransactions(account, startInstant, forceRefresh)

        return "redirect:/admin/accounts/${account.id}"
    }
}