package ru.mray.core.repository

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.mray.core.model.Account
import java.time.Instant
import java.time.OffsetDateTime

interface AccountRepository : MongoRepository<Account, String>, AccountRepositoryCustom {
    fun findByEmail(email: String): Account?
    fun countByFamilyTokenIsNotNull(): Int

    @Query("{ 'activeUntil': { \$lt: ?0 }, 'familyToken': { \$exists: true } }")
    fun findExpired(instant: Instant = Instant.now()): List<Account>

    @Query("{ 'activeUntil': { \$lt: ?0 }, 'familyToken': { \$exists: true } }", count = true)
    fun countExpired(instant: Instant = Instant.now()): Int
}

interface AccountRepositoryCustom {
    fun findPending(count: Int = Int.MAX_VALUE, sort: Sort = Sort(Sort.Direction.ASC, "registeredAt")): List<Account>
    fun findAccountsToNotify(expiresBefore: Instant = OffsetDateTime.now().plusDays(3).toInstant()): List<Account>
    fun countAccountsToNotify(expiresBefore: Instant = OffsetDateTime.now().plusDays(3).toInstant()): Long
}

class AccountRepositoryImpl(val transactionRepository: TransactionRepository,
                            val mongoTemplate: MongoTemplate) : AccountRepositoryCustom {

    override fun findPending(count: Int, sort: Sort): List<Account> {
        val pendingAccounts = transactionRepository.findInactivePaidTransactions()
                .map { it.accountId }
                .toSet()
                .let {
                    mongoTemplate.find(query(where("_id").`in`(it)).with(sort).limit(count), Account::class.java)
                }
                .filter { it.familyToken == null }

        return pendingAccounts
    }

    override fun findAccountsToNotify(expiresBefore: Instant): List<Account> {
        return mongoTemplate.find(query(
                where("activeUntil").`lt`(expiresBefore)
                        .and("renewNotificationSentAt").`is`(null)
                        .and("familyToken").exists(true)
        ), Account::class.java)
    }

    override fun countAccountsToNotify(expiresBefore: Instant): Long {
        return mongoTemplate.count(query(
                where("activeUntil").`lt`(expiresBefore)
                        .and("renewNotificationSentAt").`is`(null)
                        .and("familyToken").exists(true)
        ), Account::class.java)
    }
}