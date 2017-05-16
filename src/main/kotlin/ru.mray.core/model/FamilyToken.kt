package ru.mray.core.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "family_tokens")
class FamilyToken(
        @Enumerated(EnumType.STRING)
        val region: Account.Region,

        @ManyToOne
        val family: Family,

        val slot: Int,
        var token: String,
        var paidUntil: LocalDate,

        @OneToOne
        var account: Account? = null,
        var assignManually: Boolean = false,
        @Id var id: String = UUID.randomUUID().toString()
)