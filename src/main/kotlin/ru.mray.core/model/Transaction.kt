package ru.mray.core.model

import java.time.Instant
import java.time.Period
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Transaction(
        val accountId: String,
        @Enumerated(EnumType.STRING) val region: Account.Region,
        val period: Period,
        @Enumerated(EnumType.STRING) val type: TransactionType,
        var issueDate: Instant = Instant.now(),
        var previousTransactionId: String? = null,
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