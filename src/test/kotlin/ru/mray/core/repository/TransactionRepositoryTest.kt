package ru.mray.core.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit4.SpringRunner
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import java.time.Instant
import java.time.OffsetDateTime
import java.time.Period
import java.util.*

@RunWith(SpringRunner::class)
@DataMongoTest
class TransactionRepositoryTest {
    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Before
    fun setUp() {
        transactionRepository.deleteAll()
    }

    @Test
    fun testFindAccountInactivePaidTransactions() {
        val accountId = UUID.randomUUID().toString()

        val newTransaction = Transaction(accountId, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)

        val paidInactiveTransaction = Transaction(accountId, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
        paidInactiveTransaction.paidAt = Instant.now()

        val paidActiveTransaction = Transaction(accountId, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
        paidActiveTransaction.paidAt = Instant.now()
        paidActiveTransaction.activeSince = Instant.now()

        transactionRepository.save(listOf(newTransaction, paidInactiveTransaction, paidActiveTransaction))

        val all = transactionRepository.findAll()
        val inactivePaidTransactions = transactionRepository.findAccountInactivePaidTransactions(accountId)

        assertThat(all.count()).isEqualTo(3)
        assertThat(inactivePaidTransactions.count()).isEqualTo(1)
    }

    @Test
    fun testFindLatestActiveAccountTransaction() {
        val accountId = UUID.randomUUID().toString()

        val lastTransaction = (0..5)
                .reversed()
                .map {
                    val transaction = Transaction(accountId, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
                    val period = Period.ofMonths(it)
                    val now = OffsetDateTime.now()
                    transaction.paidAt = now.minus(period).toInstant()
                    transaction.activeSince = transaction.paidAt!!.plusSeconds(30)
                    return@map transaction
                }
                .toList()
                .let {
                    transactionRepository.save(it)
                    it.last()
                }

        val foundLastActiveTransaction = transactionRepository.findLatestActiveAccountTransaction(accountId)
        assertThat(foundLastActiveTransaction?.id).isEqualTo(lastTransaction.id)
    }

    @Test
    fun testFindLatestActiveAccountTransactionNull() {
        val accountId = UUID.randomUUID().toString()
        val foundLastActiveTransaction = transactionRepository.findLatestActiveAccountTransaction(accountId)

        assertThat(foundLastActiveTransaction).isNull()
    }
}