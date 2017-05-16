package ru.mray.core.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.repository.mongo.AccountRepository
import ru.mray.core.repository.mongo.TransactionRepository

@Controller
@RequestMapping("/")
open class IndexController(val accountRepository: AccountRepository,
                           val transactionRepository: TransactionRepository) {
    val logger: Logger = LoggerFactory.getLogger(IndexController::class.java)

    @RequestMapping
    fun index(): String {
//        logger.info("Serving /")
        return "index"
    }
}