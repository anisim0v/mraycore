package ru.mray.core.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import ru.mray.core.repository.mongo.MongoTransactionRepository
import java.time.Instant
import java.time.OffsetDateTime
import java.time.Period
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionRepositoryTest {
    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Before
    fun setUp() {
        transactionRepository.deleteAll()
    }

    @Test
    fun testFindAccountInactivePaidTransactions() {
        val account = Account("example@example.com", Account.Region.PH)
        accountRepository.save(account)

        val newTransaction = Transaction(account, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)

        val paidInactiveTransaction = Transaction(account, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
        paidInactiveTransaction.paidAt = Instant.now()

        val paidActiveTransaction = Transaction(account, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
        paidActiveTransaction.paidAt = Instant.now()
        paidActiveTransaction.activeSince = Instant.now()

        transactionRepository.save(listOf(newTransaction, paidInactiveTransaction, paidActiveTransaction))

        val all = transactionRepository.findAll()
        val inactivePaidTransactions = transactionRepository.findAccountInactivePaidTransactions(account)

        assertThat(all.count()).isEqualTo(3)
        assertThat(inactivePaidTransactions.count()).isEqualTo(1)
    }

    @Test
    fun testFindLatestActiveAccountTransaction() {
        val account = Account("example@example.com", Account.Region.PH)
        accountRepository.save(account)

        val lastTransaction = (0..5)
                .reversed()
                .map {
                    val transaction = Transaction(account, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
                    val period = Period.ofMonths(it)
                    val now = OffsetDateTime.now()
                    transaction.paidAt = now.minus(period).toInstant()
                    transaction.issueDate = now.minus(period).toInstant()
                    transaction.activeSince = transaction.paidAt!!.plusSeconds(30)
                    return@map transaction
                }
                .toList()
                .let {
                    transactionRepository.save(it)
                    it.maxBy { it.issueDate }!!
                }

        val foundLastActiveTransaction = transactionRepository.findLatestActiveAccountTransaction(account)
        assertThat(foundLastActiveTransaction?.id).isEqualTo(lastTransaction.id)
    }

    @Test
    fun testFindLatestActiveAccountTransactionNull() {
        val account = Account("example@example.com", Account.Region.PH)
        val foundLastActiveTransaction = transactionRepository.findLatestActiveAccountTransaction(account)

        assertThat(foundLastActiveTransaction).isNull()
    }

    @Test
    fun testFindInactivePaidTransactions() {
        val account = Account("example@example.com", Account.Region.PH)
        accountRepository.save(account)

        (0..5)
                .map {
                    val transaction = Transaction(account, Account.Region.PH, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
                    val now = OffsetDateTime.now()
                    transaction.paidAt = now.plusSeconds(it.toLong()).toInstant()
                    return@map transaction
                }
                .toList()
                .let {
                    transactionRepository.save(it)
                    it
                }

        val inactivePaidTransactions = transactionRepository.findInactivePaidTransactions(region = Account.Region.PH)

        assertThat(inactivePaidTransactions.count()).isEqualTo(6)
        assertThat(transactionRepository.findInactivePaidTransactions(region = Account.Region.US).count()).isEqualTo(0)
    }
}