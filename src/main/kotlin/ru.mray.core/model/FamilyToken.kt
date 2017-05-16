package ru.mray.core.model

import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class FamilyToken(
        @Enumerated(EnumType.STRING) val region: Account.Region,
        val family: String,
        val slot: Int,
        var token: String,
        var paidUntil: LocalDate,
        var account: String? = null,
        var assignManually: Boolean = false,
        @Id var id: String = UUID.randomUUID().toString()
)