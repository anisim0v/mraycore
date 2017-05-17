package ru.mray.core.repository

import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.Account.Region
import java.time.Instant
import java.time.OffsetDateTime.now
import javax.persistence.EntityManager

@Repository
interface AccountRepository : JpaRepository<Account, String>, AccountRepositoryCustom {
    fun findByEmailIgnoreCase(email: String): Account?
    fun countByFamilyTokenIsNotNull(): Int

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM accounts\nWHERE accounts.active_until < ? AND EXISTS(\n    SELECT *\n    FROM family_tokens\n    WHERE accounts.id = family_tokens.account_id\n)", nativeQuery = true)
    fun findExpired(instant: Instant = Instant.now()): List<Account>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM accounts\nWHERE accounts.renew_notification_sent_at IS NULL AND active_until < ? AND EXISTS(\n    SELECT *\n    FROM family_tokens\n    WHERE account_id = accounts.id\n)", nativeQuery = true)
    fun findAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): List<Account>
}

interface AccountRepositoryCustom {
    fun countExpired(instant: Instant = Instant.now()): Int
    fun countAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): Long
    fun findPending(region: Region, count: Int = 100): List<Account>
}

class AccountRepositoryImpl(val entityManager: EntityManager) : AccountRepositoryCustom {
    override fun findPending(region: Region, count: Int): List<Account> {

        val query = entityManager.createNativeQuery("SELECT DISTINCT ON (accounts.id) accounts.*\nFROM accounts\n  JOIN transactions\n    ON transactions.account_id = accounts.id AND transactions.paid_at IS NOT NULL AND transactions.active_since IS NULL\nWHERE accounts.region = ?\nORDER BY accounts.id, transactions.paid_at\nLIMIT ?", Account::class.java)
        query.setParameter(1, region.toString())
        query.setParameter(2, count)
        val result = query.resultList

        @Suppress("UNCHECKED_CAST")
        return result as List<Account>
    }

    override fun countExpired(instant: Instant): Int {
        throw UnsupportedOperationException()
    }

    override fun countAccountsToNotify(expiresBefore: Instant): Long {
        throw UnsupportedOperationException()
    }

}