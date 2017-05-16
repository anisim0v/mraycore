package ru.mray.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters


@SpringBootApplication
@EntityScan(basePackageClasses = arrayOf(MRayCoreApplication::class, Jsr310JpaConverters::class))
open class MRayCoreApplication {
}