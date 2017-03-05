package ru.mray.core.model

import java.time.Instant
import java.util.*

class Payment {
    var id: String = UUID.randomUUID().toString()
    var issueDate: Instant = Instant.now()
    var previousPaymentId: String? = null
    var period: Int = 1
    var paid: Boolean = false
    var paidUntil: Instant? = null
}