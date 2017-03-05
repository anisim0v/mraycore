package ru.mray.core.model

import java.time.Instant
import java.util.*

class Account {
    var id: String = UUID.randomUUID().toString()
    lateinit var email: String
    lateinit var country: String
    var renewPeriod: Int = 1
    var provisioned: Boolean = false
    var latestPaymentId: String? = null
    var activeUntil: Instant? = null
}