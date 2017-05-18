package ru.mray.core.service

import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import kotlin.reflect.KProperty

@Service
class ConfigService(environment: Environment) {

    final val consul: Consul? = consulLet@ let {
        val host = environment.getProperty("mray.consul.host", String::class.java)

        if (host == null) {
            logger.info("mray.consul.host is missing. Using envs")
            return@let null
        }

        return@let Consul.builder()
                .withHostAndPort(HostAndPort.fromHost(host))
                .build()
    }

    final val logger: Logger = LoggerFactory.getLogger(MailService::class.java)

    var registrationEnabled: Boolean by BooleanConsulProperty(consul)

    class BooleanConsulProperty(consul: Consul?) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
            return true
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {

        }
    }
}