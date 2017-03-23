
package ru.mray.core.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.Account

interface AccountRepository : MongoRepository<Account, String> {
    fun findByEmail(email: String): Account?
    fun findByProvisioned(provisioned: Boolean, pageable: Pageable): List<Account>


}