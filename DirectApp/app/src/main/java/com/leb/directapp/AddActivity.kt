package com.leb.directapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {

    lateinit var edt_name : EditText
    lateinit var edt_url : EditText
    lateinit var btn_go : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        edt_name = findViewById(R.id.et_name)
        edt_url = findViewById(R.id.et_url)
        btn_go = findViewById(R.id.btn_go)

        btn_go.setOnClickListener {
            var result = Intent()
            result.putExtra("name", edt_name.text.toString())
            result.putExtra("url", edt_url.text.toString())
            // name과 url이 비어있지 않은지 검사!

            setResult(RESULT_OK, result)
            finish()

        }
    }
}