package ru.mray.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mray.core.model.FamilyToken
import ru.mray.core.model.Transaction

@Repository
interface TransactionRepository : JpaRepository<Transaction, String> {

}