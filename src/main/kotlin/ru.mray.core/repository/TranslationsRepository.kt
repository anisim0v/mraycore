package ru.mray.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.mray.core.model.TranslationPage

interface TranslationsRepository : MongoRepository<TranslationPage, String> {
    fun findByUrlPage(urlPage: String)
}