package com.smhrd.ex20230719

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
   lateinit var mbtn_login : Button
   lateinit var btn_write : Button
   lateinit var tv_nick : TextView
   lateinit var listView : ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mbtn_login= findViewById(R.id.mbtn_login)
        btn_write = findViewById(R.id.btn_write)
        tv_nick = findViewById(R.id.tv_nick)
        listView = findViewById(R.id.listView)

        //listView 만들때!
        // 1. 데이터(배열/ ArrayList)
        // 2. 템플릿(.xml 파일 만들어서 디자인 함)
        // 3. 어댑터(.kt : BaseAdapter 상속)


        var data = arrayOf("1.Android 짱 재밌음", "2.아님 어려움", "3.그래도 재밌음!!", "4.이디오 겁나 귀여움")
                                                //화면 정보                     템플릿                 데이터
        var adapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_list_item_1, data)

        listView.adapter = adapter

        var MyIntent : Intent = intent
        var ID : String? = MyIntent.getStringExtra("id")

//        var frLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            if(it.resultCode == RESULT_OK){
//                var result : Int = it.data!!.getIntExtra("color", -1)
//                tv_nick.text = ID+ "님 환영합니다."
//            }
//        }

        var frLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                val data: Intent? = it.data
                val id: String? = data?.getStringExtra("id")

                if (id != null) {
                    tv_nick.text = "Welcome to $id"
                    tv_nick.visibility = View.VISIBLE
                } else {
                    tv_nick.visibility = View.INVISIBLE
                }
            }
        }

//        tv_nick.text = ID+ "님 환영합니다."
        mbtn_login.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)

            frLauncher.launch(intent)
        }
//        if(tv_nick.text == null){
//            tv_nick.visibility = View.VISIBLE
//        }
//        else{
//            tv_nick.text = ID + "님 환영합니다."
//        }


    }
}