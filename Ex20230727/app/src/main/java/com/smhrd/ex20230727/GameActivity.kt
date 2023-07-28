package com.smhrd.ex20230727

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import java.util.Random

class GameActivity : AppCompatActivity() {

    var imgs = arrayOfNulls<ImageView>(9) // ImageView 타입의 배열 9칸 생성하고 null로 초기화
    lateinit var tv_score : TextView
    lateinit var tv_time : TextView
    var threads = arrayOfNulls<dodoThread>(9) //Thread 객체 9개 저장


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        tv_score = findViewById(R.id.tv_score)
        tv_time = findViewById(R.id.tv_time)
        var score = 0

        //객체 생성
        cntThread(tv_time, 10).start()
        //new cntThread().start()

        for(index in 0 until 9){ //0~8까지
            // "imageView 1~9까지 id를 가진 view 주소 알아내는 코드
            var id = resources.getIdentifier("imageView"+(index+1), "id", packageName)
            imgs[index] = findViewById(id) // 9개의 ImageView for문 돌면서 다 찾아옴!
            imgs[index]!!.tag = "off"


            //배열에 생성된 Thread 누적~
            threads[index] = dodoThread(imgs[index]!!)
            threads[index]!!.start()

            //Thread 종료시키는 방법
            // 1. interrupt를 발생시킨다.
            // 2. Thread내부에서는 interrupt Exception이 발생!
            // 3. 이걸 이용해서! try-catch 을 활용하여 run 메소드를 종료 =>


            // 두더지 클릭했을 때 on ? off냐 판단 R.drawable.__주소로 이미지 변경했음
            // android에서 이미지끼리 비교하는 방법은 Drawable이라는 객체로 변경 후 이미지처리
            // 일이 커짐 => View에 tag라는 변수가 있음 tag를 활용하기
            imgs[index]!!.setOnClickListener {
                if(imgs[index]!!.tag.toString() == "on") { //두더지에 "on" 태그가 걸려있다면
                    score++
                }
                else{
                    score--
                }
                tv_score.text = score.toString()
                imgs[index]!!.tag = "off"
            }


        }

        //객체 생성될 때 ImageView를 전달받는 Thread를 설계해주세요!
        //알고리즘 => run메서드 안에서 만들어야됨
        //Handler 생성 후 메세지 받아서 처리할 메소드 오버로딩

                                                    //줄이 그어지는 이유 : 기본 생성자는 되도록 쓰지 마라.

        }
    var dodoHandler : Handler = object : Handler(Looper.getMainLooper()){
        //이 안은 오버라이딩을 위한 구간
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            //업캐스팅 되어있는 상태에서 하위클래스로 형변환
            //Resource는 주소로 저장
            (msg.obj as ImageView).setImageResource(msg.arg1)
            (msg.obj as ImageView).tag = if(msg.arg1 == R.drawable.on) "on" else "off"
        }

    }


        inner class dodoThread(var dodo: ImageView) : Thread(){

            override fun run() {

                try{
                    while(true){
                        //두더지마다 랜덤한 offTime 부여
                        var offTime = Random().nextInt(5000) + 500 // 0.5 ~ 5.5 사이
                        Thread.sleep(offTime.toLong())

                        // 올라가는 이미지로 변경!
                        var msg = Message()
                        msg.obj = dodo //ImageView 타입의 객체가 Object 타입 변수에 담김 => 형변환됨
                        msg.arg1 = R.drawable.on
                        dodoHandler.sendMessage(msg)

                        var onTime = Random().nextInt(1000) + 500 //0.5~1.5 사이
                        Thread.sleep(onTime.toLong())

                        //내려가는 이미지로 변경!
                        // Message객체는 일회성 => 한번 보낸 메세지 객체는 재사용이 불가능
                        msg = Message()
                        msg.obj = dodo
                        msg.arg1 = R.drawable.off
                        dodoHandler.sendMessage(msg)
                    }
                }catch(e : InterruptedException){
                    return //메소드 종료!
                    // 메소드는 return 키워드를 만나는 그 즉시 0호출한 곳으로 돌아간다!

                }






            }
        }

    var cntHandler : Handler = object : Handler(Looper.getMainLooper()){
        //Handler cntHandler = new Handler(Looper.getMainLooper())
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            //메세지를 받아서 처리하는 곳!
            var tv : TextView = msg.obj as TextView
            tv.text = msg.arg1.toString()

            //Thread 중지
            if(msg.arg1 == 1){
                for(temp in threads){
                    temp!!.interrupt()
                }
            }




        }
    }

    inner class cntThread(var tv : TextView, var num : Int) : Thread(){
        //public TextView tv;
//        public cntThread(TextView tv){
//            this.tv =tv;
//        }


        //1. Thread 클래스 상속! (extends)
        //2. Runnable 인터페이스 구현! (implements)
        override fun run() {
            // super.메소드이름
            // => 삭제해도 되는 경우 (매개변수 없으면), 안되는 경우(매개변수가 있으면)
            // 1. 메세지 생성!

            for(i in num downTo 1){
                var msg : Message = Message()
                // 2. 데이터 셋팅!
                msg.arg1 = i

                msg.obj = tv
                // 3. 핸들러한테 전송
                cntHandler.sendMessage(msg)
                Thread.sleep(500)
            }

            //Thread는 run메소드가 끝나면 소멸!
        }

    }



}