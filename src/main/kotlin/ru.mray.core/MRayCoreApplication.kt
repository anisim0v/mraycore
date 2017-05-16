package ru.mray.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.*


@SpringBootApplication
open class MRayCoreApplication {
    init {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}