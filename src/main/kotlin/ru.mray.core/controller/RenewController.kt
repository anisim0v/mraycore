package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.TransactionRepository
import java.time.Period

@Controller
@RequestMapping("/renew")
class RenewController(val transactionRepository: TransactionRepository) {
    @RequestMapping("/{account}")
    fun renewPage(@PathVariable account: Account, model: Model): String {
        model.addAttribute("account", account)
        return "renew/index"
    }

    @RequestMapping("/{account}", method = arrayOf(RequestMethod.POST))
    fun renew(@PathVariable account: Account,
              @RequestParam renewPeriod: Int): String {
        val transaction = Transaction(account.id, account.region, Period.ofMonths(renewPeriod),
                Transaction.TransactionType.PAYMENT)
        transactionRepository.save(transaction)
        return "redirect:/pay/${transaction.id}"
    }
}