package ru.mray.core.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Period

class PeriodUtilsKtTest {
    @Test
    fun describe() {
        assertEquals("1 month", Period.ofMonths(1).describe())
        assertEquals("5 months", Period.ofMonths(5).describe())
    }
}