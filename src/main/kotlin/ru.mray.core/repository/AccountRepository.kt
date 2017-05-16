package ru.mray.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.Account.Region
import java.time.Instant
import java.time.OffsetDateTime.*

@Repository
interface AccountRepository : JpaRepository<Account, String>, AccountRepositoryCustom {

}

interface AccountRepositoryCustom {
    fun findByEmailIgnoreCase(email: String): Account?
    fun countByFamilyTokenIsNotNull(): Int
    fun findExpired(instant: Instant = Instant.now()): List<Account>
    fun countExpired(instant: Instant = Instant.now()): Int
    fun findPending(region: Region, count: Int = Int.MAX_VALUE): List<Account>
    fun findAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): List<Account>
    fun countAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): Long
}

class AccountRepositoryImpl : AccountRepositoryCustom {
    override fun findByEmailIgnoreCase(email: String): Account? {
        throw UnsupportedOperationException()
    }

    override fun countByFamilyTokenIsNotNull(): Int {
        throw UnsupportedOperationException()
    }

    override fun findExpired(instant: Instant): List<Account> {
        throw UnsupportedOperationException()
    }

    override fun countExpired(instant: Instant): Int {
        throw UnsupportedOperationException()
    }

    override fun findPending(region: Region, count: Int): List<Account> {
        throw UnsupportedOperationException()
    }

    override fun findAccountsToNotify(expiresBefore: Instant): List<Account> {
        throw UnsupportedOperationException()
    }

    override fun countAccountsToNotify(expiresBefore: Instant): Long {
        throw UnsupportedOperationException()
    }

}