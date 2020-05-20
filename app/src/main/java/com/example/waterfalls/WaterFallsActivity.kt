package com.example.waterfalls

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.activity_water_fall.*
import kotlinx.android.synthetic.main.item_water_fall.view.*
import java.util.*
import kotlin.collections.HashMap

class WaterFallsActivity : AppCompatActivity() {

    val dataList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_fall)
        repeat(100) { dataList.add("AAA") }
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(true)
        recycler_view.itemAnimator = null

        recycler_view.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val index = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
                if (index % 2 == 0) {
                    outRect.left = 0
                    view.text_view.text = "zuo"
                } else {
                    view.text_view.text = "you"
                    outRect.left = 5.toPx()
                }
                outRect.bottom = 10.toPx()
            }

            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                c.drawColor(Color.BLUE)
            }
        })

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val first = IntArray(2)
                layoutManager.findFirstCompletelyVisibleItemPositions(first)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    layoutManager.invalidateSpanAssignments()
                }
            }
        })

        recycler_view.adapter = WaterFallAdapter()

        testSparseArray()
    }

    fun getRandomHeight(position: Int): Int {
        if (position == 0) return 40.toPx()
        return 80.toPx()
    }

    fun testSparseArray() {
        val array = SparseArray<String>(5)
        array.put(0, "0")
        array.put(1, "4")
        array.put(2, "3")
        array.put(3, "2")
        array.put(4, "1")

        for (i in 0 until array.size()) {
            Log.d("lwl", "array $i = ${array.valueAt(i)}")
        }

//        val map = ArrayMap<Int, String>()
//        map.valu

    }

    inner class WaterFallAdapter: RecyclerView.Adapter<WaterFallAdapter.WaterFallHolder>() {
        @SuppressLint("InflateParams")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterFallHolder {
            return WaterFallHolder(LayoutInflater.from(this@WaterFallsActivity).inflate(R.layout.item_water_fall, null))
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(holder: WaterFallHolder, position: Int) {
            val lp = holder.root.layoutParams
            lp.height = getRandomHeight(position).toPx()
            holder.root.layoutParams = lp
//            holder.textView.text = dataList[position]
        }


        inner class WaterFallHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.text_view
            val root: FrameLayout = itemView.root_view
        }
    }
}