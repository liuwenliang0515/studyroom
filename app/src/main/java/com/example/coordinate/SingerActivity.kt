package com.example.coordinate

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Path
import android.graphics.PointF
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Property
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aboutview.MainActivity
import com.example.aboutview.R
import com.example.aboutview.UserAdapter
import kotlinx.android.synthetic.main.activity_singer.*

class SingerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singer)

        val adapter = UserAdapter(this)
        play_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        play_list.adapter = adapter
        adapter.setDataList(arrayListOf("1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "0000"))
        adapter.notifyDataSetChanged()

        header_img.setOnClickListener {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, header_img, "singer_img")
            startActivity(Intent(this, SingerImageActivity::class.java), options.toBundle())
        }
    }

    override fun onResume() {
        super.onResume()

        ((header_layout.layoutParams as CoordinatorLayout.LayoutParams).behavior as HeaderBehavior).onOffsetChanged {
            singer_name.visibility = if (it == 0) View.VISIBLE else View.GONE
        }
    }

//        ObjectAnimator.ofObject<View, PointF>(tab_bar, POSITION_PROPERTY, null, Path())
//    private val POSITION_PROPERTY =
//        object : Property<View, PointF>(PointF::class.java, "position") {
//            override fun set(view: View, topLeft: PointF) {
//                val left = Math.round(topLeft.x)
//                val top = Math.round(topLeft.y)
//                val right = left + view.width
//                val bottom = top + view.height
//                view.setLeftTopRightBottom(left, top, right, bottom)
//            }
//
//            override fun get(view: View): PointF? {
//                return null
//            }
//        }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "singer activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "singer activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "singer activity onDestroy")
    }
}