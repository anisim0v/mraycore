package ru.mray.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.*


@SpringBootApplication
@EnableScheduling
open class MRayCoreApplication {
    init {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}