package com.smhrd.ex20230707img

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    //배열 쓰기
    //R파일에 등록된 리소스는 int 타입 (주소)!
    //정수형 배열 3칸 생성
    // int[] imgs = new int[3]
    // int[] imgs = {1,2,3}


    lateinit var img : ImageView
    lateinit var tv : TextView
    private val imga = listOf(R.drawable.a1,R.drawable.a2, R.drawable.a3)
    var imgs = arrayOf(R.drawable.a1,R.drawable.a2, R.drawable.a3)
    var cnt = 0
    lateinit var list : List<Int>
    var random = Random()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img = findViewById(R.id.imageView)
        tv = findViewById(R.id.tv)
        list = listOf(R.drawable.a1,R.drawable.a2, R.drawable.a3)
//        img.setImageResource(imga[cnt])

        // 1. btnClick 메소드 만들기
        // 2. ImageView 찾기
        // - 이전에는 TextView의 text 속성을 바꿨음. 이번에는 ImageView 의 ImageResource 를 바꿔줌
        // 3. 클릭이 일어난 버튼의 id값에 따라 ImageView의 ImageResource를 바꿔주면 됨
        // -이미지뷰.setImageResource(R.drawable.파일명)
        // 1, 2, 3 버튼 이벤트 구현하기!

        // Lv2
        // 1. R.drawable.파일명 <- 얘네들 배열에 저장
        // 2. 화살표 버튼 누를때마다 Index 증가시키면서 배열에서 이미지 꺼내기

        // Lv3
        // 2번 버튼 누르고 오른쪽 화살표 클릭하면 => 3번사진
        // 1번 버튼 누르고 오른쪽 화살표 클릭하면 => 2번사진



    }

//    fun btn1(v: View){
//        if(v.id == R.id.btn1){
//            img.setImageResource(imga[cnt])
////            img.setImageResource(R.drawable.a1)
//            tv.text = "첫번째 사진"
//        }
//        else if(v.id == R.id.btn2){
//            img.setImageResource(imga[cnt+1])
//            tv.text = "두번째 사진"
//        }
//        else if(v.id == R.id.btn3){
//            img.setImageResource(imga[cnt+2])
//            tv.text = "세번째 사진"
//        }
//    }

    fun swipe(v: View){
        // 알고리즘
        if(v.id == R.id.imageView){
            cnt=random.nextInt(3)
        } else if(v.id == R.id.btn1){
            cnt=0
        } else if(v.id == R.id.btn2){
            cnt=1
        } else if(v.id == R.id.btn3){
            cnt=2
        } else if(v.id == R.id.next_swipe){
            //마지막 사진에서 다음 누르면 첫번째 사진으로 가기
//            cnt++
//            if(cnt>imga.size-1){
//                cnt=0
//            }
            if(cnt<imga.size-1){
                cnt++
            }else{
                Toast.makeText(applicationContext, "마지막 사진입니다.", Toast.LENGTH_SHORT).show()
            }

        } else if(v.id== R.id.previous_swipe){
            //첫번째 사진에서 이전버튼 누르면 마지막 사진으로 가기
//                cnt--
//                if(cnt ==-1){
//                cnt=imga.size -1
//            }

            if(cnt>0){
                cnt--
            }else{

                Toast.makeText(applicationContext, "첫번째 사진입니다.", Toast.LENGTH_SHORT).show()
            }
        }
        //UI 바꾸는 부분
        img.setImageResource(imga[cnt])
    }


}