package ru.mray.core.service

import org.apache.commons.codec.digest.DigestUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.util.*

@Service
open class W1Service(environment: Environment) {
    /**
     * Walletone utils service
     */

    private val signingKey: String? = environment.getProperty("mray.w1.key")

    final val logger: Logger = getLogger(W1Service::class.java)

    init {
        logger.warn("mray.w1.key is not set. W1Service won't sign or validate transactions")
    }

    fun sign(data: Map<String, Any>): String {
        if (signingKey == null) {
            throw IllegalStateException("mray.w1.key is not set. Failed to sign transaction")
        }

        var singleString = data.keys.toSortedSet()
                .map { key ->
                    data[key]
                }
                .joinToString(separator = "")
        singleString += signingKey

        val byteString = singleString.byteInputStream(Charset.forName("Windows-1251"))
        val hash = DigestUtils.md5(byteString)
        val signature = Base64.getEncoder().encodeToString(hash)

        return signature
    }
}