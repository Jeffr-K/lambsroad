package com.lambsroad.lambsroad.utils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange()
            .pathMatchers("/user/**").permitAll()
            .pathMatchers("/users/**").permitAll()
            .anyExchange().authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
        return http.build()
    }

    @Bean
    fun delegatingPasswordEncoder(): PasswordEncoder? {
        val defaultEncoder: PasswordEncoder = BCryptPasswordEncoder()
        val encoders: MutableMap<String, PasswordEncoder> = HashMap()
        encoders["bcrypt"] = BCryptPasswordEncoder()
        encoders["scrypt"] = SCryptPasswordEncoder()
        val passwordEncoder = DelegatingPasswordEncoder("bcrypt", encoders)
        passwordEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder)
        return passwordEncoder
    }
}
