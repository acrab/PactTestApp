@file:Suppress("TestFunctionName")

package com.example.android.pacttestapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Callable


//@RunWith(AndroidJUnit4::class)
//class StandardTest {
//
//    private lateinit var subject: Repository
//    private lateinit var api: WikiApiService
//
//    private lateinit var observable: Observable<Model.Result>
//    private lateinit var response: Model.Result
//    @Before
//    fun setup() {
//        response = Model.Result(Model.Query(Model.SearchInfo(1)))
//
//        observable = Observable.just(response)
//
//        api = mock {
//            on { hitCountCheck(any(), any(), any(), any()) } doReturn observable
//        }
//        subject = Repository(api)
//    }
//
//    @Test
//    fun When_Searching_Gives_A_Result_Its_Returned() {
//        val result:TestObserver<Model.Result> = subject.search("Pact").test()
//
//        result.assertNoErrors()
//        result.assertValue(response)
//    }
//}