package com.lambsroad.lambsroad.utils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange()
            .pathMatchers("/user/**").permitAll()
            .anyExchange().authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}