package ru.mray.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter
import javax.servlet.Filter


@SpringBootApplication
@Configuration
open class MRayCoreApplication {
    @Bean
    open fun logFilter(): Filter {
        val filter = CommonsRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludeClientInfo(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(5120)
        return filter
    }
}