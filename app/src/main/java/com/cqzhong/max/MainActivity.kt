package com.cqzhong.max

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.clarkz.util.DataCacheUtil
import com.clarkz.util.MathUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showCacheView = findViewById<TextView>(R.id.tvCache)
        showCacheView.text = DataCacheUtil.getTotalCacheShow(this)
    }
}