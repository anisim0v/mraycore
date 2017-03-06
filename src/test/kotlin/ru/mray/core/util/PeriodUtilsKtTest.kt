package ru.mray.core.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Period

class PeriodUtilsKtTest {
    @Test
    fun describe() {
        assertEquals("1 месяц", Period.ofMonths(1).describe())
        assertEquals("2 месяца", Period.ofMonths(2).describe())
        assertEquals("5 месяцев", Period.ofMonths(5).describe())
    }
}