package ru.mray.core.config

import java.time.Period
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class PeriodConverter : AttributeConverter<Period, String> {
    override fun convertToEntityAttribute(dbData: String): Period {
        return Period.parse(dbData)
    }

    override fun convertToDatabaseColumn(attribute: Period): String {
        return attribute.toString()
    }

}