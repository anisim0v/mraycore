package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Family
import ru.mray.core.model.FamilyToken
import ru.mray.core.repository.AccountRepository
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import ru.mray.core.service.FamilyTokenService
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Controller
@RequestMapping("/admin/families")
class FamiliesController(val familyTokenRepository: FamilyTokenRepository,
                         val familyRepository: FamilyRepository,
                         val accountRepository: AccountRepository,
                         val familyTokenService: FamilyTokenService) {
    @RequestMapping
    fun familyTokens(model: Model): String {
        val families = familyRepository.findAll()
        model.addAttribute("families", families)

        return "admin/familyList"
    }

    @RequestMapping("/add")
    fun addFamilyPage(model: Model): String {
        val paidUntil = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ISO_DATE)
        model.addAttribute("paidUntil", paidUntil)
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
            @RequestParam(value = "assignManually", defaultValue = "off") assignManuallyFlag: String,
            @RequestParam(value = "assignToPending", defaultValue = "off") assignToPendingFlag: String,
            @RequestParam tokens: List<String>): String {


        val paidUntilDate = LocalDate.parse(paidUntil)
        val assignManually = when(assignManuallyFlag) {
            "on" -> true
            "off" -> false
            else -> throw IllegalArgumentException("Incorrect assignManually value")
        }
        val assignToPending = when(assignToPendingFlag) {
            "on" -> true
            "off" -> false
            else -> throw IllegalArgumentException("Incorrect assignToPending value")
        }

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
                .mapIndexed { i, it -> FamilyToken(region, family.id, i, it, family.paidUntil, assignManually) }
                .toList()
                .let { familyTokenRepository.save(it) }

        if (assignToPending) {
            familyTokenService.assignTokens(region, 5)
        }

        return "redirect:/admin/families"
    }

    @RequestMapping("/tokens")
    fun tokens(model: Model): String {
        val tokens = familyTokenRepository.findAll()
        model.addAttribute("familyTokens", tokens)
        return "admin/familyTokens"
    }

    @RequestMapping("{family}/renew")
    fun familyRenewPage(@PathVariable family: Family, model: Model): String {
        val paidUntil = family.paidUntil.plusMonths(1).format(DateTimeFormatter.ISO_DATE)
        model.addAttribute("family", family)
        model.addAttribute("paidUntil", paidUntil)
        return "admin/familyRenew"
    }

    @RequestMapping("{family}/renew", method = arrayOf(RequestMethod.POST))
    fun familyRenew(@PathVariable family: Family, @RequestParam paidUntil: String): String {
        family.paidUntil = LocalDate.parse(paidUntil)

        val tokens = familyTokenRepository.findByFamily(family.id)
                .map { it.paidUntil = family.paidUntil; it }

        familyRepository.save(family)
        familyTokenRepository.save(tokens)
        return "redirect:/admin/families/${family.id}"
    }

    @RequestMapping("/byToken/{familyToken}")
    fun familyDetailsByToken(familyToken: FamilyToken): String {
        return "redirect:/admin/families/${familyToken.family}"
    }

    @RequestMapping("/{family}")
    fun familyDetails(@PathVariable family: Family, model: Model): String {
        val familyTokens = familyTokenRepository.findByFamily(family.id)
        model.addAttribute("family", family)
        model.addAttribute("familyTokens", familyTokens)
        return "admin/familyDetails"
    }
}