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
    fun renewPage(@PathVariable account: String): String {
        return "redirect:/status/$account"
    }
}