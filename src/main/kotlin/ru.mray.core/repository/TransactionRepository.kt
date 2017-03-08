package ru.mray.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Transaction

interface TransactionRepository : MongoRepository<Transaction, String>