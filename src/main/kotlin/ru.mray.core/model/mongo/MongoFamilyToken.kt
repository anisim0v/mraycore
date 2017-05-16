package ru.mray.core.model.mongo

import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

class MongoFamilyToken(
        val region: MongoAccount.Region,
        val family: String,
        val slot: Int,
        var token: String,
        var paidUntil: LocalDate,
        var account: String? = null,
        var assignManually: Boolean = false,
        @Id var id: String = UUID.randomUUID().toString()
)