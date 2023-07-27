package com.leb.directapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var data = ArrayList<VO>()
    lateinit var rv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)

        data.add(VO("네이버", "https://www.naver.com/"))
        data.add(VO("유튜브", "https://www.youtube.com/"))
        data.add(VO("이디오 인스타", "https://www.instagram.com/dio.lee_0122/"))

        var adapter = DirectAdapter(applicationContext, R.layout.template, data)
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.adapter = adapter

    }
}






