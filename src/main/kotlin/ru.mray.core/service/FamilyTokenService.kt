package ru.mray.core.service

import org.springframework.stereotype.Service
import org.springframework.ui.ExtendedModelMap
import ru.mray.core.exceptions.NoFreeFamilyTokenAvailableException
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.model.Account
import ru.mray.core.model.FamilyToken
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
    fun assignTokenToAccount(account: Account, token: FamilyToken? = null) {
        if (account.familyToken != null) {
            return
        }

        if (token?.account != null) {
                throw IllegalStateException("Requested token is already assigned")
        }

        val familyToken = token ?: familyTokenRepository.findFirstUnassigned(account.region)
                ?: throw NoFreeFamilyTokenAvailableException("No free tokens available")
        familyToken.account = account.id
        account.familyToken = familyToken.id

        accountRepository.save(account)
        familyTokenRepository.save(familyToken)

        emailInvite(account)

        transactionService.refreshAccountTransactions(account, Instant.now())
    }

    fun emailInvite(account: Account) {
        val familyToken = familyTokenRepository.findOne(account.familyToken)
                ?: throw NotFoundException("Cannot find familyToken ${account.familyToken}")

        val family = familyRepository.findOne(familyToken.family)
                ?: throw NotFoundException("Cannot find family ${familyToken.family}")

        val model = ExtendedModelMap()
        model.put("account", account)
        model.put("family", family)
        model.put("token", familyToken)

        mailService.sendMail(account, "Приглашение в семью MusicRay", "mail/tokenAssigned", model)
    }

    fun assignTokens(region: Account.Region, tokenCountToAssign: Int = Int.MAX_VALUE) {
        val accounts = accountRepository.findPending(region, count = tokenCountToAssign)
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