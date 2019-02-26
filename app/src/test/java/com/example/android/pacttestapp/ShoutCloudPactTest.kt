package com.example.android.pacttestapp

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRuleMk2
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.RequestResponsePact
import org.apache.http.entity.ContentType
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ShoutCloudPactTest{
    @get:Rule
    val provider = PactProviderRuleMk2("shout_cloud", this)

    private val inputString = "lower case"
    private val outputString = "UPPER CASE"


    @Pact(provider="shout_cloud", consumer ="test_app")
    fun createFragment(builder: PactDslWithProvider):RequestResponsePact{
        val requestValue = "{\"INPUT\":\"$inputString\"}"
        val upperCaseRequest = "{\"INPUT\":\"${inputString.toUpperCase()}\"}"

        val returnValue = "{\"INPUT\":\"$inputString\",\"OUTPUT\":\"$outputString\"}"

        return builder

            .given("running")
            .uponReceiving("A lower case string")
            .path("/V1/SHOUT")
            .body(requestValue, ContentType.APPLICATION_JSON)
            .headers(mapOf("Content-Type" to "application/json"))
            .method("POST")

            .willRespondWith()
            .status(200)
            .body(returnValue, ContentType.APPLICATION_JSON)
            .headers(mapOf("Content-Type" to "APPLICATION/JSON"))

            .given("running")
            .uponReceiving("An upper case string")
            .path("/V1/SHOUT")
            .body(upperCaseRequest, ContentType.APPLICATION_JSON)
            .headers(mapOf("Content-Type" to "application/json"))
            .method("POST")

            .willRespondWith()
            .status(200)
            .body(returnValue, ContentType.APPLICATION_JSON)
            .headers(mapOf("Content-Type" to "APPLICATION/JSON"))

            .toPact()
    }

    @Test
    @PactVerification("shout_cloud")
    @Throws(IOException::class)
    fun test()
    {
        val url:String? = provider.url
        Assert.assertNotNull(url)
        val service = ShoutCloudService.create(provider.url)

        val result = service.shout( ShoutCloudRequest(inputString)).test()

        result.assertNoErrors()
        result.assertValue{
            it.INPUT == inputString && it.OUTPUT == outputString
        }

        val resultTwo = service.shout( ShoutCloudRequest(inputString.toUpperCase())).test()

        resultTwo.assertNoErrors()
        resultTwo.assertValue{
            it.INPUT == inputString && it.OUTPUT == outputString
        }

    }
}