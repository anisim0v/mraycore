package ru.mray.core.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.FamilyToken

interface FamilyTokenRepository : MongoRepository<FamilyToken, String> {
    fun findByAccount(accountId: String): FamilyToken?
    fun findByFamilyLogin(familyLogin: String): List<FamilyToken>
    fun findByAccountIsNull(pageable: Pageable): List<FamilyToken>
    fun findFirstByAccountIsNull(): FamilyToken?
    fun countByAccountIsNull(): Int
}