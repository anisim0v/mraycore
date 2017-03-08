package ru.mray.core.service

import org.springframework.stereotype.Service
import ru.mray.core.model.Account
import ru.mray.core.repository.TransactionRepository
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

@Service
class TransactionService(val transactionRepository: TransactionRepository) {
    fun refreshAccountTransactions(account: Account) {
        if (!account.provisioned) {
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
}