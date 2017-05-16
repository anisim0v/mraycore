package ru.mray.core.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import ru.mray.core.model.Account
import ru.mray.core.model.Family
import ru.mray.core.model.FamilyToken
import ru.mray.core.model.Transaction
import java.time.Instant
import java.time.LocalDate
import java.time.Period

@RunWith(SpringRunner::class)
@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    lateinit var accountRepository: AccountRepository

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var familyTokenRepository: FamilyTokenRepository
    @Autowired
    lateinit var familyRepository: FamilyRepository

    @Before
    fun setUp() {
        accountRepository.deleteAll()
    }

    @Test
    fun testFindPending() {

        val family = Family("test", Account.Region.PH, "testp", LocalDate.MAX, "tests", "testn", "123000", "Moscow")
        familyRepository.save(family)

        Account("bob@example.com", Account.Region.PH, 1).let {
            val transaction = Transaction(it, it.region, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
            transaction.paidAt = Instant.now()
            accountRepository.save(it)
            transactionRepository.save(transaction)
            return@let it
        }

        Account("bob2@example.com", Account.Region.PH, 1).let {
            val transaction = Transaction(it, it.region, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
            transaction.paidAt = Instant.now().plusSeconds(40)
            accountRepository.save(it)
            transactionRepository.save(transaction)
            return@let it
        }

        Account("bob3@example.com", Account.Region.PH, 1).let {
            val transaction = Transaction(it, it.region, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
            transaction.paidAt = Instant.now().plusSeconds(10)
            accountRepository.save(it)
            transactionRepository.save(transaction)
            return@let it
        }

        Account("bob4@example.com", Account.Region.PH, 1).let {
            val transaction = Transaction(it, it.region, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
            transaction.paidAt = Instant.now().plusSeconds(30)
            accountRepository.save(it)
            transactionRepository.save(transaction)
            return@let it
        }

        Account("alice@example.com", Account.Region.PH, 1).let {
            val transaction = Transaction(it, it.region, Period.ofMonths(1), Transaction.TransactionType.PAYMENT)
            transaction.paidAt = Instant.now()
            transaction.activeSince = Instant.now()
            transaction.activeUntil = Instant.now().plusSeconds(10)
            accountRepository.save(it)
            transactionRepository.save(transaction)
            return@let it
        }

        Account("donald@example.com", Account.Region.PH, 1).let {
            it.activeUntil = Instant.now().plusSeconds(10)

            val familyToken = FamilyToken(Account.Region.PH, family, 1, "testt")
            it.familyToken = familyToken
            familyToken.account = it
            accountRepository.save(it)
            familyTokenRepository.save(familyToken)

            return@let it
        }

        Account("hillary@example.com", Account.Region.PH, 1).let {
            val familyToken = FamilyToken(Account.Region.PH, family, 1, "testt")
            it.familyToken = familyToken
            familyToken.account = it
            accountRepository.save(it)
            familyTokenRepository.save(familyToken)
            return@let it
        }

        val pending = accountRepository.findPending(Account.Region.PH)
        assertThat(pending.size).isEqualTo(4)
        assertThat(pending.map { it.email }).isEqualTo(listOf("bob@example.com", "bob3@example.com", "bob4@example.com", "bob2@example.com"))
    }

    @Test
    fun testFindAccountsToNotify() {
        val daySeconds: Long = 60 * 60 * 24
        accountRepository.save(listOf(
                Account("bob@example.com", Account.Region.PH, 1).let {
                    it.familyToken = Mockito.mock(FamilyToken::class.java)
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = Instant.now().minusSeconds(10 * daySeconds)
                    return@let it
                },

                Account("alice@example.com", Account.Region.PH, 1).let {
                    it.familyToken = Mockito.mock(FamilyToken::class.java)
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = Instant.now().minusSeconds(daySeconds)
                    return@let it
                },

                Account("hillary@example.com", Account.Region.PH, 1).let {
                    it.familyToken = Mockito.mock(FamilyToken::class.java)
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = null
                    return@let it
                },

                Account("donald@example.com", Account.Region.PH, 1).let {
                    it.familyToken = Mockito.mock(FamilyToken::class.java)
                    it.activeUntil = Instant.now().plusSeconds(10 * daySeconds)
                    it.renewNotificationSentAt = null
                    return@let it
                },

                Account("rex@example.com", Account.Region.PH, 1).let {
                    it.familyToken = null
                    it.activeUntil = Instant.now().plusSeconds(daySeconds)
                    it.renewNotificationSentAt = null
                    return@let it
                }
        ))

        val result = accountRepository.findAccountsToNotify(Instant.now().plusSeconds(3 * daySeconds))
        assertThat(result.map { it.email }).containsExactly("hillary@example.com")
    }

    @Test
    fun testPendingAccountsSort() {
        val result = listOf("a", "c", "b", "c", "d", "e", "f")
                .distinct()
                .takeWhile { it != "e" }
        assertThat(result).isEqualTo(listOf("a", "c", "b", "d"))
    }
}