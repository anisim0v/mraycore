package ru.mray.core.repository

import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.mray.core.model.Account
import ru.mray.core.model.FamilyToken
import java.time.LocalDate

@Repository
interface FamilyTokenRepository : JpaRepository<FamilyToken, String> {
    @Language("PostgreSQL")
    @Query("SELECT count(*)\nFROM family_tokens\nWHERE region = :#{#region.toString()} AND account_id IS NULL AND assign_manually = FALSE AND exists(\n    SELECT *\n    FROM families\n    WHERE families.id = family_tokens.family_id AND families.paid_until > :#{#expiresAfter}\n)", nativeQuery = true)
    fun countUnassigned(@Param("region") region: Account.Region, @Param("expiresAfter") expiresAfter: LocalDate = LocalDate.now()): Int

    @Language("PostgreSQL")
    @Query("SELECT *\nFROM family_tokens\nWHERE region = :#{#region.toString()} AND account_id IS NULL AND assign_manually = FALSE AND exists(\n    SELECT *\n    FROM families\n    WHERE families.id = family_tokens.family_id AND families.paid_until > :#{#expiresAfter}\n)\nORDER BY family_id, slot\nLIMIT 1", nativeQuery = true)
    fun findFirstUnassigned(region: Account.Region,
                            expiresAfter: LocalDate = LocalDate.now()): FamilyToken?

}