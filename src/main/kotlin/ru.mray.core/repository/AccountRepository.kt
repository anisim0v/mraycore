package ru.mray.core.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Account
import java.time.Instant

interface AccountRepository : MongoRepository<Account, String>, AccountRepositoryCustom {
    fun findByEmail(email: String): Account?
    fun findByFamilyTokenIsNull(sort: Sort = Sort(Sort.Direction.ASC, "registeredAt")): List<Account>
    fun findByFamilyTokenIsNull(pageable: Pageable): List<Account>
}

interface AccountRepositoryCustom {
    fun findPending(count: Int = 0, sort: Sort = Sort(Sort.Direction.ASC, "registeredAt")): List<Account>
}

class AccountRepositoryImpl(val mongoTemplate: MongoTemplate) : AccountRepositoryCustom {
    override fun findPending(count: Int, sort: Sort): List<Account> {
        var query = query(where("familyToken").`is`(null)
                .and("activeUntil").gt(Instant.now()))
                .with(sort)

        if (count > 0) {
            query = query.limit(count)
        }

        val result = mongoTemplate.find(query, Account::class.java)
        return result
    }
}