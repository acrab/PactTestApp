package com.example.android.pacttestapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var repository: Repository

    var disposable: Disposable? = null

    var resultFromApi: Int = 0
    var errorFromApi: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //beginSearch("Pact")
        beginShout("Pact")
    }

    private fun beginShout(whisper: String) {
        repository.toUpper(whisper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(result.OUTPUT) },
                { error -> showError(error.message) }
            )
    }

    fun beginSearch(srsearch: String) {
        repository.search(srsearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(result.query.searchinfo.totalhits) },
                { error -> showError(error.message) }
            )
    }

    private fun showResult(result: String) {
        //Do something with result
        Log.d("**Result**:", result)
        findViewById<TextView>(R.id.text).text = result
    }

    private fun showResult(result: Int) {
        //Do something with result
        resultFromApi = result
        Log.d("**Result**:", result.toString())
        findViewById<TextView>(R.id.text).text = result.toString()
    }

    private fun showError(result: String?) {
        //Do something with result
        errorFromApi = result
        Log.d("**Error**:", result)
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
