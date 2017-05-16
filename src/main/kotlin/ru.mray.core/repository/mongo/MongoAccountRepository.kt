package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Account
import ru.mray.core.model.mongo.MongoAccount

interface MongoAccountRepository : MongoRepository<MongoAccount, String> {

}