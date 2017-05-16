package ru.mray.core.service

import org.springframework.jmx.export.annotation.ManagedOperation
import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.mongo.MongoAccountRepository
import ru.mray.core.repository.mongo.MongoFamilyRepository
import ru.mray.core.repository.mongo.MongoFamilyTokenRepository
import javax.annotation.PostConstruct

@Service
@ManagedResource
class MigrationService(
        val mongoAccountRepository: MongoAccountRepository,
        val accountRepository: AccountRepository,
        val mongoFamilyRepository: MongoFamilyRepository,
        val familyRepository: FamilyRepository,
        val mongoFamilyTokenRepository: MongoFamilyTokenRepository,
        val familyTokenRepository: FamilyTokenRepository
) {
    @ManagedOperation
    @Transactional
    @PostConstruct
    fun migrate() {
        val accounts = mongoAccountRepository.findAll()
        accountRepository.save(accounts)

        val families = mongoFamilyRepository.findAll()
        familyRepository.save(families)

        val familyTokens = mongoFamilyTokenRepository.findAll()
        familyTokenRepository.save(familyTokens)
    }
}