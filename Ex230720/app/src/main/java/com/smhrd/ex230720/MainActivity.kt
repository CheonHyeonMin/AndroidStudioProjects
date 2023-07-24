package com.smhrd.ex230720

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    //ListView의 업그레이드 버전
    // RecyclerView 를 만들어보자!
    // Custom RecyclerView

    //원리
    //  Data의 개수만큼 Template를 복사하여  RecyclerView안에 배치
    // => 이것은 Adapter가 해줌


    // 1. Data(VO, ArryaList)
    // -VO => 사용자(개발자) 정의 자료형!
    // 2. Template(.xml)
    // 3. Adapter
    // - ViewHolder도 만들어야함!

    //크기를 바꿔주기 위해서는 ArrayList를 사용하는게 좋음
    // VO를 담은 ArrayList 설정

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data = ArrayList<KakaoVO>()
        //ArrayList<KakaoVO> data = new ArrayList<KakaoVO>()
        //클래스는 설계하는거고 객체는 생성하는거다

        //add 함수 사용하여 메세지 5개 저장
        //1개의 메세지를 저장하기 위해 새로운 자료형을 설계!
        //RecyclerView : 재활용함 Template에 만들어준 View들을 저장

        val a1 = KakaoVO(R.drawable.img1, "이름1", "안녕","4:10pm")
        val a2 = KakaoVO(R.drawable.img2, "이름2", "안녕~~~","4:13pm")
        val a3 = KakaoVO(R.drawable.img3, "이름3", "좋아요~~","4:15pm")
        val a4 = KakaoVO(R.drawable.img4, "이름4", "싫어요~~~","4:19pm")
        val a5 = KakaoVO(R.drawable.img5, "이름5", "네엥~~~~","4:25pm")
        data.add(a1)
        data.add(a2)
        data.add(a3)
        data.add(a4)
        data.add(a5)

    }
}