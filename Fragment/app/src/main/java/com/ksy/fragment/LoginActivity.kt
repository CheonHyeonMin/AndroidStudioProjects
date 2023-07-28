package com.ksy.fragment

import android.content.Context
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
import com.ksy.fragment.VO.AndMemberVO

import org.json.JSONArray

class LoginActivity : AppCompatActivity() {

    lateinit var etId: EditText
    lateinit var etPw: EditText
    lateinit var btnLogin: Button

    lateinit var reqQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etId = findViewById(R.id.etId)
        etPw = findViewById(R.id.etPw)
        btnLogin = findViewById(R.id.btnLogin)

        reqQueue = Volley.newRequestQueue(this@LoginActivity)

        //SharedPreference 생성
        val spf = getSharedPreferences("mySPF", Context.MODE_PRIVATE)


        val test = spf.getString("member", "")
        Log.d("memberState", test.toString())

        // btnLogin을 클릭하면 id, pw 가져오기
        // Volley 사용 : node 서버로 요청 (StringRequest)
        // (HTTP Method(POST), URL(http://ip주소:8888/member/login)) -> http manifest 설정
        // 성공, 오류
        // -> 바디에 데이터 추가 (메서드를 오버라이딩)
        btnLogin.setOnClickListener {
            var inputId = etId.text.toString()
            var inputPw = etPw.text.toString()

            val request = object : StringRequest(
                Request.Method.POST,
                "http://172.30.1.43:8888/member/login",
                { response ->
                    Log.d("response", response.toString())

                    if (response == "Fail") {
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                    } else {
                        val result = JSONArray(response)
                        val member = result.getJSONObject(0)


                        //editor 생성
                        val editor = spf.edit()
                        //editor 를 통해서 현재 로그인 한 회원의 정보를 저장!
                        editor.putString("member", member.toString())
                        editor.commit() //추가해준 사항 반영
                        //완료 되면 메인 액티비티로 전환
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                },
                { error ->
                    Log.d("error", error.toString())
                }
            ) {
                override fun getParams(): MutableMap<String, String>? {
                    val params: MutableMap<String, String> = HashMap<String, String>()

                    val am = AndMemberVO(inputId, inputPw, null, null)

                    params.put("AndMember", Gson().toJson(am))

                    return params
                }
            }
            reqQueue.add(request)
        }

    }
}