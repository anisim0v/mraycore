package ru.mray.core.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit4.SpringRunner
import ru.mray.core.model.Account
import ru.mray.core.model.FamilyToken
import ru.mray.core.model.Transaction
import java.time.Instant
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.Period
import java.util.*

@RunWith(SpringRunner::class)
@DataMongoTest
class FamilyRepositoryTest {
    @Autowired
    lateinit var familyTokenRepository: FamilyTokenRepository

    @Before
    fun setUp() {
        familyTokenRepository.deleteAll()
    }

    @Test
    fun testFindLatestActiveAccountTransactionNull() {
        val familyToken = FamilyToken(Account.Region.PH, "testFamily", 0, "qwe", LocalDate.now().plusDays(10))
        familyTokenRepository.save(familyToken)
        assertThat(familyTokenRepository.findFirstByAccountIsNull()).isNotNull()

        familyToken.account = "testAccountId"
        familyTokenRepository.save(familyToken)

        assertThat(familyTokenRepository.findFirstByAccountIsNull()).isNull()

        val familyToken2 = FamilyToken(Account.Region.PH, "testFamily2", 0, "qwe2", LocalDate.now().plusDays(10))
        familyTokenRepository.save(familyToken2)

        assertThat(familyTokenRepository.findFirstByAccountIsNull()).isNotNull()
    }
}