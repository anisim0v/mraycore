package ru.mray.core.model

import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Family(
        val login: String,
        @Enumerated(EnumType.STRING) val region: Account.Region,
        val password: String,
        var paidUntil: LocalDate,
        val streetName: String,
        val streetNumber: String,
        val zipCode: String,
        val city: String,
        @Id val id: String = UUID.randomUUID().toString()
)