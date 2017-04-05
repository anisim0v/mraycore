package ru.mray.core.model

import java.util.*

class FamilyToken() {
    var id: String = UUID.randomUUID().toString()
    lateinit var region: Account.Region
    lateinit var familyLogin: String
    var slot: Int = 0 // Initialization is required for primitive types
    lateinit var token: String
    var account: String? = null


    constructor(region: Account.Region, familyLogin: String,
                slot: Int, token: String) : this() {
        this.region = region
        this.familyLogin = familyLogin
        this.slot = slot
        this.token = token
    }

}