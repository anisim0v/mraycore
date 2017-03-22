package ru.mray.core.controller.admin

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.controller.PayController
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository


@Controller
@RequestMapping("admin/users")
class UsersPageController(val accountRepository: AccountRepository) {
    val logger: Logger = LoggerFactory.getLogger(PayController::class.java)
    @RequestMapping
    fun generateTable(model: Model): String {
        val users = accountRepository.findAll()
        model.addAllAttributes(users)

        return "admin/users"
    }
}