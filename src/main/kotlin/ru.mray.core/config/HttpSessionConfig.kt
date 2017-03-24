package ru.mray.core.config

import org.springframework.context.annotation.Bean
import org.springframework.session.data.mongo.JdkMongoSessionConverter
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession

@EnableMongoHttpSession
open class HttpSessionConfig {
    //    See http://docs.spring.io/spring-session/docs/current/reference/html5/#httpsession-mongo
    @Bean
    open fun jdkMongoSessionConverter(): JdkMongoSessionConverter {
        return JdkMongoSessionConverter()
    }
}