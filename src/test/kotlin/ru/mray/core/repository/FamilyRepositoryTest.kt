package ru.mray.core.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import ru.mray.core.model.Account
import ru.mray.core.model.Family
import ru.mray.core.model.FamilyToken
import java.time.LocalDate

@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FamilyRepositoryTest {
    @Autowired
    lateinit var familyTokenRepository: FamilyTokenRepository

    @Autowired
    lateinit var familyRepository: FamilyRepository

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Before
    fun setUp() {
        familyTokenRepository.deleteAll()
    }

    @Test
    fun testFindFirstUnassigned() {
        val family = Family("test", Account.Region.PH, "testp", LocalDate.now().plusDays(30), "tests", "testn", "123000", "Moscow")
        val familyToken = FamilyToken(Account.Region.PH, family, 0, "qwe")
        familyRepository.save(family)
        familyTokenRepository.save(familyToken)

        assertThat(familyTokenRepository.findFirstUnassigned(Account.Region.PH)).isNotNull()


        val account = Account("bob@example.com", Account.Region.PH, 1)
        accountRepository.save(account)

        familyToken.account = account
        familyTokenRepository.save(familyToken)

        assertThat(familyTokenRepository.findFirstUnassigned(Account.Region.PH)).isNull()


        val familyToken2 = FamilyToken(Account.Region.PH, family, 0, "qwe2")
        familyTokenRepository.save(familyToken2)

        assertThat(familyTokenRepository.findFirstUnassigned(Account.Region.PH)).isNotNull()
    }

    @Test
    fun testCountUnassigned() {
        val family = Family("test", Account.Region.PH, "testp", LocalDate.now().plusDays(30), "tests", "testn", "123000", "Moscow")
        familyRepository.save(family)

        val tokens = listOf(5, 2, 0, 3, 1, 7).reversed().map {
            FamilyToken(Account.Region.PH, family, it, "qwe")
        }
        familyTokenRepository.save(tokens)

        val firstUnassigned = familyTokenRepository.findFirstUnassigned(Account.Region.PH)
        assertThat(firstUnassigned!!.slot).isEqualTo(0)
    }
}