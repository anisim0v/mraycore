package ru.mray.core.model.mongo

import ru.mray.core.model.Account
import java.time.Instant
import java.time.Period
import java.util.*
import javax.persistence.Id

class MongoTransaction(
        val accountId: String,
        val region: Account.Region,
        val period: Period,
        val type: TransactionType,
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