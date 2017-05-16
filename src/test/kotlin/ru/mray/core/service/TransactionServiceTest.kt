package ru.mray.core.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.repository.mongo.MongoAccountRepository
import ru.mray.core.repository.mongo.MongoTransactionRepository
import java.time.Instant
import java.time.OffsetDateTime
import java.time.Period
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class TransactionServiceTest {

    val transactionRepository: TransactionRepository = mock(TransactionRepository::class.java)

    val account = Account("bob@example.com", Account.Region.PH, 1).let {
        it.familyToken = "exampletoken"
        return@let it
    }

    val activatedTransaction = Transaction(account.id, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT).let {
        it.activeSince = Instant.now()
        it.activeUntil = Instant.now().plus(10, ChronoUnit.DAYS)
        it.paidAt = Instant.now()
        return@let it
    }

    val paidTransaction = Transaction(account.id, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT).let {
        it.paidAt = Instant.now()
        return@let it
    }

    val transactionService = TransactionService(transactionRepository, mock(AccountRepository::class.java))

    init {
        `when`(transactionRepository.findLatestActiveAccountTransaction(account.id))
                .thenReturn(activatedTransaction)

        `when`(transactionRepository.findAccountInactivePaidTransactions(account.id))
                .thenReturn(listOf(paidTransaction))
    }

    @Test
    fun testRefreshAccountTransactions() {
        transactionService.refreshAccountTransactions(account)

        assertThat(paidTransaction.previousTransactionId).isEqualTo(activatedTransaction.id)
        assertThat(paidTransaction.activeSince).isNotNull()
        assertThat(paidTransaction.activeUntil)
                .isEqualTo(OffsetDateTime.ofInstant(activatedTransaction.activeUntil!!, ZoneId.of("UTC"))
                        .plus(paidTransaction.period)
                        .toInstant())
    }

    @Test
    fun testServiceUsesNow() {
        activatedTransaction.activeUntil = Instant.now().minusSeconds(10)

        transactionService.refreshAccountTransactions(account)

        assertThat(paidTransaction.activeUntil).isGreaterThanOrEqualTo(OffsetDateTime.ofInstant(activatedTransaction.activeUntil!!, ZoneId.of("UTC"))
                .plus(paidTransaction.period)
                .toInstant())
    }

    @Test
    fun testNothingHappensWhenAccountIsNotProvisioned() {
        account.familyToken = null

        transactionService.refreshAccountTransactions(account)

        assertThat(paidTransaction.previousTransactionId).isNull()
        assertThat(paidTransaction.activeSince).isNull()
        assertThat(paidTransaction.activeUntil).isNull()
    }
}