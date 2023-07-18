package com.smhrd.ex20230712

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var iv_kakaopage : ImageView
    lateinit var tv_google : TextView
    lateinit var tv_sns : TextView
    lateinit var tv_call : TextView
    lateinit var btn_login : Button
    lateinit var edt_email : EditText
    lateinit var edt_pw : EditText
    lateinit var members : HashMap<String, String>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //members = new HashMap(); 똑같음
        members = HashMap()
        members.put("cjsgusals", "1111")
        members.put("cjsgusals1", "1112")
        members.put("cjsgusals2", "1113")
        members.put("cjsgusals3", "1114")
        members.put("cjsgusals4", "1115")



        iv_kakaopage=findViewById(R.id.iv_kakaopage)
        tv_google = findViewById(R.id.tv_google)
        tv_sns = findViewById(R.id.tv_sns)
        tv_call = findViewById(R.id.tv_call)
        btn_login = findViewById(R.id.btn_login)
        edt_email = findViewById(R.id.edt_email)
        edt_pw = findViewById(R.id.edt_pw)



        iv_kakaopage.setOnClickListener{
            //Intent!!
            //카카오 웹페이지 이동~
            var intent : Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kakaocorp.com/page/"))
            // Intent intent = new Intent() 위 아래 똑같음 아래는 자바 코드
            startActivity(intent)
//            val intent1 = Intent(this, MainActivity2::class.java)
        }

        tv_google.setOnClickListener{
            // 1. Intent 생성
            // 2. putExtra 함수 사용, 데이터 담기
            // 3. Intent 실행!

            var intent : Intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, "아구찜")
            startActivity(intent)
        }
        tv_sns.setOnClickListener {
            //문자보내는 페이지까지 이동
            // 실제로 문자 보내야된다... -> 문자보내는 업체 사용해야함
            // 1. Intent 생성

            // 2. putExtra 함수 사용 문자 내용 작성
            // 3. Intent 실행
            var intent : Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010-7132-8359"))
            intent.putExtra("sms_body", "안녕")
            startActivity(intent)

        }

        tv_call.setOnClickListener {
            // 1. Intent  생성 (Action, Data)
            // 2. Intent 실행


            //android에는 예민한 기능임
            //권한요청을 해서 사용자가 허용을 해야 수행할 수 있도록 Android가 설계해놨음!

            // 1. 이미 권한이 승인하지 않았는지 검사!
            // 2. 승인하지 않았다면 다이얼로그 띄우기!

            // 3. Manifest.xml 파일 열어서 permission 태그 추가!


            if(ActivityCompat.checkSelfPermission(applicationContext, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),0)

            }

            val intent : Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:010-7132-8359"))
            startActivity(intent)

        }

        btn_login.setOnClickListener {
            //다른 Activity로 이동~
            var intent : Intent = Intent(this, SubActivity::class.java)
            //edt.text까지만 하면 charSequence(문자열) 타입인데 equals로 비교할려면 String으로 바꿔줘야되기 때문에 det.text 뒤에 .toString()을 붙여줌

            //1. id가 존재하는지
            var inputId = edt_email.text.toString()
            var inputPw = edt_pw.text.toString()

            if(members.containsKey(inputId)){ //해당되는 key값이 있으면 true, 없으면 false
                if(members.get(inputId).equals(inputPw)){
                    //members에 저장된 값 중 inputId를 키값으로 하는 데이터가
                    //inputPw와 같으면 true 다르면 false

                    intent.putExtra("email",edt_email.text.toString())
                    intent.putExtra("password", edt_pw.text.toString())
                    startActivity(intent)
                }
            }
            else{
                Toast.makeText(applicationContext, "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }



        }

    }



//    fun iv_click(v: View){
//        if(v.id == R.id.iv_kakaopage){
//            val intent = Intent(this,MainActivity2::class.java)
//            startActivity(intent)
//        }
//
//    }
}