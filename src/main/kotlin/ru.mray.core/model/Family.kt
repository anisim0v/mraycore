package ru.mray.core.model

import java.time.LocalDate
import java.util.*

class Family {
    var id: String = UUID.randomUUID().toString()
    lateinit var login: String
    lateinit var region: Account.Region
    lateinit var password: String
    lateinit var paidUntil: LocalDate
    lateinit var streetName: String
    lateinit var streetNumber: String
    lateinit var zipCode: String
    lateinit var city: String
}