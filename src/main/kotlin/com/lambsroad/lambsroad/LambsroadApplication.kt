package com.lambsroad.lambsroad

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class LambsroadApplication

fun main(args: Array<String>) {
    runApplication<LambsroadApplication>(*args)
}
