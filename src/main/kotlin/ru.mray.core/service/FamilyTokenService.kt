package ru.mray.core.service

import org.springframework.stereotype.Service
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.model.Account
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyTokenRepository

@Service
class FamilyTokenService(private val familyTokenRepository: FamilyTokenRepository,
                         private val accountRepository: AccountRepository) {
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
    }
}