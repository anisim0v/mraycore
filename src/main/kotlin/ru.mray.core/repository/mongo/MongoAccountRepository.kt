package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Account

interface MongoAccountRepository : MongoRepository<Account, String> {

}