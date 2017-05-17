package ru.mray.core.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.model.FamilyToken
import ru.mray.core.repository.FamilyTokenRepository

@Controller
@RequestMapping("/admin/familyTokens")
class FamilyTokensController(val familyTokenRepository: FamilyTokenRepository) {
    @RequestMapping("/{token}/setAssignManually")
    fun setAssignManually(@PathVariable token: FamilyToken, @RequestParam enabled: Boolean): String {
        token.assignManually = enabled
        familyTokenRepository.save(token)
        return "redirect:/admin/families/${token.family}"
    }
}