package ru.mray.core.controller.admin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import ru.mray.core.controller.JoinController
import ru.mray.core.model.TranslationPage
import ru.mray.core.repository.TranslationsRepository

@Controller
@RequestMapping("/admin/translations")
class NewPage(val translationsRepository: TranslationsRepository) {
    val logger: Logger = LoggerFactory.getLogger(JoinController::class.java)

    @RequestMapping
    fun getPage(): String {
        return "translations/index"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun processForm(@RequestParam titlePage: String,
                    @RequestParam introEmbed: String,
                    @RequestParam wtrflEmbed: String,
                    @RequestParam introPage: String,
                    @RequestParam urlPage: String,
                    model: Model): String {
        val page = TranslationPage(titlePage, introEmbed, wtrflEmbed, introPage, urlPage)
        translationsRepository.save(page)
        logger.info("New page: title: ${page.titlePage} introEmbed: $introEmbed. wtrflEmbed: $wtrflEmbed. urlPage: $urlPage")
        return "translations/index"
    }

    @RequestMapping("/newpage")
    fun newPage(): String {
        return "translations/newpage"
    }
}