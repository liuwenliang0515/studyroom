package com.example.aboutview

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coordinate_layout.*
import kotlinx.android.synthetic.main.user_item.view.*

class CoordinateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.coordinate_layout)
        setSupportActionBar(tool_bar)

        val adapter = UserAdapter(this)
        user_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        user_list.adapter = adapter
        adapter.setDataList(arrayListOf("1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999", "0000"))
        adapter.notifyDataSetChanged()

        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "request layout", Toast.LENGTH_SHORT).show()
//            header_image.requestLayout()

            text_view.text = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        }, 5000)
    }

}

class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var mDataList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(mDataList[position])
    }

    fun setDataList(data: ArrayList<String>) {
        mDataList = data
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTv = itemView.user_name

        fun bindData(data: String) {
            userNameTv.text = data
        }
    }
}