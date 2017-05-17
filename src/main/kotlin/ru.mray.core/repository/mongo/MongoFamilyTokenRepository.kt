package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.FamilyToken
import ru.mray.core.model.mongo.MongoFamilyToken

interface MongoFamilyTokenRepository : MongoRepository<MongoFamilyToken, String> {

}