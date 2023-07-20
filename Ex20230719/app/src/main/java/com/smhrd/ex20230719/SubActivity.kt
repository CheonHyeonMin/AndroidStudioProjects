package com.smhrd.ex20230719

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SubActivity : AppCompatActivity() {
    lateinit var edt_id : EditText
    lateinit var edt_pw : EditText
    lateinit var btn_login : Button
    lateinit var members : HashMap<String, String>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        members = HashMap()
        members.put("cjsgusals", "1111")

        edt_id = findViewById(R.id.edt_id)
        edt_pw = findViewById(R.id.edt_pw)
        btn_login = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            val inputId = edt_id.text.toString()
            val inputPw = edt_pw.text.toString()

            if(members.containsKey(inputId)){
                if(members.get(inputId).equals(inputPw)){
                    intent.putExtra("id",inputId)
                    intent.putExtra("pw",inputPw)

                    setResult(RESULT_OK, intent)
                    // 4. 나는 종료
                    finish()
                }
            }
            else{
                Toast.makeText(this, "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }


        }
    }
}