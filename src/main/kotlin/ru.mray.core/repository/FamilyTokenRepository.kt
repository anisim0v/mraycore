package ru.mray.core.repository

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.FamilyToken
import java.time.LocalDate

@Repository
interface FamilyTokenRepository : JpaRepository<FamilyToken, String>, FamilyTokenRepositoryCustom {
}

interface FamilyTokenRepositoryCustom {
    fun findFirstUnassigned(region: Account.Region,
                            expiresAfter: LocalDate = LocalDate.now(),
                            sort: Sort = Sort(Sort.Order(Sort.Direction.ASC, "family"))): FamilyToken?

    fun countUnassigned(region: Account.Region, expiresAfter: LocalDate = LocalDate.now()): Int
}

class FamilyTokenRepositoryImpl : FamilyTokenRepositoryCustom {
    override fun findFirstUnassigned(region: Account.Region, expiresAfter: LocalDate, sort: Sort): FamilyToken? {
        throw UnsupportedOperationException()
    }

    override fun countUnassigned(region: Account.Region, expiresAfter: LocalDate): Int {
        throw UnsupportedOperationException()
    }

}