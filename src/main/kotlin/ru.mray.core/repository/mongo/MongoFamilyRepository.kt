package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Family
import ru.mray.core.model.mongo.MongoFamily

interface MongoFamilyRepository : MongoRepository<MongoFamily, String> {

}