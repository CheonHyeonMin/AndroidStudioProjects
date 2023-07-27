package com.smhrd.fragment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bnv: BottomNavigationView
    lateinit var fl : FrameLayout
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv = findViewById(R.id.bnv)
        fl = findViewById(R.id.fl)

        //bnv에서 선택한 메뉴에 따라 fl1에 표시할 fragment를 갈아 끼우기!
        bnv.setOnItemSelectedListener {
            Log.d("id", it.itemId.toString())

            when(it.itemId){
                R.id.tab1 -> {
                    //supportFragmentManager 활용 transaction 생성
                    //transaction을 통해 프래그먼트 교체 -> commit(완료)
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
                    ).commit()
                }
                R.id.tab2 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()
                }
                R.id.tab3 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }

            }

            //boolean : true(이벤트 인식이 더 좋음!) /false(이벤트인식)
            true
        }

    }
}