package ru.mray.core.model

import java.time.Instant
import java.time.Period
import java.util.*

class Transaction() {
    lateinit var accountId: String
    lateinit var period: Period
    var issueDate: Instant = Instant.now()
    var previousPaymentId: String? = null
    var paidAt: Instant? = null
    var activatedAt: Instant? = null
    var activeUntil: Instant? = null
    val id: String = UUID.randomUUID().toString()

    constructor(accountId: String, period: Period) : this() {
        this.accountId = accountId
        this.period = period
    }
}