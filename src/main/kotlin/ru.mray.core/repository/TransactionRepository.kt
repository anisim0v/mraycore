package ru.mray.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.mray.core.model.Transaction

interface TransactionRepository : MongoRepository<Transaction, String> {
    @Query("{ 'accountId': ?0, 'paidAt': { \$ne: null }, 'activatedAt': { \$exists: false } }")
    fun findAccountInactivePaidTransactions(accountId: String): List<Transaction>
}