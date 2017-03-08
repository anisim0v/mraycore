package ru.mray.core.service

import org.springframework.stereotype.Service
import ru.mray.core.model.Account
import ru.mray.core.repository.TransactionRepository

@Service
class TransactionService(val transactionRepository: TransactionRepository) {
    fun refreshAccountTransactions(account: Account) {
        if (!account.provisioned) {
            return
        }

        val inactivePaidTransactions = transactionRepository.findAccountInactivePaidTransactions(account.id)
                .sortedBy { it.paidAt }

    }
}