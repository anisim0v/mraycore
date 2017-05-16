package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import ru.mray.core.model.Account

interface MongoAccountRepository : org.springframework.data.mongodb.repository.MongoRepository<Account, String>, ru.mray.core.repository.mongo.MongoAccountRepositoryCustom {
    fun findByEmailIgnoreCase(email: String): ru.mray.core.model.Account?
    fun countByFamilyTokenIsNotNull(): Int

    @org.springframework.data.mongodb.repository.Query("{ 'activeUntil': { \$lt: ?0 }, 'familyToken': { \$exists: true } }")
    fun findExpired(instant: java.time.Instant = java.time.Instant.now()): List<ru.mray.core.model.Account>

    @org.springframework.data.mongodb.repository.Query("{ 'activeUntil': { \$lt: ?0 }, 'familyToken': { \$exists: true } }", count = true)
    fun countExpired(instant: java.time.Instant = java.time.Instant.now()): Int
}

interface MongoAccountRepositoryCustom {
    fun findPending(region: ru.mray.core.model.Account.Region, count: Int = Int.MAX_VALUE): List<ru.mray.core.model.Account>
    fun findAccountsToNotify(expiresBefore: java.time.Instant = java.time.OffsetDateTime.now().plusDays(3).toInstant()): List<ru.mray.core.model.Account>
    fun countAccountsToNotify(expiresBefore: java.time.Instant = java.time.OffsetDateTime.now().plusDays(3).toInstant()): Long
}

class MongoAccountRepositoryImpl(val transactionRepository: TransactionRepository,
                            val mongoTemplate: org.springframework.data.mongodb.core.MongoTemplate) : ru.mray.core.repository.mongo.MongoAccountRepositoryCustom {

    override fun findPending(region: ru.mray.core.model.Account.Region, count: Int): List<ru.mray.core.model.Account> {
        val transactions = transactionRepository.findInactivePaidTransactions(region)
        val pendingAccounts = transactions
                .map { it.accountId }
                .distinct()
                .let {
                    mongoTemplate.find(query(where("_id").`in`(it).and("familyToken").exists(false)), ru.mray.core.model.Account::class.java)
                }
//                The following is necessary since mongo returns unsorted list
                .associateBy { it.id }

        @Suppress("UNCHECKED_CAST")
        val result = transactions
                .map { pendingAccounts[it.accountId] }
                .filter { it != null }
                .take(count) as List<ru.mray.core.model.Account>

        return result
    }

    override fun findAccountsToNotify(expiresBefore: java.time.Instant): List<ru.mray.core.model.Account> {
        return mongoTemplate.find(query(
                where("activeUntil").`lt`(expiresBefore)
                        .and("renewNotificationSentAt").`is`(null)
                        .and("familyToken").exists(true)
        ), ru.mray.core.model.Account::class.java)
    }

    override fun countAccountsToNotify(expiresBefore: java.time.Instant): Long {
        return mongoTemplate.count(query(
                where("activeUntil").`lt`(expiresBefore)
                        .and("renewNotificationSentAt").`is`(null)
                        .and("familyToken").exists(true)
        ), ru.mray.core.model.Account::class.java)
    }
}