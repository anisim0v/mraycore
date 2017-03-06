package ru.mray.core.component

import org.junit.Test

import org.junit.Assert.*
import ru.mray.core.model.Account
import java.time.Period

class PricesHolderTest {
    @Test
    fun getPrice() {
        val pricesHolder = PricesHolder()
        val price = pricesHolder.getPrice(Account.Region.PH, Period.ofMonths(1))

        assertEquals(80.0, price, 0.0)
    }
}