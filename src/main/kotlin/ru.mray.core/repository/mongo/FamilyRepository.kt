package ru.mray.core.repository.mongo

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Family

interface FamilyRepository : org.springframework.data.mongodb.repository.MongoRepository<Family, String> {

}