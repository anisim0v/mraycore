package ru.mray.core.controller.admin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.controller.PayController
import ru.mray.core.repository.AccountRepository


@Controller
@RequestMapping("admin/users")
class UsersPageController(val accountRepository: AccountRepository) {
    val logger: Logger = LoggerFactory.getLogger(PayController::class.java)

    @RequestMapping
    fun generateTable(@RequestParam(defaultValue="0") page: Int,
                      model: Model): String {
        val users = accountRepository.findAll(PageRequest(page, 50))
        model.addAttribute("users", users)

        return "admin/users"
    }
}