package ru.mray.core.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.repository.mongo.MongoTransactionRepository

@Controller
@RequestMapping("/renew")
class RenewController(val transactionRepository: MongoTransactionRepository) {
    @RequestMapping("/{account}")
    fun renewPage(@PathVariable account: String): String {
        return "redirect:/status/$account"
    }
}