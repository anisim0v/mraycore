package ru.mray.core.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.mongo.MongoAccountRepository
import ru.mray.core.repository.mongo.TransactionRepository
import java.time.OffsetDateTime
import java.time.Period

@Controller
@RequestMapping("/status")
class StatusController(val transactionRepository: TransactionRepository,
                       val accountRepository: MongoAccountRepository) {

    val logger: Logger = LoggerFactory.getLogger(StatusController::class.java)


    @RequestMapping
    fun indexPage(): String {
        return "status/index"
    }

    @RequestMapping("/byEmail")
    fun byEmail(email: String): String {
        val account = accountRepository.findByEmailIgnoreCase(email)
                ?: throw NotFoundException("В нашей базе нет пользователя с таким email")
        return "redirect:/status/${account.id}"
    }

    @RequestMapping("/{account}")
    fun statusPage(@PathVariable account: Account, model: Model): String {
        val latestTransaction = transactionRepository.findFirstByAccountId(account.id)
        val showRenewForm = latestTransaction == null ||
                !(latestTransaction.activeUntil == null
                        || latestTransaction.activeUntil!! > OffsetDateTime.now().plusDays(10).toInstant())

        val queueSize = accountRepository.findPending(account.region)
                .takeWhile { it.id != account.id }
                .count()

        model.addAttribute("account", account)
        model.addAttribute("transaction", latestTransaction)
        model.addAttribute("showRenewForm", showRenewForm)
        model.addAttribute("queueSize", queueSize)

        logger.info("Serving /status/${account.id} (${account.email})")

        return "status/status"
    }

    @RequestMapping("/{account}/renew", method = arrayOf(RequestMethod.POST))
    fun renew(@PathVariable account: Account,
              @RequestParam renewPeriod: Int): String {
        val transaction = Transaction(account.id, account.region, Period.ofMonths(renewPeriod),
                Transaction.TransactionType.PAYMENT)
        transactionRepository.save(transaction)
        return "redirect:/pay/${transaction.id}"
    }
}