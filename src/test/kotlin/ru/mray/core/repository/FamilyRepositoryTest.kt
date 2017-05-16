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
import ru.mray.core.repository.mongo.MongoFamilyTokenRepository
import java.time.LocalDate

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
        assertThat(familyTokenRepository.findFirstUnassigned(Account.Region.PH)).isNotNull()

        familyToken.account = "testAccountId"
        familyTokenRepository.save(familyToken)

        assertThat(familyTokenRepository.findFirstUnassigned(Account.Region.PH)).isNull()

        familyToken.account = null
        familyToken.paidUntil = LocalDate.now().minusDays(10)
        familyTokenRepository.save(familyToken)
        assertThat(familyTokenRepository.findFirstUnassigned(Account.Region.PH)).isNull()

        val familyToken2 = FamilyToken(Account.Region.PH, "testFamily2", 0, "qwe2", LocalDate.now().plusDays(10))
        familyTokenRepository.save(familyToken2)

        assertThat(familyTokenRepository.findFirstUnassigned(Account.Region.PH)).isNotNull()
    }

    @Test
    fun testCountUnassigned() {
        val tokens = listOf(5, 2, 0, 3, 1, 7).reversed().map {
            FamilyToken(Account.Region.PH, "musicray-test$it", 0, "qwe", LocalDate.now().plusDays(10))
        }
        familyTokenRepository.save(tokens)

        val firstUnassigned = familyTokenRepository.findFirstUnassigned(Account.Region.PH)
        assertThat(firstUnassigned!!.family).isEqualTo("musicray-test0")
    }
}