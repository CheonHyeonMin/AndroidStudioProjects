package com.leb.directapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var data = ArrayList<VO>()
    lateinit var rv : RecyclerView
    lateinit var btn_add : Button
    lateinit var adapter : DirectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)
        btn_add = findViewById(R.id.btn_add)


        data.add(VO("네이버", "https://www.naver.com/"))
        data.add(VO("유튜브", "https://www.youtube.com/"))
        data.add(VO("이디오 인스타", "https://www.instagram.com/dio.lee_0122/"))

        var adapter = DirectAdapter(applicationContext, R.layout.template, data)
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.adapter = adapter

        btn_add.setOnClickListener {
            var it : Intent  = Intent(this, AddActivity::class.java)
            launcher.launch(it)

        }

    }

    var launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            var title = it.data!!.getStringExtra("title")
            var url = it.data!!.getStringArrayExtra("url")

            data.add(VO(title!!, url!!.toString()))
            adapter.notifyDataSetChanged()
        }
    }

}






