package ru.mray.core.controller

import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.ExtendedModelMap
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.service.MailService
import java.time.Period
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/join")
class JoinController(val accountRepository: AccountRepository,
                     val transactionRepository: TransactionRepository,
                     val passwordEncoder: PasswordEncoder,
                     val mailService: MailService,
                     environment: Environment) {

    val logger: Logger = LoggerFactory.getLogger(JoinController::class.java)

    val registrationEnabled: Boolean = environment.getProperty("mray.registration")?.toBoolean() ?: false

    @RequestMapping
    fun getPage(): String {
        return when(registrationEnabled) {
            true -> "join/join"
            false -> "join/disabled"
        }
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processForm(@RequestParam email: String,
                    @RequestParam region: Account.Region,
                    @RequestParam period: Int,
                    httpServletResponse: HttpServletResponse,
                    model: Model): String {

        if (accountRepository.findByEmailIgnoreCase(email) != null) {
            httpServletResponse.status = 400
            model.addAttribute("message", "Этот email уже связан с другим акканутом")
            return "error"
        }

        val account = Account(email, region, period)
        val password = RandomStringUtils.random(8, true, true)
        account._password = passwordEncoder.encode(password)
        accountRepository.save(account)
        logger.info("New account: ID: ${account.id} Email: $email. Region: $region. Period: $period")

        val transaction = Transaction(account, account.region, Period.ofMonths(period), Transaction.TransactionType.PAYMENT)

        transactionRepository.save(transaction)
        logger.info("New transaction: Type: ${transaction.type}. Account: ${transaction.account.id}. " +
                "Period: ${transaction.period}. Region: ${transaction.region}. ID: ${transaction.id}")

        val mailModel = ExtendedModelMap()
        mailModel.addAttribute("account", account)
        mailService.sendMail(account, "Welcome to Music Ray", "mail/welcome", mailModel)

        model.addAttribute("email", email)
        model.addAttribute("transactionId", transaction.id)
        return "join/done"
    }
}