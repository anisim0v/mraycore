package ru.mray.core.model

import java.time.Instant
import java.time.Period
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transactions")
class Transaction(
        @ManyToOne
        val account: Account,

        @Enumerated(EnumType.STRING)
        val region: Account.Region,

        val period: Period,

        @Enumerated(EnumType.STRING)
        val type: TransactionType,

        var issueDate: Instant = Instant.now(),

        @Basic(fetch = FetchType.LAZY)

        var paidAt: Instant? = null,
        var activeSince: Instant? = null,
        var activeUntil: Instant? = null,
        @Id var id: String = UUID.randomUUID().toString()
) {
    enum class TransactionType {
        BONUS,
        PAYMENT
    }
}