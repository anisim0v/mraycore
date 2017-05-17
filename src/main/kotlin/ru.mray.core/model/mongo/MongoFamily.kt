package ru.mray.core.model.mongo

import org.springframework.data.mongodb.core.mapping.Document
import ru.mray.core.model.Account
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Document(collection = "family")
class MongoFamily(
        val login: String,
        val region: Account.Region,
        val password: String,
        var paidUntil: LocalDate,
        val streetName: String,
        val streetNumber: String,
        val zipCode: String,
        val city: String,
        @Id val id: String = UUID.randomUUID().toString()
)