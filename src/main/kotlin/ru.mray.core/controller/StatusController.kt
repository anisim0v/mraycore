package ru.mray.core.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.exceptions.BadRequestException
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository
import java.time.Instant
import java.time.OffsetDateTime
import java.time.Period
import java.time.temporal.ChronoUnit

@Controller
@RequestMapping("/status")
class StatusController(val transactionRepository: TransactionRepository,
                       val accountRepository: AccountRepository) {

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
    fun statusPage(@PathVariable account: Account, model: Model, @AuthenticationPrincipal authUser: Account?): String {
        val paidTransaction = transactionRepository.findLatestPaid(account)

        val unpaidTransactionValidSince = listOf(paidTransaction?.issueDate, OffsetDateTime.now().minusDays(1).toInstant())
                .filterNotNull()
                .max()!!

        val unpaidTransaction = transactionRepository.findLatestUnpaid(account, unpaidTransactionValidSince)

        val showRenewForm = when {
            unpaidTransaction != null -> false
            paidTransaction?.paidAt != null && paidTransaction.activeUntil == null -> false
            paidTransaction?.activeUntil != null
                    && paidTransaction.activeUntil!! > OffsetDateTime.now().plusDays(10).toInstant() -> false
            else -> true
        }

        val queueSize = accountRepository.findPending(account.region)
                .takeWhile { it.id != account.id }
                .count()

        model.addAttribute("account", account)
        model.addAttribute("family", account.familyToken?.family)
        model.addAttribute("unpaidTransaction", unpaidTransaction)
        model.addAttribute("paidTransaction", paidTransaction)
        model.addAttribute("showRenewForm", showRenewForm)
        model.addAttribute("queueSize", queueSize)
        model.addAttribute("isAdmin", authUser?.admin ?: false)

        logger.info("Serving /status/${account.id} (${account.email})")

        return "status/status"
    }

    @RequestMapping("/{account}/renew", method = arrayOf(RequestMethod.POST))
    fun renew(@PathVariable account: Account,
              @RequestParam renewPeriod: Int,
              @AuthenticationPrincipal authUser: Account?): String {

        if(renewPeriod == 0 && authUser?.admin != true) {
            throw BadRequestException("Invalid renew period")
        }

        val transaction = Transaction(account, account.region, Period.ofMonths(renewPeriod),
                Transaction.TransactionType.PAYMENT)
        transactionRepository.save(transaction)
        return "redirect:/pay/${transaction.id}"
    }
}