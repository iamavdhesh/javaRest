package com.rest.api.application_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApplicationRestApplication

fun main(args: Array<String>) {
    runApplication<ApplicationRestApplication>(*args)
}
