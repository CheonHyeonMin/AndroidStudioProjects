package com.smhrd.ex20230727

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var tv_start1 : TextView
    lateinit var btn_start1 : Button

    lateinit var tv_start2 : TextView
    lateinit var btn_start2 : Button
    lateinit var edt_num : EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_start1= findViewById(R.id.tv_start1)
        btn_start1= findViewById(R.id.btn_start1)

        tv_start2= findViewById(R.id.tv_start2)
        btn_start2= findViewById(R.id.btn_start2)
        edt_num = findViewById(R.id.edt_num)

        //EditText에 적힌 글자 가져오는 코드~

        btn_start1.setOnClickListener {
            //EditText에 적힌 글자 가져오는 코드~
            var th1 : cntThread = cntThread(tv_start1, 15)

            th1.start()
        }

        btn_start2.setOnClickListener {
            //EditText에 적힌 글자 가져오는 코드~
            var th2 = cntThread(tv_start2, edt_num.text.toString().toInt())

            th2.start()
        }

        // Thread는 class로 설계한다!
        //  때문에!!! 객체를 생성해야한다!
        // start()는 준비단계 구현하는 사람이 손댈 필요 없음
        // 구현해야 하는 곳은 run()
    }
    //object : 객체화 시킴 java로는 new와 같은 개념
    var cntHandler : Handler = object : Handler(Looper.getMainLooper()){
        //Handler cntHandler = new Handler(Looper.getMainLooper())
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            //메세지를 받아서 처리하는 곳!
            var tv : TextView = msg.obj as TextView
            tv.text = msg.arg1.toString()




        }
    }
                         //생성자 매개변수
    inner class cntThread(var tv : TextView, var num : Int) : Thread(){
          //public TextView tv;
//        public cntThread(TextView tv){
//            this.tv =tv;
//        }


        //1. Thread 클래스 상속! (extends)
        //2. Runnable 인터페이스 구현! (implements)
        override fun run() {
            // super.메소드이름
            // => 삭제해도 되는 경우 (매개변수 없으면), 안되는 경우(매개변수가 있으면)
            // 1. 메세지 생성!

            for(i in num downTo 1){
                var msg : Message = Message()
                // 2. 데이터 셋팅!
                msg.arg1 = i

                msg.obj = tv
                // 3. 핸들러한테 전송
                cntHandler.sendMessage(msg)
                Thread.sleep(500)
            }

            //Thread는 run메소드가 끝나면 소멸!
        }

    }
}