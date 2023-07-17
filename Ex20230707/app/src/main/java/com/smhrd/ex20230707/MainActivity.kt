package com.smhrd.ex20230707

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //변수 이름 전체 바꿔주기 ALT+SHiFT+R
    lateinit var tvnum: TextView
    var cnt = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvnum = findViewById(R.id.change_Num)
    }

//    fun btn_Plus(v : View){
//        cnt++
//        tvnum.text = cnt.toString()
//    }
//
//    fun btn_Minus(v : View){
//        if(cnt>0){
//            cnt--
//        }
//        tvnum.text = cnt.toString()
//    }

    fun btn_Click(v: View) {
        // 버튼이 늘어날 때마다 메소드를 하나씩 계속 추가해야하나?
        // click메소드 하나로 여러개의 Event를 처리할 수 있음!
        // 매개변수 v => 사용자가 클릭한 View(Button)
        if (v.id == R.id.btn_Plus) {
            cnt++
        } else if (v.id == R.id.btn_Minus) {
            if (cnt > 0) {
                cnt--
                if (cnt == 0) {
                    tvnum.setTextColor(Color.parseColor("#FFFF00"))
                }
            }


        }
        tvnum.text = cnt.toString()
    }
}