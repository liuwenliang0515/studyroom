package com.example.aboutview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.example.coordinate.SingerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    fun dp2px(dp: Int): Float {
        return Resources.getSystem().displayMetrics.scaledDensity * dp
    }

    var lastOffset = 0
    var height: Float = 0f
        get() = field
        set(value) {
            field = value

//            val rect = Rect(0, 0, initWidth, (initHeight * value).toInt())
//            content_layout.clipBounds = rect

            //不稳定
//            Log.d("lwl", "offset = " + ((value * 200) + 0.5 - lastOffset))
            ViewCompat.offsetTopAndBottom(content_layout, ((value * 200) + 0.5 - lastOffset).toInt())
            lastOffset = ((value * 200) + 0.5).toInt()
//            Log.d("lwl", "after scroll,sum = " + lastOffset)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate")
        setContentView(R.layout.activity_main)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            mainLooper.queue.addIdleHandler {
//                Log.d("lwla", "addIdleHandler")
//                true
//            }
//        }

        start_btn.setOnClickListener {
            if (height == 1f) {
                animator?.reverse()
            } else {
                animator?.start()
            }
        }


        content_layout.viewTreeObserver.addOnGlobalLayoutListener {
            ViewCompat.offsetTopAndBottom(content_layout, (height * 200).toInt())
        }

    }

    var animator: ObjectAnimator? = null
    var initHeight: Int = 0
    var initWidth: Int = 0
    var globalRect: Rect? = null

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume")

        content_layout.post {
            initHeight = content_layout.measuredHeight
            initWidth = content_layout.measuredWidth
            Log.d("lwl", "init bottom = $initHeight")

            animator = ObjectAnimator.ofFloat(this, "height", 0f, 1f).setDuration(1000)
//            animator = ObjectAnimator.ofFloat(content_layout, "translationY", 0f, 100f).setDuration(1000)

            animator?.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                @SuppressLint("SetTextI18n")
                override fun onAnimationEnd(animation: Animator?) {
                    Log.d("lwl", "top = " + content_layout.top)
                    text_view.text =
                        text_view.text.toString() + "abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd"
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                    content_layout.visibility = View.VISIBLE
                }

            })

            animator?.start()
        }

        action_btn.setOnClickListener {
//            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SingerActivity::class.java))
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("lifecycle", "onSaveInstanceState")
        outState.putString("key", "value")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("lifecycle", "onRestoreInstanceState")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onpause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onstop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy")
    }

    fun funC(value: Int) {

    }

    inline fun funA(crossinline block: () -> Int) {
        val a = block()
        funC(a)
    }

    fun funB(): Boolean {
        val list: List<Int> = listOf()
        funA {
            1
//            return true
        }

        inlineFun(1)

        return false
    }

    inline fun <reified T> inlineFun(field: T) {
        val a = 3
        if (a is T) {

        }
    }

    fun funC(list: ArrayList<out TextView>) {
//        val list = arrayListOf<out Any>("")
        val list2: ArrayList<out TextView> = ArrayList()
    }

    private fun loadBitmap() {
//        val options0 = BitmapFactory.Options()
//        options0.inJustDecodeBounds = true
//        BitmapFactory.decodeResource(resources, R.drawable.chenguanxi, options0)
//        options0.inJustDecodeBounds = false
//        val bm0 = BitmapFactory.decodeResource(resources, R.drawable.chenguanxi, options0)
//        Log.d("lwl", "bm0")
//        log(bm0)
//        img1.setImageBitmap(bm0)
//        Log.d("lwl", "jiazai zhihou = " + (img1.drawable as BitmapDrawable).bitmap.byteCount)
//
//        val options = BitmapFactory.Options()
//        options.inJustDecodeBounds = true
//        BitmapFactory.decodeResource(resources, R.drawable.chenguanxi, options)
//        options.inJustDecodeBounds = false
////        options.inDensity = resources.displayMetrics.densityDpi * 2
//        options.inTargetDensity = resources.displayMetrics.densityDpi / 2
//        val bm = BitmapFactory.decodeResource(resources, R.drawable.chenguanxi, options)
//        Log.d("lwl", "bm1")
//        log(bm)
//        img2.setImageBitmap(bm)
//
//        Log.d("lwl", "jiazai zhihou = " + (img2.drawable as BitmapDrawable).bitmap.byteCount)
//
//        ReentrantLock(false).unlock()

//        val options2 = BitmapFactory.Options()
//        options2.inJustDecodeBounds = true
//        BitmapFactory.decodeResource(resources, R.drawable.chenguanxi, options2)
//        options2.inJustDecodeBounds = false
//        options2.inSampleSize = 2
//        val bm2 = BitmapFactory.decodeResource(resources, R.drawable.chenguanxi, options2)
//        Log.d("lwl", "bm2")
//        log(bm2)
//        img3.setImageBitmap(bm2)
    }

    private fun log(bm0: Bitmap) {
        Log.d(
            "lwl",
            "bm1 bm size = " + bm0.byteCount + "; bm.width, height = " + bm0.width + "  " + bm0.height + " density =  " + bm0.density
        )
    }
}
