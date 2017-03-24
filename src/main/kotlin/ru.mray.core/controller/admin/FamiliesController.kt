package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.Account
import ru.mray.core.model.Family
import ru.mray.core.model.FamilyToken
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository
import java.time.LocalDate


@Controller
@RequestMapping("/admin/families")
class FamiliesController(val familyTokenRepository: FamilyTokenRepository,
                         val familyRepository: FamilyRepository) {
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
                .map {
                    val familyToken = FamilyToken()
                    familyToken.familyLogin = family.login
                    familyToken.region = region
                    familyToken.token = it
                    return@map familyToken
                }
                .toList()
                .let { familyTokenRepository.save(it) }

        return "admin/familyAdd"
    }
}