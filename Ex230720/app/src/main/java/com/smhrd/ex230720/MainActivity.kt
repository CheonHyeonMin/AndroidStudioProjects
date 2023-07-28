package com.smhrd.ex230720

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //ListView의 업그레이드 버전인 RecyclerView중에서도 Custom RecyclerView를 만들어보자!
    //RecyclerView란 ? 스크롤 가능하고 같은 탬플릿이 반복되는 거 ex) 유튜브,인스타그램
    //RecyclerView의 원리 : 데이터의 개수만큼 탬플릿을 복사하여 RecyclerView안에 배치
    //=> 어댑터가 이 일을 해줌
    //필요한 것들
    //1. 데이터 (VO, ArrayList)
    //2. 템플릿 (.xml파일로 만들 것)
    //3. 어댑터(.kt) + View Holder(class)도 만들어야 함

    lateinit var rv : RecyclerView
    lateinit var btn_send : Button
    lateinit var edt_msg : EditText

    //FireBase => 구글 클라우드(ex. dropdox, 네이버클라우드) 서버로 JSON구조
    //클라우드서버? 구글에서 일정량의 저장소와 서버를 구축해두고 안드로이드 개발자에게 제공해주는 서비스
    //목적 : Android 개발자가 서버를 구현하는 번거로움을 해소


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.listView)
        btn_send = findViewById(R.id.btn_send)
        edt_msg = findViewById(R.id.edt_msg)

        //App에 연결되어있는 Firebase DataBase 객체 가져오기
        val database = Firebase.database
        //DaraBase 경로 가져오기
        val myRef = database.getReference("message")
        //해당 경로에 데이터 저장하기
        //myRef.setValue("Hello")


        //ArrayList 객체 생성
        var data = ArrayList<KakaoVO>()

        //add함수 사용하여 메세지 5개 저장하기
//          var vol : KakaoVO = KakaoVO(R.drawable.img1, "팡이", "내 맘대로", "오전 06:00")
//          data.add(vol)
        //myRef : 주소, push() : 임의의 키값(map구조는 키값이 같으면 데이터가 갱신되기 때문에 작성), setValue(저장할 값)
//        myRef.push().setValue(KakaoVO(R.drawable.img1, "민준", "안녕하세요","오후 4:20"))
//        myRef.push().setValue(KakaoVO(R.drawable.img2, "서준", "안녕하요","오후 4:21"))
//        myRef.push().setValue(KakaoVO(R.drawable.img3, "도준", "안녕하","오후 4:23"))
//        myRef.push().setValue(KakaoVO(R.drawable.img4, "영준", "안녕하세","오후 4:25"))
//        myRef.push().setValue(KakaoVO(R.drawable.img5, "이준", "안녕","오후 4:40"))

        //  내가 보낸 채팅 오른쪽에 띄우는 법
        // 1. template.xml팡리에 오른쪽 톡 추가(중요! 같은 파일에 추가할 것!)
        // 2. 현재 로그인한 ID를 생성자로 전달! => 메세지 주인!
        // 3. adapter 클래스의 onBindView 메소드에서 data.get(position).name (메세지 주인)과 생성자로 전달된 id를 비교
        // 4. 일치한다면 왼쪽 뷰들은 전부 gone, 오른쪽 뷰들은 visiable
        // tip! template에 뷰가 추가됐으니 ViewHolder도 수정필요!!!


        var adapter : KakaoAdapter = KakaoAdapter(applicationContext, R.layout.template, data)
        //LayoutManager 세팅
        rv.layoutManager = LinearLayoutManager(applicationContext) //목록형, Grid는 바둑형

        rv.adapter = adapter

        btn_send.setOnClickListener {
            myRef.push().setValue(KakaoVO(R.drawable.img2, "서준", edt_msg.text.toString(), "오후 4:58"))
            //adapter 새로고침
//            adapter.notifyDataSetChanged()
            //스크롤 옮기기
            rv.smoothScrollToPosition(data.size-1) //마지막 위치로
            //EditText 비우기
            edt_msg.setText("") //edt_msg.text.clear()

        }//onClickListener 끝

        //데이터 새로 추가하기
        myRef.addChildEventListener(ChildEvent(data, adapter))

    } //onCreate 끝
} //class 끝