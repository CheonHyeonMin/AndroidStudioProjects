package com.smhrd.ex20230711

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer


class MainActivity : AppCompatActivity() {
    // 1. Button 25개 findviewById => 배열, for문
    // - 변수로 생성하면 힘듬, 순서가 없어서 for문도 못돌림
    // 2. 1~25까지 저장된 배열 생성!, 랜덤으로 섞기!
    // 3. Button에 숫자 띄우기!
    // 4. ClickEvent 처리하기
    // - 버튼을 누르면 사라지기!
    // - 순서에 맞는지 확인하기!
        // : 버튼 누를때마다 카운트를 세고 카운트와 버튼에 적혀있는 숫자가 같다면 사라짐!
        // : 25 버튼 눌렀으면 멈춤!
    // - 25버튼 눌렀으면 멈춤!

//    var temp = arrayOf(1,2,3)
      var btns = arrayOfNulls<Button>(25)
      var nums = Array(25){i -> i+1 } //람다식으로 1~25까지 채운 배열 생성!
      var cnt :Int = 1 //내가 눌러야 하는 숫자
      lateinit var time : Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
          setContentView(R.layout.gamelayout) //xml 바꿔주기
          time = findViewById(R.id.tv_time)
        //for(int x= 0; x<=24; x++)

//        for(x in 0 ..24){}

        //2.
        nums.shuffle() //배열 섞임

        for(index in 0 until 25){
            //id명(String)으로 주소값(int) 알아내기
            var id : Int = resources.getIdentifier("btn"+(index+1), "id", packageName)
            btns[index] = findViewById(id)
            // 3.
            btns[index]!!.text = nums[index].toString()
            // !! => null값이 아닐때만 메소드 호출
            btns[index]!!.setOnClickListener {
                time.start()
                //4.
                if(cnt.toString() == btns[index]!!.text){
                    btns[index]!!.visibility = View.INVISIBLE //view를 화면에서 지우는 코드
                    cnt++

                    if(cnt == 26){
                        time.stop()
                    }
                }



            }
        }



        //배열이랑 함쎄 쓰는 for문
        //for(Button data : btns)
//        for((x, data) in btns.withIndex())
//            // x : index
//            // data : 값
//
//        }
    }

}