package com.example.coroutine

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.jvm.internal.Intrinsics

class SuspendFunctions {
    suspend fun returnSuspended() = suspendCancellableCoroutine<String> { continuation ->
        thread {
            Thread.sleep(1000)
            continuation.resume("Return suspended.")
        }
        kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
    }

    suspend fun returnImmediately() = suspendCancellableCoroutine<String> {
        it.resume("Return immediately.")


        learn {
            1
        }
    }

    fun learn(block: (String) -> Int): Int {
        return block("")
    }

    companion object {
        fun log(msg: Int) {
            Log.d("lwl", msg.toString())
        }
    }
}