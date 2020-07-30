package com.rest.api.application_rest.controller

import com.rest.api.application_rest.model.PostRequest
import com.rest.api.application_rest.model.PostResponse
import com.rest.api.application_rest.model.SampleDataResponse
import org.springframework.web.bind.annotation.*

@RestController
class MyRestController {
    @RequestMapping("/sample")
    fun sampleResponse(@RequestParam(value = "name", defaultValue = "Kotlin") name: String): SampleDataResponse {
        val response = SampleDataResponse()
        response.id = 1
        response.message = "Your name is $name"
        return response
    }


    @RequestMapping(path= ["/test"], method= [RequestMethod.POST])
    fun test(@RequestBody inputPayload: PostRequest): PostResponse? {
        val response = PostResponse()
        response.setRoleNumber(inputPayload.roleNumber * 100)
        response.message="Hello " + inputPayload.name
        response.status=200
        response.roleName=inputPayload.name;
        return response
    }


}