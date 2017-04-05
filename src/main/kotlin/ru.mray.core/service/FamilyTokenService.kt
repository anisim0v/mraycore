package ru.mray.core.service

import org.springframework.stereotype.Service
import org.springframework.ui.ExtendedModelMap
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import java.time.Instant

@Service
class FamilyTokenService(private val familyTokenRepository: FamilyTokenRepository,
                         private val accountRepository: AccountRepository,
                         private val familyRepository: FamilyRepository,
                         private val transactionService: TransactionService,
                         private val mailService: MailService) {
    fun assignTokenToAccount(account: Account) {
        if (account.familyToken != null) {
            return
        }

        val familyToken = familyTokenRepository.findFirstUnassigned(account.region)
                ?: throw NotFoundException("No free tokens available")
        familyToken.account = account.id
        account.familyToken = familyToken.id

        accountRepository.save(account)
        familyTokenRepository.save(familyToken)

        val family = familyRepository.findOne(familyToken.familyLogin)
                ?: throw NotFoundException("Cannot find family ${familyToken.familyLogin}")

        val model = ExtendedModelMap()
        model.put("account", account)
        model.put("family", family)
        model.put("token", familyToken)

        mailService.sendMail(account, "Приглашение в семью MusicRay", "mail/tokenAssigned", model)

        transactionService.refreshAccountTransactions(account, Instant.now())
    }

    fun assignTokens(tokenCountToAssign: Int) {
        val accounts = accountRepository.findPending(tokenCountToAssign)
        print(accounts.size)
        accounts.forEach { assignTokenToAccount(it) }
    }

    fun unlinkAccount(account: Account, newToken: String) {
        val familyToken = familyTokenRepository.findByAccount(account.id)
                ?: throw NotFoundException("Cannot find requested FamilyToken")
        familyToken.account = null
        familyToken.token = newToken
        familyTokenRepository.save(familyToken)

        account.familyToken = null
        accountRepository.save(account)
    }
}