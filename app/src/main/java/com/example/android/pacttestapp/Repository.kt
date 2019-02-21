package com.example.android.pacttestapp

import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(private val wikiApiService: WikiApiService)
{
//    fun search(searchTerm:String, onResult:(Int)->Unit, onError:(String?)->Unit)
    fun search(searchTerm:String): Observable<Model.Result>
    {
        return wikiApiService.hitCountCheck("query", "json", "search", searchTerm)
    }
}