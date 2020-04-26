package com.example.coordinate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aboutview.R
import kotlinx.android.synthetic.main.activity_singer_img.*

class SingerImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_singer_img)

        singer_img.onImageClick = {
            onBackPressed()
        }
        singer_img.post {singer_img.setImageSource(R.drawable.chenguanxi)}
    }
}