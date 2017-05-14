package ru.mray.core.model

class TranslationPage{
    var titlePage: String? = null
    var introPage: String? = null
    var introEmbed: String? = null
    var wtrflEmbed: String? = null
    var urlPage: String? = null
    constructor(titlePage: String, introEmbed: String, wtrflEmbed: String, introPage: String, urlPage: String) {
        this.titlePage = titlePage
        this.introEmbed = introEmbed
        this.wtrflEmbed = wtrflEmbed
        this.introPage = introPage
        this.urlPage = urlPage
    }
}