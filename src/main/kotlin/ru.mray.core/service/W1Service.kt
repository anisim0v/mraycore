package ru.mray.core.service

import org.apache.commons.codec.digest.DigestUtils
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.Charset
import java.util.*

@Service
open class W1Service(@Value("\${mray.w1.key}") private val signingKey: String) {
    /**
     * Walletone utils service
     */

    val logger = getLogger(W1Service::class.java)

    fun sign(data: Map<String, Any>): String {
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