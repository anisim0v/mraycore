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
     * @param interpretAsNewSubscription Если True и текущая подписка уже закончилась, то считать текущий момент началом
     * действия новых транзакций. False - использовать время окончания предыдущей транзакции в качестве начала для новой
     */
    fun refreshAccountTransactions(account: Account, interpretAsNewSubscription: Boolean = false) {
        if (account.familyToken == null) {
            return
        }

        val inactivePaidTransactions = transactionRepository.findAccountInactivePaidTransactions(account.id)
                .sortedBy { it.paidAt }

        var latestActiveAccountTransaction = transactionRepository.findLatestActiveAccountTransaction(account.id)


        inactivePaidTransactions.forEach {
            var activationStartTime = latestActiveAccountTransaction?.activeUntil ?: Instant.now()

            if (interpretAsNewSubscription) {
                activationStartTime = listOf(activationStartTime, Instant.now())
                        .max()
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
        accountRepository.save(account)
    }

    fun refreshAccountTransactions(accountId: String) {
        val account = accountRepository.findOne(accountId)
                ?: throw NoSuchElementException("Failed to refresh account transactions: can't find " +
                "account with requested user id")
        refreshAccountTransactions(account)
    }
}