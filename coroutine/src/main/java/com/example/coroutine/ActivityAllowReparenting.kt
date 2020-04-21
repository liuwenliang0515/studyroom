package com.example.coroutine

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class ActivityAllowReparenting: AppCompatActivity() {

//    @Volatile
    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reparenting)

        var stop = false

        thread {
            while (!stop) {
                Log.d("lwl", "num is $num")
            }
        }

        thread {
            num = 1
            Thread.yield()
            num = 2
            Thread.yield()
            num = 3
            Thread.yield()
            num = 4
            Thread.yield()
            num = 5
        }

        Handler().postDelayed({
            stop = true
        }, 1000)




    }

    override fun onResume() {
        super.onResume()
        Log.d("lwl", "reparent onresume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lwl", "reparent onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lwl", "reparent onDestroy")
    }

}