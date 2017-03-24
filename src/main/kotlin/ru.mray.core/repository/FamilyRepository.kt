package ru.mray.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Family
import ru.mray.core.model.FamilyToken

interface FamilyRepository : MongoRepository<Family, String> {

}