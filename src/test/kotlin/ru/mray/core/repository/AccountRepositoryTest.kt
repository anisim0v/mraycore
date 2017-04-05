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
import java.time.Period

@RunWith(SpringRunner::class)
@DataMongoTest
class AccountRepositoryTest {
    @Autowired
    lateinit var accountRepository: AccountRepository
    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Before
    fun setUp() {
        accountRepository.deleteAll()
    }

    @Test
    fun testFindLatestActiveAccountTransactionNull() {
        accountRepository.save(listOf(
                Account("bob@example.com", Account.Region.PH, 1).let {
                    val transaction = Transaction(it.id, it.region, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
                    transaction.paidAt = Instant.now()
                    transactionRepository.save(transaction)
                    return@let it
                },
                Account("alice@example.com", Account.Region.PH, 1).let {
                    val transaction = Transaction(it.id, it.region, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
                    transaction.paidAt = Instant.now()
                    transaction.activeSince = Instant.now()
                    transaction.activeUntil = Instant.now().plusSeconds(10)
                    transactionRepository.save(transaction)
                    return@let it
                },
                Account("donald@example.com", Account.Region.PH, 1).let {
                    it.activeUntil = Instant.now().plusSeconds(10)
                    it.familyToken = "test"
                    return@let it
                },
                Account("hillary@example.com", Account.Region.PH, 1).let {
                    it.familyToken = "test"
                    return@let it
                }
        ))

        val pending = accountRepository.findPending()
        assertThat(pending.size).isEqualTo(1)
        assertThat(pending.first().email).isEqualTo("bob@example.com")
    }

    @Test
    fun testFindAccountsToNotify() {
        val daySeconds: Long = 60 * 60 * 24
        accountRepository.save(listOf(
                Account("bob@example.com", Account.Region.PH, 1).let {
                    it.familyToken = "test"
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = Instant.now().minusSeconds(10 * daySeconds)
                    return@let it
                },

                Account("alice@example.com", Account.Region.PH, 1).let {
                    it.familyToken = "test"
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = Instant.now().minusSeconds(daySeconds)
                    return@let it
                },

                Account("hillary@example.com", Account.Region.PH, 1).let {
                    it.familyToken = "test"
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = null
                    return@let it
                },

                Account("donald@example.com", Account.Region.PH, 1).let {
                    it.familyToken = null
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = null
                    return@let it
                },

                Account("rex@example.com", Account.Region.PH, 1).let {
                    it.familyToken = null
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = Instant.now().minusSeconds(10 * daySeconds)
                    return@let it
                }
        ))

        val result = accountRepository.findAccountsToNotify(Instant.now().plusSeconds(3 * daySeconds),
                Instant.now().minusSeconds(3 * daySeconds))
        assertThat(result.map { it.email }).containsExactly("bob@example.com", "hillary@example.com")
    }
}