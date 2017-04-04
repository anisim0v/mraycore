package ru.mray.core.model

import java.time.Instant
import java.time.Period
import java.util.*

class Transaction() {
    lateinit var accountId: String
    lateinit var period: Period
    lateinit var region: Account.Region
    lateinit var type: TransactionType
    var issueDate: Instant = Instant.now()
    var previousTransactionId: String? = null
    var paidAt: Instant? = null
    var activeSince: Instant? = null
    var activeUntil: Instant? = null
    var id: String = UUID.randomUUID().toString()

    constructor(accountId: String, region: Account.Region, period: Period, type: TransactionType) : this() {
        this.accountId = accountId
        this.region = region
        this.period = period
        this.type = type
    }

    enum class TransactionType {
        BONUS,
        PAYMENT
    }
}