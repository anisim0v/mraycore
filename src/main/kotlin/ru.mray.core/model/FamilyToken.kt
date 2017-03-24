package ru.mray.core.model

class FamilyToken {
    lateinit var region: Account.Region
    lateinit var token: String
    lateinit var streetName: String
    lateinit var streetNumber: String
    lateinit var zipCode: String
    lateinit var city: String

    var account: String? = null
}