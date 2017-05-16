package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.model.Transaction
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.repository.mongo.MongoTransactionRepository
import ru.mray.core.service.TransactionService


@Controller
@RequestMapping("/admin/transactions")
class TransactionsController(val transactionRepository: TransactionRepository,
                             val transactionService: TransactionService) {
    @RequestMapping("/{transaction}/remove")
    fun remove(@PathVariable transaction: Transaction): String {
        transactionRepository.delete(transaction)
        transactionService.refreshAccountTransactions(transaction.account.id)
        return "redirect:/admin/accounts/${transaction.account.id}"
    }

    @RequestMapping("/{transaction}/deactivate")
    fun deactivate(@PathVariable transaction: Transaction): String {
        transaction.activeSince = null
        transaction.activeUntil = null
        transactionRepository.save(transaction)
        transactionService.refreshAccountTransactions(transaction.account.id)
        return "redirect:/admin/accounts/${transaction.account.id}"
    }
}