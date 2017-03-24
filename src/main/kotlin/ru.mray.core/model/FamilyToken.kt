package ru.mray.core.model

import java.util.*

class FamilyToken {
    var id: String = UUID.randomUUID().toString()
    lateinit var region: Account.Region
    lateinit var familyLogin: String
    lateinit var token: String

    var account: String? = null
}