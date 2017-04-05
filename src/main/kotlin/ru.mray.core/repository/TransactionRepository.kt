package ru.mray.core.repository

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.mray.core.model.Transaction

interface TransactionRepository : TransactionRepositoryCustom, MongoRepository<Transaction, String> {
    fun findByAccountId(accountId: String): List<Transaction>

    @Query("{ 'accountId': ?0, 'paidAt': { \$ne: null }, 'activeSince': { \$exists: false } }")
    fun findAccountInactivePaidTransactions(accountId: String): List<Transaction>

    @Query("{ 'paidAt': { \$ne: null }, 'activeSince': { \$exists: false } }", count = true)
    fun countInactivePaidTransactions(): Int

    @Query("{ 'paidAt': { \$ne: null }, 'activeSince': { \$exists: false } }")
    fun findInactivePaidTransactions(): List<Transaction>
}


interface TransactionRepositoryCustom {
    fun findLatestActiveAccountTransaction(accountId: String): Transaction?
}

class TransactionRepositoryImpl(val mongoTemplate: MongoTemplate) : TransactionRepositoryCustom {
    override fun findLatestActiveAccountTransaction(accountId: String): Transaction? {
        val query = query(
                Criteria
                        .where("accountId").`is`(accountId)
                        .and("activeSince").ne(null))
                .with(Sort(Sort.Direction.DESC, "issueDate"))

        val result = mongoTemplate.findOne(query, Transaction::class.java)
        return result
    }
}