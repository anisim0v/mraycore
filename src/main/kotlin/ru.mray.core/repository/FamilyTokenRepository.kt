package ru.mray.core.repository

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.mray.core.model.Account
import ru.mray.core.model.FamilyToken
import java.time.LocalDate

interface FamilyTokenRepository : MongoRepository<FamilyToken, String> {
    fun findByAccount(accountId: String): FamilyToken?
    fun findByFamilyLogin(familyLogin: String): List<FamilyToken>

    @Query("{ 'account': null, 'paidUntil': { \$gt: ?1 }, 'region': ?0 }")
    fun findFirstUnassigned(region: Account.Region,
                            expiresAfter: LocalDate = LocalDate.now(),
                            sort: Sort = Sort(Sort.Order(Sort.Direction.ASC, "familyLogin"))): FamilyToken?

    @Query("{ 'account': null, 'paidUntil': { \$gt: ?1 }, 'region': ?0 }", count = true)
    fun countUnassigned(region: Account.Region, expiresAfter: LocalDate = LocalDate.now()): Int
}