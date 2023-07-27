package com.smhrd.ex230720

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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
    // 3. Adapter(.kt)
    // - ViewHolder(.kt) 도 만들어야함!





    //크기를 바꿔주기 위해서는 ArrayList를 사용하는게 좋음
    // VO를 담은 ArrayList 설정

    lateinit var rv : RecyclerView
    lateinit var btn_send : Button
    lateinit var edt_msg : EditText

    //FireBase -> 구글 클라우드 서버
    // 클라우드(dropbox, 네이버 클라우드) 서버?
    // 구글에서 일정량의 저장소와 서버를 구축해두고 Android 개발자에게 제공!
    // 목적 : Android 개발자가 서버를 구현하는 번거로움을 해소!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.listView)
        btn_send = findViewById(R.id.btn_send)
        edt_msg = findViewById(R.id.edt_msg)

        //App에 연결되어있는 FireBase Database  객체 가져오기
        val database = Firebase.database

        // DataBase 경로 가져오기
        val myRef = database.getReference("message")

        //해당 경로에 데이터 저장하기




        var data = ArrayList<KakaoVO>()
        //ArrayList<KakaoVO> data = new ArrayList<KakaoVO>()
        //클래스는 설계하는거고 객체는 생성하는거다

        //add 함수 사용하여 메세지 5개 저장
        //1개의 메세지를 저장하기 위해 새로운 자료형을 설계!
        //RecyclerView : 재활용함 Template에 만들어준 View들을 저장

//        val a1 = KakaoVO(R.drawable.img1, "이름1", "안녕","4:10pm")
//        data.add(a2)

        myRef.push().setValue(KakaoVO(R.drawable.img1, "이름1", "안녕","4:10pm"))
        myRef.push().setValue(KakaoVO(R.drawable.img2, "이름2", "안녕~~~","4:13pm"))
        myRef.push().setValue(KakaoVO(R.drawable.img3, "이름3", "좋아요~~","4:15pm"))
        myRef.push().setValue(KakaoVO(R.drawable.img4, "이름4", "싫어요~~~","4:19pm"))
        myRef.push().setValue(KakaoVO(R.drawable.img4, "이름4", "싫어요~~~","4:19pm"))
        myRef.push().setValue(KakaoVO(R.drawable.img5, "이름5", "네엥~~~~","4:25pm"))


        var adapter : KakaoAdapter = KakaoAdapter(applicationContext, R.layout.template, data)

        // ***
        rv.layoutManager = LinearLayoutManager(applicationContext) //목록형
        rv.adapter = adapter

        btn_send.setOnClickListener {
            data.add(KakaoVO(R.drawable.img2, "이름2", edt_msg.text.toString(), "오후 16:38"))

            //adapter 새로고침
            adapter.notifyDataSetChanged()

            //스크롤 옮기기
            rv.smoothScrollToPosition(data.size-1)

            //EditText 비우기
            edt_msg.text.clear()

        }






    }
}