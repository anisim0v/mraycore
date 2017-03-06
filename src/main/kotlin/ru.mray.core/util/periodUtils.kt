package ru.mray.core.util

import java.time.Period

fun Period. describe(): String {
    val periodTotalMonths = this.toTotalMonths()
    val periodDescription = when (periodTotalMonths.rem(10)) {
        1L -> "$periodTotalMonths month"
        else -> "$periodTotalMonths months"
    }

    return periodDescription
}