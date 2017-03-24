package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import ru.mray.core.repository.AccountRepository


@Controller
@RequestMapping("admin")
class UsersPageController(val accountRepository: AccountRepository) {
    @RequestMapping("/accounts")
    fun users(model: Model): String {
        val accounts = accountRepository.findAll()
        model.addAttribute("accounts", accounts)

        return "admin/accounts/all"
    }

    @RequestMapping("/accounts/pending")
    fun pendingAccounts(model: Model): String {
        val accounts = accountRepository.findByProvisioned(false)
        model.addAttribute("accounts", accounts)

        return "admin/accounts/pending"
    }
}