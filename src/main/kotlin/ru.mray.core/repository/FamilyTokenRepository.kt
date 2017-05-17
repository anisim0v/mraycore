package ru.mray.core.repository

import org.intellij.lang.annotations.Language
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.FamilyToken
import java.time.LocalDate

@Repository
interface FamilyTokenRepository : JpaRepository<FamilyToken, String>, FamilyTokenRepositoryCustom {
    @Language("PostgreSQL")
    @Query("SELECT count(*)\nFROM family_tokens\nWHERE region = :#{#region.toString()} AND account_id IS NULL AND assign_manually = FALSE AND exists(\n    SELECT *\n    FROM families\n    WHERE families.id = family_tokens.family_id AND families.paid_until > :#{#expiresAfter}\n)", nativeQuery = true)
    fun countUnassigned(@Param("region") region: Account.Region, @Param("expiresAfter") expiresAfter: LocalDate = LocalDate.now()): Int
}

interface FamilyTokenRepositoryCustom {
    fun findFirstUnassigned(region: Account.Region,
                            expiresAfter: LocalDate = LocalDate.now(),
                            sort: Sort = Sort(Sort.Order(Sort.Direction.ASC, "family"))): FamilyToken?

}

class FamilyTokenRepositoryImpl : FamilyTokenRepositoryCustom {
    override fun findFirstUnassigned(region: Account.Region, expiresAfter: LocalDate, sort: Sort): FamilyToken? {
        throw UnsupportedOperationException()
    }
}