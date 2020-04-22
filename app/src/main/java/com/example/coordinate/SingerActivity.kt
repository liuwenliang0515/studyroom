package com.example.coordinate

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    }

    override fun onResume() {
        super.onResume()

        ((header_layout.layoutParams as CoordinatorLayout.LayoutParams).behavior as HeaderBehavior).onOffsetChanged {
            singer_name.visibility = if (it == 0) View.VISIBLE else View.GONE
        }

        header_img.setOnClickListener {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, header_img, "singer_img")
            startActivity(Intent(this, SingerImageActivity::class.java), options.toBundle())
        }
    }

    override fun onPause() {
        super.onPause()
    }
}