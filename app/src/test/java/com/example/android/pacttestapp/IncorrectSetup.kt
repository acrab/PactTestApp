@file:Suppress("ClassName")

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

class IncorrectSetup_URL{
    @get:Rule
    val provider = PactProviderRuleMk2("shout_cloud", this)

    private val requestValue = "{\"INPUT\":\"hello world\"}"
    private val returnValue = "{\"INPUT\":\"hello world\",\"OUTPUT\":\"HELLO WORLD\"}"

    @Pact(provider="shout_cloud", consumer ="test_app")
    fun createFragment(builder: PactDslWithProvider):RequestResponsePact{

        return builder

            .given("running")
            .uponReceiving("A lower case string")
            .path("/V1/WRONG")
            .body(requestValue, ContentType.APPLICATION_JSON)
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

        val result = service.shout( ShoutCloudRequest("hello world")).test()

        result.assertNoErrors()
        result.assertValue{
            it.INPUT == "hello world" && it.OUTPUT == "HELLO WORLD"
        }

    }
}

class IncorrectSetup_HEADER{
    @get:Rule
    val provider = PactProviderRuleMk2("shout_cloud", this)

    private val requestValue = "{\"INPUT\":\"hello world\"}"
    private val returnValue = "{\"INPUT\":\"hello world\",\"OUTPUT\":\"HELLO WORLD\"}"

    @Pact(provider="shout_cloud", consumer ="test_app")
    fun createFragment(builder: PactDslWithProvider):RequestResponsePact{

        return builder

            .given("running")
            .uponReceiving("A lower case string")
            .path("/V1/SHOUT")
            .body(requestValue, ContentType.APPLICATION_JSON)
            .headers(mapOf("Content-Type" to "text/plain"))
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

        val result = service.shout( ShoutCloudRequest("hello world")).test()

        result.assertNoErrors()
        result.assertValue{
            it.INPUT == "hello world" && it.OUTPUT == "HELLO WORLD"
        }

    }
}

class IncorrectSetup_Method{
    @get:Rule
    val provider = PactProviderRuleMk2("shout_cloud", this)

    private val requestValue = "{\"INPUT\":\"hello world\"}"
    private val returnValue = "{\"INPUT\":\"hello world\",\"OUTPUT\":\"HELLO WORLD\"}"

    @Pact(provider="shout_cloud", consumer ="test_app")
    fun createFragment(builder: PactDslWithProvider):RequestResponsePact{

        return builder

            .given("running")
            .uponReceiving("A lower case string")
            .path("/V1/SHOUT")
            .body(requestValue, ContentType.APPLICATION_JSON)
            .headers(mapOf("Content-Type" to "application/json"))
            .method("GET")

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

        val result = service.shout( ShoutCloudRequest("hello world")).test()

        result.assertNoErrors()
        result.assertValue{
            it.INPUT == "hello world" && it.OUTPUT == "HELLO WORLD"
        }

    }
}

class IncorrectSetup_Body{
    @get:Rule
    val provider = PactProviderRuleMk2("shout_cloud", this)

    private val requestValue = "\"hello world\""
    private val returnValue = "{\"INPUT\":\"hello world\",\"OUTPUT\":\"HELLO WORLD\"}"

    @Pact(provider="shout_cloud", consumer ="test_app")
    fun createFragment(builder: PactDslWithProvider):RequestResponsePact{

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

        val result = service.shout( ShoutCloudRequest("hello world")).test()

        result.assertNoErrors()
        result.assertValue{
            it.INPUT == "hello world" && it.OUTPUT == "HELLO WORLD"
        }

    }
}