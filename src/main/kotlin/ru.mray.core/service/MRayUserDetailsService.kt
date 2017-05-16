package ru.mray.core.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.mray.core.repository.mongo.MongoAccountRepository

@Service
class MRayUserDetailsService(val accountRepository: MongoAccountRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        return accountRepository.findByEmailIgnoreCase(email) ?: throw UsernameNotFoundException("Cannot find account with email $email")
    }
}