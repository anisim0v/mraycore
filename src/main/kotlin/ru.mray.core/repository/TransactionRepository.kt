package ru.mray.core.repository

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.Account.Region
import ru.mray.core.model.Transaction

@Repository
interface TransactionRepository : JpaRepository<Transaction, String>, TransactionRepositoryCustom {

}

interface TransactionRepositoryCustom {
    fun findByAccountId(accountId: String, sort: Sort? = Sort(Sort.Direction.DESC, "issueDate")): List<Transaction>
    fun findFirstByAccountId(accountId: String, sort: Sort? = Sort(Sort.Direction.DESC, "issueDate")): Transaction?
    fun findAccountInactivePaidTransactions(account: Account): List<Transaction>
    fun findInactivePaidTransactions(region: Region, sort: Sort = Sort(Sort.Direction.ASC, "paidAt")): List<Transaction>
    fun findLatestActiveAccountTransaction(account: Account): ru.mray.core.model.Transaction?
}

class TransactionRepositoryImpl : TransactionRepositoryCustom {
    override fun findLatestActiveAccountTransaction(account: Account): Transaction? {
        throw UnsupportedOperationException()
    }

    override fun findAccountInactivePaidTransactions(account: Account): List<Transaction> {
        throw UnsupportedOperationException()
    }

    override fun findByAccountId(accountId: String, sort: Sort?): List<Transaction> {
        throw UnsupportedOperationException()
    }

    override fun findFirstByAccountId(accountId: String, sort: Sort?): Transaction? {
        throw UnsupportedOperationException()
    }

    override fun findInactivePaidTransactions(region: Region, sort: Sort): List<Transaction> {
        throw UnsupportedOperationException()
    }

}