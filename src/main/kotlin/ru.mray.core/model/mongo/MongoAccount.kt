package ru.mray.core.model.mongo

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.mray.core.model.Account
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Document(collection = "account")
class MongoAccount(
        val email: String,
        val region: Account.Region,
        var renewPeriod: Int = 1,
        var registeredAt: Instant = Instant.now(),
        var familyToken: String? = null,
        var activeUntil: Instant? = null,
        var renewNotificationSentAt: Instant? = null,
        var admin: Boolean = false,
        @Field("password") var _password: String? = null,
        val id: String = UUID.randomUUID().toString()
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        if (admin) {
            authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
        return authorities
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String? {
        return _password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}