package ru.mray.core.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit4.SpringRunner
import ru.mray.core.model.Account
import java.time.Instant
import java.util.*

@RunWith(SpringRunner::class)
@DataMongoTest
class AccountRepositoryTest {
    @Autowired
    lateinit var accountRepository: AccountRepository

    @Before
    fun setUp() {
        accountRepository.deleteAll()
    }

    @Test
    fun testFindLatestActiveAccountTransactionNull() {
        accountRepository.save(listOf(
                Account("bob@example.com", Account.Region.PH, 1).let {
                    it.activeUntil = Instant.now().plusSeconds(10.times(3))
                    return@let it
                },
                Account("alice@example.com", Account.Region.PH, 1).let {
                    it.activeUntil = Instant.now().minusSeconds(10.times(3))
                    return@let it
                },
                Account("donald@example.com", Account.Region.PH, 1).let {
                    return@let it
                },
                Account("hillary@example.com", Account.Region.PH, 1).let {
                    it.familyToken = "Uhh.. Hillary Trump."
                    return@let it
                }
        ))

        assertThat(accountRepository.findPending().size).isEqualTo(1)
    }
}