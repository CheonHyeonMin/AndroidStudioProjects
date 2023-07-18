package com.smhrd.ex0718

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var etUrl: EditText
    lateinit var btnUrl:Button
    lateinit var tvResult:TextView

    lateinit var reqQueue:RequestQueue

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //컴포넌트 가져오기
        etUrl = findViewById(R.id.etUrl)
        btnUrl = findViewById(R.id.btnUrl)
        tvResult = findViewById(R.id.tvResult)

        //reqQueue생성
        //                      현재 activicty를 context로
        reqQueue = Volley.newRequestQueue(this)

        //request생성 : 사용자가 주소 입력 후 요청 버튼을 눌렀을 때
        //버튼이 클릭되면 etUrl에 작성된 주소를 가져와서 요청 -> 응답오는 값 출력
        btnUrl.setOnClickListener {
            //etUrl에 작성된 텍스트 가져오기 -> 문자열 변환
            val input = etUrl.text.toString()
            //요청
            //request객체 생성
            //StringRequest : String 타입의 응답값을 받을 때 사용하는 객체
            //                  단점 : 대용량 네트워크 처리 불가
            val request = StringRequest(
                Request.Method.GET, //요청메서드(GET/POST)
                input, //요청 주소 https://www.google.com
                {   //요청과 응답이 성공했을 때 (200)
                    //response -> 서버가 응답해준 결과값
                        response ->
                    Log.d("response", response.toString())
                    tvResult.text = response
                },
                {   //통신 실패
                    //error -> 발생한 오류에 대한 정보를 가지고 있음
                        error ->
                    Log.d("error", error.toString())
                    Toast.makeText(this, "error 발생", Toast.LENGTH_SHORT).show()
                }
            )

            //생성된 request 객체 -> request Queue에 추가
            // => 쓰레드가 생성되어 서버로 요청, 응답받기 가능
            reqQueue.add(request)
        }
    }
}