package com.smhrd.ex20230706

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")


    // field변수 = 전역변수 = 클래스 변수 = 멤버 변수
    lateinit var btn : Button
    lateinit var tv : TextView
    var check = true
    lateinit var btn_change :Button
    lateinit var edit_text :EditText

    //field에서는 변수 생성만 하고 setContentView 밑에서 변수 값 저장

    //        var cnt  :Boolean = true
    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //이미 생성된 변수에 find한 값 저장
        btn = findViewById(R.id.btn_click)

        //1. findView를 하면 ~~에서 찾는다
        // 2. 그래서 ~~ 하기 전에 find하면 찾을 수 없다.
        // 3. 그렇기 때문에 반드시 ~~~아래에 ~~~를 적어야 한다.



        btn.setOnClickListener {
//            if(tv.text.equals("Hello")){
//                tv.setText("안녕")
//            }
//            else{
//                tv.setText("Hello")
//            }

            if(check){
                tv.setText("안녕")
                check = false
            }
            else if(!check){
                tv.setText("Hello")
                check = true
            }

//              if(count %2==1){
//                  tv.setText("안녕")
//                  count++
//              }
//            else if(count %2 == 0){
//                tv.setText("Hello")
//                  count++
//              }

            //Toggle Event 실습
            var btn1 : Button = findViewById(R.id.btn_click)
            var tv1 : TextView = findViewById(R.id.tv_result)

            //xml에서 Hello! 라고 정확하게 적어놓지 않으면 click 안에 있는 if문은 무쓸모... 혹은 좀 이상해짐

//            btn1.setOnClickListener {
//                //1) 버튼 눌렀을 때 textView에 뭐라고 적혀있는지 검사!
//                if(tv1.text.equals("Hello")){
//                    tv1.text = "안녕!"
//                }
//                else{
//                    tv1.text="Hello"
//                }
//
//            }
        }




//        btn_change.setOnClickListener {
//            tv.text = edit_text.text
//            edit_text.text.clear()
//        } //도전 xml에서 Event 처리하는 코드로 고치기! (샘이랑 같이 함)
        //❤ xml로 Event 처리하는 방법
        // 1. 버튼을 클릭했을 때 실행될 메소드 정의 (kt)
        //  - 매개변수르 반드시 View 타입으로 생성 (kt)
        // 2. xml 파일을 열어서 버튼 선택 후 onClick 속성에 메소드 연결 (xml)

    }
    // public void btnClick(View currentClick){
    // }

    fun btnClick(currentClick : View){
        tv.text = edit_text.text
        edit_text.text.clear()
    }
}