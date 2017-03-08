package ru.mray.core.service

import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.TransactionRepository
import java.time.Instant
import java.time.Period
import java.util.*

class TransactionServiceTest {
    @Test
    fun refreshAccountTransactions() {
        val transactionRepository = Mockito.mock(TransactionRepository::class.java)

        val accountId = UUID.randomUUID().toString()

        val activatedTransaction = Transaction(accountId, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
        activatedTransaction.activatedAt = Instant.now()
        activatedTransaction.activeUntil = Instant.now().plusSeconds(30)
        activatedTransaction.paidAt = Instant.now()

        Mockito.`when`(transactionRepository.findLatestActiveAccountTransaction(accountId))
                .thenReturn(activatedTransaction)

        val paidTransaction = Transaction(accountId, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
        paidTransaction.paidAt = Instant.now()

        Mockito.`when`(transactionRepository.findAccountInactivePaidTransactions(accountId))
                .thenReturn(listOf(paidTransaction))

        val transactionService = TransactionService(transactionRepository)

        val account = Account("bob@example.com", Account.Region.PH, 1)
        account.id = accountId
        account.provisioned = true

        transactionService.refreshAccountTransactions(account)

        Assertions.assertThat(paidTransaction.previousTransactionId).isEqualTo(activatedTransaction.id)
        Assertions.assertThat(paidTransaction.activatedAt).isNotNull()
        Assertions.assertThat(paidTransaction.activeUntil).isNotNull()
    }
}