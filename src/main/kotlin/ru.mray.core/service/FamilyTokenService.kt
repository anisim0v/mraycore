package ru.mray.core.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyTokenRepository

@Service
class FamilyTokenService(private val familyTokenRepository: FamilyTokenRepository,
                         private val accountRepository: AccountRepository,
                         private val transactionService: TransactionService) {
    fun assignTokenToAccount(account: Account) {
        if (account.familyToken != null) {
            return
        }

        val familyToken = familyTokenRepository.findFirstByAccountIsNull()
                ?: throw NotFoundException("No free tokens available")
        familyToken.account = account.id
        account.familyToken = familyToken.id

        accountRepository.save(account)
        familyTokenRepository.save(familyToken)

        transactionService.refreshAccountTransactions(account)
    }

    fun assignTokens(tokenCountToAssign: Int) {
        val tokens = familyTokenRepository.findByAccountIsNull(PageRequest(0, tokenCountToAssign))
        val accpunts = accountRepository.findByFamilyTokenIsNull(
                PageRequest(0, tokenCountToAssign, Sort(Sort.Direction.ASC, "registeredAt")))
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