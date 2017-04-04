package ru.mray.core.repository

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Account

interface AccountRepository : MongoRepository<Account, String>, AccountRepositoryCustom {
    fun findByEmail(email: String): Account?
    fun countByfamilyTokenIsNotNull(): Int
}

interface AccountRepositoryCustom {
    fun findPending(count: Int = Int.MAX_VALUE, sort: Sort = Sort(Sort.Direction.ASC, "registeredAt")): List<Account>
}

class AccountRepositoryImpl(val transactionRepository: TransactionRepository,
                            val mongoTemplate: MongoTemplate) : AccountRepositoryCustom {
    override fun findPending(count: Int, sort: Sort): List<Account> {
        val pendingAccounts = transactionRepository.findInactivePaidTransactions()
                .map { it.accountId }
                .toSet()
                .take(count)
                .let {
                    mongoTemplate.find(query(where("_id").`in`(it)).with(sort), Account::class.java)
                }

        return pendingAccounts
    }
}