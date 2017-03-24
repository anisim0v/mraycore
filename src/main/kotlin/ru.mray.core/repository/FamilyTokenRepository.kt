package ru.mray.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.FamilyToken

interface FamilyTokenRepository : MongoRepository<FamilyToken, String> {

}