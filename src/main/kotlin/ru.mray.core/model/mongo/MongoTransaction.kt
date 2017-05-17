package ru.mray.core.model.mongo

import org.springframework.data.mongodb.core.mapping.Document
import ru.mray.core.model.Account
import ru.mray.core.model.Transaction
import java.time.Instant
import java.time.Period
import java.util.*
import javax.persistence.Id

@Document(collection = "transaction")
class MongoTransaction(
        val accountId: String,
        val region: Account.Region,
        val period: Period,
        val type: Transaction.TransactionType,
        var issueDate: Instant = Instant.now(),
        var paidAt: Instant? = null,
        var activeSince: Instant? = null,
        var activeUntil: Instant? = null,
        @Id var id: String = UUID.randomUUID().toString()
) {
}