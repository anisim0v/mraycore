package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.mray.core.model.Transaction
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.service.TransactionService


@Controller
@RequestMapping("/admin/transactions")
class TransactionsController(val transactionRepository: TransactionRepository,
                             val transactionService: TransactionService) {
    @RequestMapping("/{transaction}/remove")
    fun remove(@PathVariable transaction: Transaction): String {
        transactionRepository.delete(transaction)
        transactionService.refreshAccountTransactions(transaction.accountId)
        return "redirect:/admin/accounts/${transaction.accountId}"
    }
}