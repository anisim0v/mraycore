package ru.mray.core.repository

import org.intellij.lang.annotations.Language
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.Account.Region
import ru.mray.core.model.Transaction

@Repository
interface TransactionRepository : JpaRepository<Transaction, String> {
    fun findByAccountId(accountId: String, sort: Sort? = Sort(Sort.Direction.DESC, "issueDate")): List<Transaction>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE paid_at IS NOT NULL AND active_since IS NULL AND region = :#{#region.toString}\nORDER BY paid_at", nativeQuery = true)
    fun findInactivePaidTransactions(region: Region): List<Transaction>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE paid_at IS NOT NULL AND active_since IS NULL AND account_id = #{#account.id}\nORDER BY paid_at", nativeQuery = true)
    fun findAccountInactivePaidTransactions(account: Account): List<Transaction>

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM transactions\nWHERE active_since IS NOT NULL AND account_id = #{#account.id}\nORDER BY issue_date DESC", nativeQuery = true)
    fun findLatestActiveAccountTransaction(account: Account): ru.mray.core.model.Transaction?
}