package ru.mray.core.service

import org.springframework.jmx.export.annotation.ManagedOperation
import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.mongo.MongoAccountRepository

@Service
@ManagedResource
class MigrationService(
        val mongoAccountRepository: MongoAccountRepository,
        val accountRepository: AccountRepository
) {
    @ManagedOperation
    @Transactional
    fun migrate() {
        val accounts = mongoAccountRepository.findAll()
        accountRepository.save(accounts)
    }
}