package com.smhrd.ex20230705

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)// 준비하는 코드
        setContentView(R.layout.activity_main) //xml + kt 연결하는 코드

        // 1. Button 찾기
        val btn : Button = findViewById(R.id.button)
        val btn1 : Button = findViewById(R.id.button2)
        // 2. TextView 찾기
        val tv : TextView = findViewById(R.id.textView)
        // 3. Button 눌렀을 때를 감지(Event)할 Listener달아주기
        btn.setOnClickListener {
//            text.setText("안녕하세요")
            tv.text = "안녕하세요" //public 변수 접근
            tv.setTextColor(Color.parseColor("#FFFF00")) // 메소드로 접근

        }
        btn1.setOnClickListener {
            tv.text="나는 천혀민!"
        }
        // 4. Listener 안에서 TextView의 text 속성 바꾸기!


    }
}