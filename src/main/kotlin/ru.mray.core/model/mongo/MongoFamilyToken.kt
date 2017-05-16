package ru.mray.core.model.mongo

import ru.mray.core.model.Account
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

class MongoFamilyToken(
        val region: Account.Region,
        val family: String,
        val slot: Int,
        var token: String,
        var paidUntil: LocalDate,
        var account: String? = null,
        var assignManually: Boolean = false,
        @Id var id: String = UUID.randomUUID().toString()
)