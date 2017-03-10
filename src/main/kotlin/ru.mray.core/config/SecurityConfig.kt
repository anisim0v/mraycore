package ru.mray.core.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import ru.mray.core.service.MRayUserDetailsService

@Configuration
open class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").failureUrl("/login-fail")
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder,
                        userDetailsService: MRayUserDetailsService) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
    }
}