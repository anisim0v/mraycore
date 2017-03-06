package ru.mray.core.util

import java.time.Period

fun Period.describe(): String {
    val periodTotalMonths = this.toTotalMonths()
    val periodDescription = when (periodTotalMonths.rem(10)) {
        1L -> "$periodTotalMonths месяц"
        2L, 3L, 4L -> "$periodTotalMonths месяца"
        else -> "$periodTotalMonths месяцев"
    }

    return periodDescription
}