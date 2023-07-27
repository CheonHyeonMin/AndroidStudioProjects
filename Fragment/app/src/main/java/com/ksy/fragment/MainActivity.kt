package com.ksy.fragment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.ksy.fragment.VO.AndMemberVO


class MainActivity : AppCompatActivity() {

    lateinit var bnv: BottomNavigationView
    lateinit var fl: FrameLayout
    lateinit var tvId: TextView
    lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv = findViewById(R.id.bnv)
        fl = findViewById(R.id.fl)
        tvId = findViewById(R.id.tvId)
        btnLogout = findViewById(R.id.btnLogout)



        //SharedPreference 생성(name : mySPF)
        val spf = getSharedPreferences("mySPF", Context.MODE_PRIVATE)
        //"member" 값 가져오기 -> json 형태의 문자열
        val strMember = spf.getString("member", "")
        //json 문자열을 AndMemberVO 타입으로 변환 (Gson 사용)
        val memberVO = Gson().fromJson(strMember, AndMemberVO::class.java)
        //VO에서 id만 꺼내서 textView text 속성으로 넣어주기
        tvId.text = memberVO.id + "님 환영합니다💖"

        //btnLogOut에 클릭리스너
        // -> spf 저장된 값 삭제
        // -> LoginActivity로 전환(인텐트)
        btnLogout.setOnClickListener {
            val editor = spf.edit()
            editor.remove("member")
            editor.commit()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }


        // MainActivity로 전환되지마자 프래그먼트 1번으로 바꾸기
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            Fragment1()
        ).commit()

        // bnv에서 선택한 메뉴에 따라 fl에 표시할 Fragment를 갈아 끼우기
        bnv.setOnItemSelectedListener {
            Log.d("id", it.itemId.toString())

            when (it.itemId) {
                // supportFragmentManager 활용 transaction 생성
                // transaction을 통해 프래그먼트 교체 -> commit(완료)
                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()
                }

                R.id.tab3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }

                R.id.tab4 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
            }
            // boolean - true(이벤트 인식이 더 좋음!) / false
            true
        }
    }
}