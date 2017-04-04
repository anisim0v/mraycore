package ru.mray.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.FamilyToken

interface FamilyTokenRepository : MongoRepository<FamilyToken, String> {
    fun findByAccount(accountId: String): FamilyToken?
    fun findByFamilyLogin(familyLogin: String): List<FamilyToken>
    fun findFirstByAccountIsNull(): FamilyToken?
}