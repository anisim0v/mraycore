package ru.mray.core.repository

import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.Account.Region
import java.time.Instant
import java.time.OffsetDateTime.now

@Repository
interface AccountRepository : JpaRepository<Account, String>, AccountRepositoryCustom {
    fun findByEmailIgnoreCase(email: String): Account?
    fun countByFamilyTokenIsNotNull(): Int

    @Query("SELECT *\nFROM accounts\nWHERE accounts.active_until < ? AND EXISTS(\n    SELECT *\n    FROM family_tokens\n    WHERE accounts.id = family_tokens.account_id\n)", nativeQuery = true)
    fun findExpired(instant: Instant = Instant.now()): List<Account>

    @Language("PostgreSQL")
    @Query("SELECT DISTINCT ON (accounts.id) *\nFROM accounts\n  JOIN transactions\n    ON transactions.account_id = accounts.id AND transactions.paid_at IS NOT NULL AND transactions.active_since IS NULL\nWHERE accounts.region = ?\nORDER BY accounts.id, transactions.paid_at\nLIMIT ?", nativeQuery = true)
    fun findPending(region: Region, count: Int = 100): List<Account>
}

interface AccountRepositoryCustom {
    fun countExpired(instant: Instant = Instant.now()): Int
    fun findAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): List<Account>
    fun countAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): Long
}

class AccountRepositoryImpl(val jdbcTemplate: JdbcTemplate) : AccountRepositoryCustom {

    override fun countExpired(instant: Instant): Int {
        throw UnsupportedOperationException()
    }


    override fun findAccountsToNotify(expiresBefore: Instant): List<Account> {
        throw UnsupportedOperationException()
    }

    override fun countAccountsToNotify(expiresBefore: Instant): Long {
        throw UnsupportedOperationException()
    }

}