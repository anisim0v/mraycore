package ru.mray.core.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.jmx.export.annotation.ManagedOperation
import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mray.core.model.Account
import ru.mray.core.model.Family
import ru.mray.core.model.FamilyToken
import ru.mray.core.model.Transaction
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.repository.TransactionRepository
import ru.mray.core.repository.mongo.MongoAccountRepository
import ru.mray.core.repository.mongo.MongoFamilyRepository
import ru.mray.core.repository.mongo.MongoFamilyTokenRepository
import ru.mray.core.repository.mongo.MongoTransactionRepository
import javax.annotation.PostConstruct

@Service
@ManagedResource
class MigrationService(
        val mongoAccountRepository: MongoAccountRepository,
        val accountRepository: AccountRepository,
        val mongoFamilyRepository: MongoFamilyRepository,
        val familyRepository: FamilyRepository,
        val mongoFamilyTokenRepository: MongoFamilyTokenRepository,
        val familyTokenRepository: FamilyTokenRepository,
        val mongoTransactionRepository: MongoTransactionRepository,
        val transactionRepository: TransactionRepository) {

    val logger: Logger = LoggerFactory.getLogger(MigrationService::class.java)

    @ManagedOperation
    @Transactional
    @PostConstruct
    fun migrate() {

        if (accountRepository.count() > 0) {
            logger.info("Skipping migration")
            return
        }

        logger.info("Starting migration")

        val accounts = mongoAccountRepository.findAll()
                .map {
                    Account(id = it.id,
                            email = it.email,
                            region = it.region,
                            renewPeriod = it.renewPeriod,
                            registeredAt = it.registeredAt,
                            activeUntil = it.activeUntil,
                            renewNotificationSentAt = it.renewNotificationSentAt,
                            _password = it._password,
                            admin = it.admin)
                }
        accountRepository.save(accounts)
        logger.info("Accounts migrated")


        val families = mongoFamilyRepository.findAll()
                .map {
                    Family(
                            login = it.login,
                            region = it.region,
                            password = it.password,
                            paidUntil = it.paidUntil,
                            streetName = it.streetName,
                            streetNumber = it.streetNumber,
                            zipCode = it.zipCode,
                            city = it.city,
                            id = it.id
                    )
                }
        familyRepository.save(families)
        logger.info("Families migrated")

        val familyTokens = mongoFamilyTokenRepository.findAll()
                .map {
                    val family = familyRepository.findOne(it.family)
                    val account = it.account?.let { accountRepository.findOne(it) }
                    FamilyToken(region = it.region,
                            family = family,
                            account = account,
                            slot = it.slot,
                            token = it.token,
                            assignManually = it.assignManually,
                            id = it.id)
                }
        familyTokenRepository.save(familyTokens)
        logger.info("FamilyTokens migrated")


        val transactions = mongoTransactionRepository.findAll()
                .map {
                    val account = accountRepository.findOne(it.accountId)
                    Transaction(
                            account = account,
                            region = it.region,
                            period = it.period,
                            type = it.type,
                            issueDate = it.issueDate,
                            paidAt = it.paidAt,
                            activeSince = it.activeSince,
                            activeUntil = it.activeUntil,
                            id = it.id
                    )
                }

        transactionRepository.save(transactions)
        logger.info("Transactions migrated")
    }
}