package ru.mray.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository


@Controller
@RequestMapping("/pay")
class PayController(val accountRepository: AccountRepository,
                    val transactionRepository: TransactionRepository) {
    @RequestMapping("/{transactionId}")
    fun createForm(@PathVariable transactionId: String): String {
        return "pay/form"
    }
}