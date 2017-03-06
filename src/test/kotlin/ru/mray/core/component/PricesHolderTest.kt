package ru.mray.core.component

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.mray.core.model.Account
import java.time.Period

class PricesHolderTest {

    val pricesHolder = PricesHolder()

    @Before
    fun setUp() {
        pricesHolder.prices = mapOf(
                Account.Region.PH.name to mapOf(1 to 80.90)
        )
    }

    @Test
    fun getPrice() {
        val price = pricesHolder.getPrice(Account.Region.PH, Period.ofMonths(1))

        assertEquals(80.90, price, 0.0)
    }

    @Test
    fun getFormattedPrice() {
        val formattedPrice = pricesHolder.getFormattedPrice(Account.Region.PH, Period.ofMonths(1))

        assertEquals("80.90", formattedPrice)
    }
}