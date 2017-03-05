package ru.mray.core.model

import java.time.Instant
import java.time.Period
import java.util.*

class Transaction {
    var id: String = UUID.randomUUID().toString()
    var issueDate: Instant = Instant.now()
    var previousPaymentId: String? = null
    var period: Period = Period.ofMonths(1)
    var paidAt: Instant? = null
    var activatedAt: Instant? = null
    var activeUntil: Instant? = null
}