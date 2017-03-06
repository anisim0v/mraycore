package ru.mray.core.component

import org.springframework.stereotype.Component
import org.yaml.snakeyaml.Yaml
import ru.mray.core.model.Account
import java.time.Period
import java.util.*

@Component
open class PricesHolder {

    internal var prices: Map<String, Map<Int, Double>>

    init {
        val resource = javaClass.classLoader.getResource("prices.yaml")
        prices = resource.openStream().use {
            @Suppress("UNCHECKED_CAST")
            Yaml().load(it) as Map<String, Map<Int, Double>>
        }
    }

    fun getPrice(region: Account.Region, period: Period): Double {
        val price = prices[region.name]?.get(period.months)
                ?: throw NoSuchElementException("Cannot get price for $region $period")
        return price
    }

    fun getFormattedPrice(region: Account.Region, period: Period): String {
        val price = getPrice(region, period)
        return String.format("%.2f", price)
    }
}