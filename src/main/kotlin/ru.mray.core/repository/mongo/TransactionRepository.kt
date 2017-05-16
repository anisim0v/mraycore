package ru.mray.core.repository.mongo

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Query.query
import ru.mray.core.model.Transaction

interface TransactionRepository : ru.mray.core.repository.mongo.TransactionRepositoryCustom, org.springframework.data.mongodb.repository.MongoRepository<Transaction, String> {
    fun findByAccountId(accountId: String, sort: org.springframework.data.domain.Sort? = org.springframework.data.domain.Sort(Sort.Direction.DESC, "issueDate")): List<ru.mray.core.model.Transaction>
    fun findFirstByAccountId(accountId: String, sort: org.springframework.data.domain.Sort? = org.springframework.data.domain.Sort(Sort.Direction.DESC, "issueDate")): ru.mray.core.model.Transaction?

    @org.springframework.data.mongodb.repository.Query("{ 'accountId': ?0, 'paidAt': { \$ne: null }, 'activeSince': { \$exists: false } }")
    fun findAccountInactivePaidTransactions(accountId: String): List<ru.mray.core.model.Transaction>

    @org.springframework.data.mongodb.repository.Query("{ 'paidAt': { \$ne: null }, 'activeSince': { \$exists: false }, 'region': ?0 }")
    fun findInactivePaidTransactions(region: ru.mray.core.model.Account.Region, sort: org.springframework.data.domain.Sort = org.springframework.data.domain.Sort(Sort.Direction.ASC, "paidAt")): List<ru.mray.core.model.Transaction>
}


interface TransactionRepositoryCustom {
    fun findLatestActiveAccountTransaction(accountId: String): ru.mray.core.model.Transaction?
}

class TransactionRepositoryImpl(val mongoTemplate: org.springframework.data.mongodb.core.MongoTemplate) : ru.mray.core.repository.mongo.TransactionRepositoryCustom {
    override fun findLatestActiveAccountTransaction(accountId: String): ru.mray.core.model.Transaction? {
        val query = query(
                org.springframework.data.mongodb.core.query.Criteria
                        .where("accountId").`is`(accountId)
                        .and("activeSince").ne(null))
                .with(org.springframework.data.domain.Sort(Sort.Direction.DESC, "issueDate"))

        val result = mongoTemplate.findOne(query, ru.mray.core.model.Transaction::class.java)
        return result
    }
}