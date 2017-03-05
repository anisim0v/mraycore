package ru.mray.core.model

import java.time.Instant
import java.util.*

data class Account (
        var email: String,
        var region: Region,
        var renewPeriod: Int = 1,
        var provisioned: Boolean = false,
        var activeUntil: Instant? = null,
        val id: String = UUID.randomUUID().toString()
)

enum class Region {
    PH,
    US
}