package ru.mray.core.model

import java.time.Instant
import java.util.*

data class Account (
    val id: String = UUID.randomUUID().toString(),
    var email: String,
    var country: String,
    var renewPeriod: Int = 1,
    var provisioned: Boolean = false,
    var activeUntil: Instant? = null
)