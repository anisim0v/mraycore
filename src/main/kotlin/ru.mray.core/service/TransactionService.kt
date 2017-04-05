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
     * Считает Transaction.activeUntil для всех оплаченных, но еще не активированных транзакций. Этот метод
     * должен вызываться каждый раз при подтверждении оплаты транзакции / выдаче FamilyToken'а
     *
     * @param newTransactionsStartInstant Момент начала отсчета для новых транзакций в случае, если последняя
     * активированная уже истекла. Иначе (или в случае == null) началом отсчета будет момент истечения предыдущей
     * транзакции.
     */
    fun refreshAccountTransactions(account: Account, newTransactionsStartInstant: Instant? = null) {
        if (account.familyToken == null) {
            return
        }

        val inactivePaidTransactions = transactionRepository.findAccountInactivePaidTransactions(account.id)
                .sortedBy { it.paidAt }

        var latestActiveAccountTransaction = transactionRepository.findLatestActiveAccountTransaction(account.id)

        inactivePaidTransactions.forEach {
            var activationStartTime = latestActiveAccountTransaction?.activeUntil ?: Instant.now()

            if (newTransactionsStartInstant != null) {
                activationStartTime = listOf(activationStartTime, newTransactionsStartInstant).max()
            }

            val lastTransactionActiveUntil = OffsetDateTime.ofInstant(activationStartTime, ZoneId.of("UTC"))
                    .plus(it.period)
                    .toInstant()

            it.activeSince = activationStartTime
            it.activeUntil = lastTransactionActiveUntil
            it.previousTransactionId = latestActiveAccountTransaction?.id

            transactionRepository.save(it)
            latestActiveAccountTransaction = it
        }

        account.activeUntil = latestActiveAccountTransaction?.activeUntil ?: account.activeUntil
        account.renewNotificationSentAt = null
        accountRepository.save(account)
    }

    fun refreshAccountTransactions(accountId: String) {
        val account = accountRepository.findOne(accountId)
                ?: throw NoSuchElementException("Failed to refresh account transactions: can't find " +
                "account with requested user id")
        refreshAccountTransactions(account)
    }
}