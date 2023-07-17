package com.example.ex20230703

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class CalculatoryActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculatory1)

        val btnplus : Button = findViewById(R.id.plus)

        btnplus.setOnClickListener {

            Toast.makeText(this@CalculatoryActivity1,"클릭!", Toast.LENGTH_SHORT).show()

        }
    }

    // ~~타입의 변수 ~~을 생성하고 ~~으로 찾은 ~~~을 저장해라
    // 버튼 타입의 변수 btnplus를 생성하고 id값으로 찾은 View를 저장해라
    //변수
    //생성(선언) : 변수명 앞에 자료형 표기
    //사용(참조) : 변수명 앞에 자료형 표기x

    //int a = 3; => 생성
    //syso(a); => 사용
    //Q1. 같은 이름의 변수 2개이상 생성 가능?
    //int a = 3; =>생성
    // a =5; =>a라는 이름의 변수를 사용

    //Q2. 생성하지 않은 변수 사용가능?
    //syso(c); => 생성하지 않았으니까! 안됨
    // inc = 10;

}