package com.smhrd.andserver

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var edt_id : EditText
    lateinit var edt_pw : EditText
    lateinit var edt_tel : EditText
    lateinit var edt_birth : EditText
    lateinit var btn_join : Button

    lateinit var reqQueue : RequestQueue

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edt_id = findViewById(R.id.edt_id)
        edt_pw = findViewById(R.id.edt_pw)
        edt_tel = findViewById(R.id.edt_tel)
        edt_birth = findViewById(R.id.edt_birth)
        btn_join = findViewById(R.id.btn_join)

        reqQueue = Volley.newRequestQueue(this@MainActivity)

        //버튼을 클릭하면 사용자가 작성한 값 가져오기 -> NODE 보내기 -> 응답(가입이 성공/실패)
        btn_join.setOnClickListener {
            val inputId = edt_id.text.toString()
            val inputPw = edt_pw.text.toString()
            val inputTel = edt_tel.text.toString()
            val inputDate = edt_birth.text.toString()

            //object: : 무명객체 생성
            //StringRequest 구현하는 객체 생성
            val request = object:StringRequest(
                Request.Method.POST,
                //manifest.xml ->android:usesCleartextTraffic="true"
                "http://172.30.1.43:8888/join", //http
                {
                    response ->
                    Log.d("response", response.toString())
                },
                {
                    error ->
                    Log.d("error", error.toString())
                }

            ){
                override fun getParams() :MutableMap<String, String>{
                    val params:MutableMap<String, String> = HashMap<String, String>()

                    val amv = AndMemberVO(inputId, inputPw, inputTel, inputDate)

                    //AndMemberVO(Object) -> JSON 형태로 변환

                    params.put("AndMember", Gson().toJson(amv))
                    return params
                }
            }
            reqQueue.add(request)

        }


    }
}