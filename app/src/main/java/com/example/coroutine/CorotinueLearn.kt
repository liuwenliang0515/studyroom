package com.example.coroutine

import android.util.Log
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CorotinueLearn {

    fun learn() {

//        CoroutineScope().launch {
//
//        }

        val job = GlobalScope.launch(Dispatchers.Main + CoroutineName("learn")) {
            val res = async {
                getData()
            }

            val name = getUserCoroutine()


            coroutineContext[Job.Key]

            supervisorScope {

            }

            coroutineScope {

            }

        }

        val myDispatcher = Executors.newSingleThreadExecutor {
            Thread(it, "my thread")
        }.asCoroutineDispatcher().use {

        }

//        GlobalScope.launch(myDispatcher) {
//
//        }
//        myDispatcher.close()
    }

    fun wrong() {
        var i = 0
        Executors.newFixedThreadPool(10)
            .asCoroutineDispatcher().use { dispatcher ->
                List(1000000) {
                    GlobalScope.launch(dispatcher) {
                        i++
                    }
                }.forEach {
//                    it.join()
                }
            }
    }

    suspend fun getUserCoroutine() = suspendCoroutine<String> { continuation ->
        getUser {
            continuation.resume(it)
        }
    }

    fun getUser(callback: (String) -> Unit) {
        //...
        callback.invoke("name")
    }

    class MyContinuationInterceptor : ContinuationInterceptor {
        override val key = ContinuationInterceptor
        override fun <T> interceptContinuation(continuation: Continuation<T>) =
            MyContinuation(continuation)
    }

    class MyContinuation<T>(val continuation: Continuation<T>) : Continuation<T> {
        override val context = continuation.context
        override fun resumeWith(result: Result<T>) {
            Log.d("lwl", "<MyContinuation> $result")
            continuation.resumeWith(result)
        }
    }

    suspend fun getData(): String {
        delay(200)
        return "data"
    }
}