package ru.mray.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.mray.core.model.FamilyToken
import java.time.LocalDate

interface FamilyTokenRepository : MongoRepository<FamilyToken, String> {
    fun findByAccount(accountId: String): FamilyToken?
    fun findByFamilyLogin(familyLogin: String): List<FamilyToken>

    @Query("{ 'account': null, 'paidUntil': { \$gt: ?0 } }")
    fun findFirstUnassigned(expiresAfter: LocalDate = LocalDate.now()): FamilyToken?

    @Query("{ 'account': null, 'paidUntil': { \$gt: ?0 } }", count = true)
    fun countUnassigned(expiresAfter: LocalDate = LocalDate.now()): Int
}