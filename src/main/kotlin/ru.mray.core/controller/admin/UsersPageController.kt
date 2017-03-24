package ru.mray.core.controller.admin

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.repository.AccountRepository


@Controller
@RequestMapping("admin")
class UsersPageController(val accountRepository: AccountRepository) {
    @RequestMapping("/users")
    fun generateTable(@RequestParam(defaultValue = "0") page: Int,
                      model: Model): String {
        val users = accountRepository.findAll(PageRequest(page, 50))
        model.addAttribute("users", users)

        return "admin/users"
    }

    @RequestMapping("/notprovisioned")
    fun generateNoProvisionedTable(@RequestParam(defaultValue = "0") page: Int,
                                   model: Model): String {
        val users = accountRepository.findByProvisioned(false, PageRequest(page, 50))
        model.addAttribute("users", users)

        return "admin/notprovisioned"
    }
}