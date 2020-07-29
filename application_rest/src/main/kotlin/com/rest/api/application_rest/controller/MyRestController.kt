package com.rest.api.application_rest.controller

import com.rest.api.application_rest.model.SampleDataResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MyRestController {
    @RequestMapping("/sample")
    fun sampleResponse(@RequestParam(value = "name", defaultValue = "Kotlin") name: String): SampleDataResponse {
        val response = SampleDataResponse()
        response.id = 1
        response.message = "Your name is $name"
        return response
    }



}