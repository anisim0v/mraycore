package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.exceptions.NotFoundException
import ru.mray.core.model.Account
import ru.mray.core.model.Family
import ru.mray.core.model.FamilyToken
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import java.time.LocalDate


@Controller
@RequestMapping("/admin/families")
class FamiliesController(val familyTokenRepository: FamilyTokenRepository,
                         val familyRepository: FamilyRepository,
                         val accountRepository: AccountRepository) {
    @RequestMapping
    fun familyTokens(model: Model): String {
        val families = familyRepository.findAll()
        model.addAttribute("families", families)

        return "admin/familyList"
    }

    @RequestMapping("/add")
    fun addFamilyPage(): String {
        return "admin/familyAdd"
    }

    @RequestMapping("/add", method = arrayOf(RequestMethod.POST))
    fun addFamily(
            @RequestParam login: String,
            @RequestParam password: String,
            @RequestParam region: Account.Region,
            @RequestParam city: String,
            @RequestParam streetName: String,
            @RequestParam streetNumber: String,
            @RequestParam zipCode: String,
            @RequestParam paidUntil: String,
            @RequestParam tokens: List<String>): String {

        val paidUntilDate = LocalDate.parse(paidUntil)

        val family = Family()
        family.login = login
        family.password = password
        family.region = region
        family.city = city
        family.streetName = streetName
        family.streetNumber = streetNumber
        family.zipCode = zipCode
        family.paidUntil = paidUntilDate
        familyRepository.save(family)

        tokens
                .mapIndexed { i, it -> FamilyToken(region, family.login, i, it) }
                .toList()
                .let { familyTokenRepository.save(it) }

        return "admin/familyAdd"
    }

    @RequestMapping("/tokens")
    fun tokens(model: Model): String {
        val tokens = familyTokenRepository.findAll()
        model.addAttribute("familyTokens", tokens)
        return "admin/familyTokens"
    }

    @RequestMapping("/unlink")
    fun unlinkPage(): String {
        return "admin/familyUnlink"
    }

    @RequestMapping("/unlink", method = arrayOf(RequestMethod.POST))
    fun unlink(@RequestParam email: String,
               @RequestParam newToken: String): String {
        val account = accountRepository.findByEmail(email) ?: throw NotFoundException("Cannot find requested account")

        val familyToken = familyTokenRepository.findByAccount(account.id)
        familyToken.account = null
        familyToken.token = newToken
        familyTokenRepository.save(familyToken)

        account.familyToken = null
        accountRepository.save(account)
        return "admin/familyUnlink"
    }
}