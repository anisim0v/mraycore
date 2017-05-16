package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.FamilyToken

interface MongoFamilyTokenRepository : MongoRepository<FamilyToken, String> {

}