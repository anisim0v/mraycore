package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository

@Controller
@RequestMapping("/")
open class IndexController(val accountRepository: AccountRepository,
                           val transactionRepository: TransactionRepository) {
    @RequestMapping
    fun index(): String {
        return "index"
    }
}