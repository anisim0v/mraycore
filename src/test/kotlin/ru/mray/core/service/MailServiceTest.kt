package ru.mray.core.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.ui.ExtendedModelMap

@RunWith(SpringRunner::class)
@SpringBootTest
class MailServiceTest {

    @Autowired
    lateinit var mailService: MailService

    @Test
    fun testMailService() {
        val model = ExtendedModelMap()
        model.put("name", "world")
        val renderedTemplate = mailService.renderTemplate("mailTest", model)
        assertThat(renderedTemplate).isEqualTo("Hello world")
    }
}