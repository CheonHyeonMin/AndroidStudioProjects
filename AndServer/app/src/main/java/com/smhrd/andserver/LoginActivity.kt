package com.smhrd.andserver

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    lateinit var edt_id : EditText
    lateinit var edt_pw : EditText
    lateinit var btn_login : Button

    lateinit var reqQueue : RequestQueue
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edt_id = findViewById(R.id.edt_id)
        edt_pw = findViewById(R.id.edt_pw)
        btn_login = findViewById(R.id.btn_login)

        //Gradle Module volley 추가
        reqQueue = Volley.newRequestQueue(this@LoginActivity)



        btn_login.setOnClickListener {
            var input_id = edt_id.text.toString()
            var input_pw = edt_pw.text.toString()


            //object: : 무명객체 생성
            //StringRequest 구현하는 객체 생성
            val request = object: StringRequest(
                Request.Method.POST,
                //manifest.xml ->android:usesCleartextTraffic="true"
                "http://172.30.1.43:8888/login", //http
                {
                        response ->
                    Log.d("response", response.toString())
                    if(response == "Success"){
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                },
                {
                        error ->
                    Log.d("error", error.toString())
                }

            ){
                //Mutable은 인터페이스이기 때문에 HashMap처럼 만들어줌
                override fun getParams() :MutableMap<String, String>{
                    val params:MutableMap<String, String> = HashMap<String, String>()

//                    val amv = AndMemberVO(inputId, inputPw, inputTel, inputBirth)
                    val a = AndMemberVO(input_id, input_pw, null, null)

                    //AndMemberVO(Object) -> JSON 형태로 변환
                    // a가 Object(객체)이기 때문에 문자열로 바꿔줌
                    params.put("AndMember", Gson().toJson(a))
                    return params
                }
            }
            //여러번 요청할때
            request.setShouldCache(false)
            reqQueue.add(request)

        }



    }
}