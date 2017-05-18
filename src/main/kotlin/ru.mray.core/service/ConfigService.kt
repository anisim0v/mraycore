package ru.mray.core.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

@Service
class ConfigService(val environment: Environment,
                    objectMapper: ObjectMapper) {

    final val logger: Logger = LoggerFactory.getLogger(MailService::class.java)

    final val consul: Consul? = consulLet@ let {
        val host = environment.getProperty("mray.consul.host", String::class.java)

        if (host == null) {
            logger.info("mray.consul.host is missing. Using envs")
            return@let null
        }

        return@let Consul.builder()
                .withHostAndPort(HostAndPort.fromParts(host, 8500))
                .build()
    }

    var registrationEnabled: Boolean by BooleanConsulProperty("mray.registration", false, Boolean::class, consul, environment, objectMapper)
    var paymentsEnabled: Boolean by BooleanConsulProperty("mray.payments", false, Boolean::class, consul, environment, objectMapper)
    var notificationsEnabled: Boolean by BooleanConsulProperty("mray.notifications", false, Boolean::class, consul, environment, objectMapper)
    var autoassignmentEnabled: Boolean by BooleanConsulProperty("mray.autoassignment", false, Boolean::class, consul, environment, objectMapper)


    class BooleanConsulProperty<T : Any>(val name: String, val defaultValue: T, val type: KClass<T>,
                                         val consul: Consul?, val environment: Environment,
                                         val objectMapper: ObjectMapper) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {

            val consulResult = consul?.keyValueClient()?.getValueAsString(name.replace(".", "/"))?.orNull()
            val envResult = environment.getProperty(name)
            val resultStr = consulResult ?: envResult

            val result = resultStr?.let { objectMapper.readValue<T>(resultStr, type.java) }

            return result ?: defaultValue
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            if (consul == null) {
                return
            }

            val keyValueClient = consul.keyValueClient()
            keyValueClient.putValue(name.replace(".", "/"), objectMapper.writeValueAsString(value))
        }
    }
}