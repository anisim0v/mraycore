package ru.mray.core.repository

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.mray.core.model.Transaction

interface TransactionRepository : TransactionRepositoryCustom, MongoRepository<Transaction, String> {
    @Query("{ 'accountId': ?0, 'paidAt': { \$ne: null }, 'activatedAt': { \$exists: false } }")
    fun findAccountInactivePaidTransactions(accountId: String): List<Transaction>
}


interface TransactionRepositoryCustom {
    fun findLatestActiveAccountTransaction(accountId: String): Transaction?
}

class TransactionRepositoryImpl(val mongoTemplate: MongoTemplate) : TransactionRepositoryCustom {
    override fun findLatestActiveAccountTransaction(accountId: String): Transaction? {
        val query = query(
                Criteria
                        .where("accountId").`is`(accountId)
                        .and("activatedAt").ne(null))
                .with(Sort(Sort.Direction.DESC, "activatedAt"))

        val result = mongoTemplate.findOne(query, Transaction::class.java)
        return result
    }
}