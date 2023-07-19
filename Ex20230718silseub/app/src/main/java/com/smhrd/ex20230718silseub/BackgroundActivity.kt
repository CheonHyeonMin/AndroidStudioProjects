package com.smhrd.ex20230718silseub

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class BackgroundActivity : AppCompatActivity() {
    lateinit var list_back : ListView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)
        list_back = findViewById(R.id.list_back)

        var colors = arrayOf("RED","GREEN","BLUE")

        var adapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_list_item_1, colors)

        //listView에 adapter 연결~
        list_back.adapter = adapter

        list_back.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            // 1. 데이터 담을 빈 Intent 생성
            var it_result = Intent()
            // 2. Intent에 값 담기(index)
            it_result.putExtra("color", i)
            // 3. 다시 돌려주기
            setResult(RESULT_OK, it_result)
            // 4. 나는 종료
            finish()

        })

    }
}