package com.smhrd.ex20230712

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class SubActivity : AppCompatActivity() {
    lateinit var btn_main : Button
    lateinit var listView : ListView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        btn_main = findViewById(R.id.btn_main)
        listView = findViewById(R.id.listView)

        // 1. Data
        // 2. 템플릿 하나를 디자인 하는 것을 template design ListView
        // - 템플릿 (Android 에서 제공해주는 템플릿)
        // 3. Adapter ( Data + 템플릿) - Android에서 제공해주는 Adapter

        var foods = arrayOf("참치회", "아구찜", "파지감자탕", "오돌뼈", "쏘맥")

        var adapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_list_item_1, foods)

        //listView에 adapter 연결~
        listView.adapter = adapter

        btn_main.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
              finish()
        }

    }
}