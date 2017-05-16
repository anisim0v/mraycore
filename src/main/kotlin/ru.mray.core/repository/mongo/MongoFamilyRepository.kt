package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Family

interface MongoFamilyRepository : org.springframework.data.mongodb.repository.MongoRepository<Family, String> {

}