package ru.mray.core.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.mray.core.model.Account
import ru.mray.core.repository.mongo.MongoAccountRepository
import ru.mray.core.repository.mongo.MongoTransactionRepository
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

@Service
class TransactionService(private val transactionRepository: MongoTransactionRepository,
                         private val accountRepository: MongoAccountRepository) {

    val logger: Logger = LoggerFactory.getLogger(TransactionService::class.java)

    /**
     * Считает Transaction.activeUntil для всех оплаченных, но еще не активированных транзакций. Этот метод
     * должен вызываться каждый раз при подтверждении оплаты транзакции / выдаче FamilyToken'а
     *
     * @param newTransactionsStartInstant Момент начала отсчета для новых транзакций в случае, если последняя
     * активированная уже истекла. Иначе (или в случае == null) началом отсчета будет момент истечения предыдущей
     * транзакции.
     */
    fun refreshAccountTransactions(account: Account, newTransactionsStartInstant: Instant? = null, force: Boolean = false) {
        var latestActiveAccountTransaction = transactionRepository.findLatestActiveAccountTransaction(account.id)

        logger.info("Refreshing transactions for account ${account.id}. Force: $force. Start instant: $newTransactionsStartInstant")

        if (account.familyToken == null && !force) {
            account.activeUntil = latestActiveAccountTransaction?.activeUntil
            accountRepository.save(account)
            return
        }

        val inactivePaidTransactions = transactionRepository.findAccountInactivePaidTransactions(account.id)
                .sortedBy { it.paidAt }

        var newTransactionsStartInstantApplied = false

        inactivePaidTransactions.forEach {
            var activationStartTime = latestActiveAccountTransaction?.activeUntil

            if (newTransactionsStartInstant != null && !newTransactionsStartInstantApplied) {
                activationStartTime = listOf(activationStartTime, newTransactionsStartInstant)
                        .filter { it != null }.maxBy { it!! }
                newTransactionsStartInstantApplied = true
            }

            activationStartTime = activationStartTime ?: Instant.now()

            val lastTransactionActiveUntil = OffsetDateTime.ofInstant(activationStartTime, ZoneId.of("UTC"))
                    .plus(it.period)
                    .toInstant()

            it.activeSince = activationStartTime
            it.activeUntil = lastTransactionActiveUntil
            it.previousTransactionId = latestActiveAccountTransaction?.id

            transactionRepository.save(it)
            latestActiveAccountTransaction = it
        }

        account.activeUntil = latestActiveAccountTransaction?.activeUntil
        account.renewNotificationSentAt = null
        accountRepository.save(account)
    }

    fun refreshAccountTransactions(accountId: String, newTransactionsStartInstant: Instant? = null, force: Boolean = false) {
        val account = accountRepository.findOne(accountId)
                ?: throw NoSuchElementException("Failed to refresh account transactions: can't find " +
                "account with requested user id")
        refreshAccountTransactions(account, newTransactionsStartInstant, force)
    }
}