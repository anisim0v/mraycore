package ru.mray.core.service

import org.springframework.stereotype.Service
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

@Service
class TransactionService(private val transactionRepository: TransactionRepository,
                         private val accountRepository: AccountRepository) {
    /**
     * Calculates Transaction.activeUntil instants for all paid, but not activated yet account transactions
     *
     * This method should be called every time account.provisioned flag changes or transaction becomes paid
     */
    fun refreshAccountTransactions(account: Account) {
        if (account.familyToken == null) {
            return
        }

        val inactivePaidTransactions = transactionRepository.findAccountInactivePaidTransactions(account.id)
                .sortedBy { it.paidAt }

        var latestActiveAccountTransaction = transactionRepository.findLatestActiveAccountTransaction(account.id)

        inactivePaidTransactions.forEach {
            val activationStartTime = listOf(latestActiveAccountTransaction?.activeUntil, Instant.now())
                    .filter { it != null }
                    .maxBy { it!! }!!

            val activeUntil = OffsetDateTime.ofInstant(activationStartTime, ZoneId.of("UTC"))
                    .plus(it.period)
                    .toInstant()

            it.activatedAt = Instant.now()
            it.activeUntil = activeUntil
            it.previousTransactionId = latestActiveAccountTransaction?.id

            transactionRepository.save(it)
            latestActiveAccountTransaction = it
        }
    }

    fun refreshAccountTransactions(accountId: String) {
        val account = accountRepository.findOne(accountId)
                ?: throw NoSuchElementException("Failed to refresh account transactions: can't find " +
                "account with requested user id")
        refreshAccountTransactions(account)
    }
}