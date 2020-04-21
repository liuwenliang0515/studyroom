package com.example.coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.NullPointerException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("lwl", "MAIN oncreate")

        btn.setOnClickListener {
            startActivity(Intent(this, ActivityAllowReparenting::class.java))
        }

        val map = ConcurrentHashMap<Int, String>(16)

        repeat(1000) {
            map[it] = "test"
        }

//        val runSuspend = RunSuspend()
//        val table = ContinuationImpl(runSuspend)
//        table.resumeWith(Unit)
//        runSuspend.await()

        //异常
//        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//            log("${coroutineContext[CoroutineName]} $throwable")
//        }

//        GlobalScope.launch(exceptionHandler + CoroutineName("parent")) {
//
//            launch(exceptionHandler + CoroutineName("launch")) {
//                val job = launch(exceptionHandler + CoroutineName("async")) {
//                    delay(1000)
//                    throw NullPointerException("lwl")
//                }
//
//
////                try {
////                    delay(3000)
////                } catch (e: Exception) {
//                    Log.d("lwl", "catch1")
////                }
////
////                try {
////                    val ss = job.await()
////                } catch (e: Exception) {
////                    Log.d("lwl", "catch2")
////                }
//            }
//
//
//        }

//        fun1()
//        GlobalScope.launch(Dispatchers.Main) {
//            log("start")
//            log("callback data = ${getData()}")
//            log("next")
//            val other = getData()
//            log("other")
//            log("other = $other")
//        }


//        val handler = CoroutineExceptionHandler {
//            context, throwable ->
//            log("${context[CoroutineName]} $throwable")
//        }
//
//        GlobalScope.launch(handler) {
//            log(1)
//
//            supervisorScope {
//                launch(handler) {
//                    log(2)
//                    delay(1000)
//                    throw NullPointerException("lwl")
//                }
//            }
//
//        }


//        GlobalScope.launch {
//            val job = GlobalScope.launch {
//                log("1")
//                val deferred = getUserCoroutine()
//                log("2")
//                deferred.invokeOnCompletion {
//                    log("invokeOnCompletion, $it, ${deferred.isCancelled}")
//                }
//                try {
//                    log(deferred.await())
//                } catch (e: Exception) {
//                    log(e.message ?: "")
//                }
//                log(3)
//            }
//            delay(10)
//            job.cancelAndJoin()
//        }




//        GlobalScope.launch {
//            log(1)
//            val job = launch {
//                launch {
//                    delay(1000)
//                    log(99)
//                }
//                delay(500)
//                log(2)
//            }
//            log(3)
//            delay(100)
//            job.cancelAndJoin()
//        }


    }


    fun getUserCoroutine(): Deferred<String> {
        val def = CompletableDeferred<String>()
        thread {
            Thread.sleep(3000)
            def.complete("done")
        }
        return def
    }


    suspend fun getData() = suspendCancellableCoroutine<String> { continuation ->
        getDataDelay {
            continuation.resume(it)
        }
    }

    fun getDataDelay(callback: (String) -> Unit) {
        thread {
            Thread.sleep(2000)
            callback.invoke("success")
        }
    }

    fun fun1() {
        GlobalScope.launch {
            //            fetch()

            log("parent start")
            coroutineScope {
                launch {
                    delay(5000)
                }
                log("launch finish")
            }
            log("parent finish")
        }
        log("cor finish")
    }

    suspend fun fetch() {
        coroutineScope {
            log("repeat start")
            launch {
                delay(5000)
            }

            log("repeat finish")
        }
        log("fetch finish")
    }

    fun log(msg: String) {
        Log.d("lwl", msg)
    }

    fun log(num: Int) {
        Log.d("lwl", num.toString())
    }
}
