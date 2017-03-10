package ru.mray.core.config

import org.springframework.context.annotation.Bean
import org.springframework.session.data.mongo.JdkMongoSessionConverter
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession

@EnableMongoHttpSession
open class HttpSessionConfig {

    @Bean
    open fun jdkMongoSessionConverter(): JdkMongoSessionConverter {
        return JdkMongoSessionConverter()
    }
}