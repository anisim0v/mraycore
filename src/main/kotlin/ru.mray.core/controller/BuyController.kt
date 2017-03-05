package ru.mray.core.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Region
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository
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
                    @RequestParam region: Region,
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
        logger.info("New user: Email: $email. Region: $region. Period: $period")

        val transaction = Transaction(account.id, Period.ofMonths(period))
        transactionRepository.save(transaction)
        logger.info("New transaction: Account: ${transaction.accountId}. Period: ${transaction.period}. ID: ${transaction.id}")

        model.addAttribute("email", email)
        model.addAttribute("transactionId", transaction.id)
        return "buy/done"
    }
}