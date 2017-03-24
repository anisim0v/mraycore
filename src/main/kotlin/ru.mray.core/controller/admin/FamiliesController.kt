package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.repository.FamilyRepository
import ru.mray.core.repository.FamilyTokenRepository


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
            @RequestParam region: String,
            @RequestParam city: String,
            @RequestParam streetName: String,
            @RequestParam streetNumber: String,
            @RequestParam zipCode: String,
            @RequestParam paidUntil: String,
            @RequestParam tokens: List<String>): String {
        return "admin/familyAdd"
    }
}