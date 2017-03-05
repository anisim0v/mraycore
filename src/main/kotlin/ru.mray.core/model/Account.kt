package ru.mray.core.model

import java.time.Instant
import java.util.*

class Account() {
    lateinit var email: String
    lateinit var region: Region
    var renewPeriod: Int = 1
    var provisioned: Boolean = false
    var activeUntil: Instant? = null
    val id: String = UUID.randomUUID().toString()

    constructor(email: String, region: Region, renewPeriod: Int) : this() {
        this.email = email
        this.region = region
        this.renewPeriod = renewPeriod
    }
}

enum class Region {
    PH,
    US
}