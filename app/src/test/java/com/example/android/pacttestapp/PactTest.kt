package com.example.android.pacttestapp

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRuleMk2
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.RequestResponsePact
import com.google.gson.Gson
import io.reactivex.observers.TestObserver
import org.apache.http.entity.ContentType
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class PactTest {

    @get:Rule
    val provider = PactProviderRuleMk2("wiki_provider",this)

    @Pact(provider = "wiki_provider", consumer = "wiki_consumer")
    fun createFragment(builder: PactDslWithProvider):RequestResponsePact {

        val bodyObj = Model.Result(Model.Query(Model.SearchInfo(234540)))
        val bodyString = Gson().toJson(bodyObj)

        return builder

            .given("app wants data")
            .uponReceiving("a data request")
            .path("/w/api.php")
            .query("action=query&format=json&list=search&srsearch=TEST")
            .method("GET")

            .willRespondWith()
            .status(200)
            .body(bodyString, ContentType.APPLICATION_JSON)

            .toPact()
    }

    @Test
    @PactVerification("wiki_provider")
    @Throws(IOException::class)
    fun runTest()
    {
        val url:String? = provider.url
        assertNotNull(url)

        val service = WikiApiService.create(provider.url)

        val result: TestObserver<Model.Result> = service.hitCountCheck("query", "json", "search", "TEST").test()
        result.assertNoErrors()
        result.assertValue(Model.Result(Model.Query(Model.SearchInfo(234540))))
    }
}