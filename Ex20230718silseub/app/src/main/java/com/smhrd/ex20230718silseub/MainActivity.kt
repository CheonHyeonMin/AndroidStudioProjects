package com.smhrd.ex20230718silseub

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var btn_back : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_back = findViewById(R.id.btn_back)


        btn_back.setOnClickListener {
            val intent = Intent(this, BackgroundActivity::class.java)
                // startActivity => 편도
                // ForResultLauncher => 왕복
            frLauncher.launch(intent)

        }
    }

    var frLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        // 메소드가 다 호출되고 나면 Launcher가 리턴됨
        // 중괄호 안은 Launcher 영역 안
        // it : 갔다가 돌아왔을 때 담아준 데이터!

        // 1. 이상없이 돌아왔는지
        if(it.resultCode == RESULT_OK){
            var result : Int = it.data!!.getIntExtra("color", -1)
        }
        // 2. 담아준 데이터가 있는지

    }

}