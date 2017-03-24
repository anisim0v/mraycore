package ru.mray.core.model

import org.springframework.data.annotation.Id
import java.time.LocalDate

class Family {
    @Id lateinit var login: String
    lateinit var region: Account.Region
    lateinit var password: String
    lateinit var paidUntil: LocalDate
    lateinit var streetName: String
    lateinit var streetNumber: String
    lateinit var zipCode: String
    lateinit var city: String
}