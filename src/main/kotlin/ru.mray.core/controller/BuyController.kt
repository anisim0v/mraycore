package ru.mray.core.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository
import java.time.Instant
import java.time.Period
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/buy")
class BuyController(val accountRepository: AccountRepository,
                    val transactionRepository: TransactionRepository) {

    val logger: Logger = LoggerFactory.getLogger(BuyController::class.java)

    @RequestMapping
    fun getPage(): String {
        return "buy/buy"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processForm(@RequestParam email: String,
                    @RequestParam region: Account.Region,
                    @RequestParam period: Int,
                    httpServletResponse: HttpServletResponse,
                    model: Model): String {

        if (accountRepository.findByEmail(email) != null) {
            httpServletResponse.status = 400
            model.addAttribute("message", "Этот email уже связан с другим акканутом")
            return "buy/error" // TODO: Create error page
        }

        val account = Account(email, region, period)
        accountRepository.save(account)
        logger.info("New account: Email: $email. Region: $region. Period: $period")

        val bonusTransaction = Transaction(account.id, Period.ofDays(1), Transaction.TransactionType.BONUS)
        bonusTransaction.paidAt = Instant.now()
        logger.info("New transaction: Type: ${bonusTransaction.type}. Account: ${bonusTransaction.accountId}. " +
                "Period: ${bonusTransaction.period}. ID: ${bonusTransaction.id}")

        val paymentTransaction = Transaction(account.id, Period.ofMonths(period), Transaction.TransactionType.PAYMENT)
        logger.info("New transaction: Type: ${paymentTransaction.type}. Account: ${paymentTransaction.accountId}. " +
                "Period: ${paymentTransaction.period}. ID: ${paymentTransaction.id}")

        transactionRepository.save(listOf(paymentTransaction, bonusTransaction))

        model.addAttribute("email", email)
        model.addAttribute("transactionId", paymentTransaction.id)
        return "buy/done"
    }
}