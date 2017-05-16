package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Transaction
import ru.mray.core.model.mongo.MongoTransaction

interface MongoTransactionRepository : MongoRepository<MongoTransaction, String> {
}