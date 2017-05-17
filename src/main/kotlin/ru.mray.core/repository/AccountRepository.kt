package ru.mray.core.repository

import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.Account.Region
import java.time.Instant
import java.time.OffsetDateTime.now
import javax.persistence.EntityManager

@Repository
interface AccountRepository : JpaRepository<Account, String> {
    fun findByEmailIgnoreCase(email: String): Account?
    fun countByFamilyTokenIsNotNull(): Int

    @Language("PostgreSQL")
    @Query("SELECT DISTINCT ON (accounts.id) accounts.*\nFROM accounts\n  JOIN transactions\n    ON transactions.account_id = accounts.id AND transactions.paid_at IS NOT NULL AND transactions.active_since IS NULL\nWHERE accounts.region = :#{[0].toString()}\nORDER BY accounts.id, transactions.paid_at\nLIMIT :#{[1]}", nativeQuery = true)
    fun findPending(region: Region, count: Int = 100): List<Account>

    @Language("PostgreSQL")
    @Query("SELECT count(DISTINCT accounts.id)\nFROM accounts\n  JOIN transactions\n    ON transactions.account_id = accounts.id AND transactions.paid_at IS NOT NULL AND transactions.active_since IS NULL\nWHERE accounts.region = :#{[0].toString()}", nativeQuery = true)
    fun countPending(region: Region): Int

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM accounts\nWHERE accounts.active_until < ? AND EXISTS(\n    SELECT *\n    FROM family_tokens\n    WHERE accounts.id = family_tokens.account_id\n)", nativeQuery = true)
    fun findExpired(instant: Instant = Instant.now()): List<Account>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM accounts\nWHERE accounts.renew_notification_sent_at IS NULL AND active_until < ? AND EXISTS(\n    SELECT *\n    FROM family_tokens\n    WHERE account_id = accounts.id\n)", nativeQuery = true)
    fun findAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): List<Account>

    @Language("PostgreSQL")
    @Query("SELECT count(*)\nFROM accounts\nWHERE accounts.renew_notification_sent_at IS NULL AND active_until < ? AND EXISTS(\n    SELECT *\n    FROM family_tokens\n    WHERE account_id = accounts.id\n)", nativeQuery = true)
    fun countAccountsToNotify(expiresBefore: Instant = now().plusDays(3).toInstant()): Long

    @Language("PostgreSQL")
    @Query("SELECT count(*)\nFROM accounts\nWHERE accounts.active_until < ? AND EXISTS(\n    SELECT *\n    FROM family_tokens\n    WHERE accounts.id = family_tokens.account_id\n)", nativeQuery = true)
    fun countExpired(instant: Instant = Instant.now()): Int
}