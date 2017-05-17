package ru.mray.core.repository

import org.intellij.lang.annotations.Language
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.Account.Region
import ru.mray.core.model.Transaction
import java.time.Instant
import java.time.OffsetDateTime

@Repository
interface TransactionRepository : JpaRepository<Transaction, String> {
    fun findByAccountId(accountId: String, sort: Sort? = Sort(Sort.Direction.DESC, "issueDate")): List<Transaction>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE paid_at IS NULL AND account_id = :#{[0].id} AND issue_date > :#{[1]}\nORDER BY issue_date DESC\nLIMIT 1", nativeQuery = true)
    fun findLatestUnpaid(account: Account, since: Instant): Transaction?

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE paid_at IS NOT NULL AND account_id = :#{[0].id}\nORDER BY issue_date DESC\nLIMIT 1", nativeQuery = true)
    fun findLatestPaid(account: Account): Transaction?

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE paid_at IS NOT NULL AND active_since IS NULL AND region = :#{[0].toString()}\nORDER BY paid_at", nativeQuery = true)
    fun findInactivePaidTransactions(region: Region): List<Transaction>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE paid_at IS NOT NULL AND active_since IS NULL AND account_id = :#{[0].id}\nORDER BY paid_at", nativeQuery = true)
    fun findAccountInactivePaidTransactions(account: Account): List<Transaction>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE active_since IS NOT NULL AND account_id = :#{[0].id}\nORDER BY issue_date DESC\nLIMIT 1", nativeQuery = true)
    fun findLatestActiveAccountTransaction(account: Account): ru.mray.core.model.Transaction?
}