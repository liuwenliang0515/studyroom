package com.example.bigimage

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aboutview.R
import com.example.coordinate.toPx
import kotlinx.android.synthetic.main.activity_big_image.*
import kotlinx.android.synthetic.main.activity_water_fall.*
import kotlinx.android.synthetic.main.item_water_fall.view.*
import java.util.*
import kotlin.collections.HashMap

class BigImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_image)


        val tmpOptions = BitmapFactory.Options()
        tmpOptions.inJustDecodeBounds = true
        BitmapFactory.decodeStream(assets.open("image.jpg"), null, tmpOptions)
        Log.d("lwl", "width = ${tmpOptions.outWidth}, height = ${tmpOptions.outHeight}")


    }

}