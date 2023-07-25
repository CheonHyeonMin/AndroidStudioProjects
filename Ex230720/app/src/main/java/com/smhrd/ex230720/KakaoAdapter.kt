package com.smhrd.ex230720

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter


class KakaoAdapter(var context : Context, var template : Int, var data : ArrayList<KakaoVO>)
    :Adapter<KakaoViewHolder>(){

        //extends Adapter<KakaoViewHolder>

    //상위클래스인 Adapter 클래스가 추상클래스이기 때문이다.
    // => 추상클래스를 상속받는 하위클래스는 반드시 추상메소드를 오버라이딩(재정의) 해야한다! 오버라이딩을 하지 않을꺼면 KakaoAdapter도 추상 클래스가 되어야 한다.
    // 추상 메서드 :
    //제네릭(Generic)
    // 클래스를 설계하는 시점에는 자료형을 기재하지 않음
    // 자료형은 개발자가 결정함
    // 객체를 생성할 때 자료형을 결정함
    // 상속받고 제네릭 적고 오버라이딩
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoViewHolder {
        //public KakaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)

        //ViewHolder를 생성하는 곳! =>RecyclerView 만들 때 한번만 호출!

        //xml => kt(java) 객체로 만드는 작업 Inflater 라고 부른다.
        //대표적으로 findViewById

        //ViewHolder를 생성할 때 Template.xml을 View타입으로 변환해서 전달

//        Inflate : LayoutInflater 클래스의 Inflate() 메서드를 사용해 View를 생성하는 것

//        Inflater : Layoutflater 클래스의 인스턴스

        var template_View  : View = LayoutInflater.from(context).inflate(template, parent, false)

        var VH : KakaoViewHolder = KakaoViewHolder(template_View)

        return VH

    }

    override fun getItemCount(): Int {
        // public int getItemCount(){}

        //전체 아이템의 개수를 리턴하는 곳!
        // return 5; =>
        return data.size //전체 메세지의 개수

    }

    override fun onBindViewHolder(holder: KakaoViewHolder, position: Int) { //상속 받았을 떄 매개변수 결정됨
        // 이전에 쓰던 ViewHolder에서 View들 꺼내다가 ArrayList에 저장된 데이터들로 꾸미는 곳!
        // 이전에 쓰던 onCreateViewHolder에서 사용한 것들을 재활용해서 사용하기 때문에 onCreateViewHolder는 한번만 실행됨
        // getItemCount에 적힌 숫자만큼 호출!
        var img : ImageView = holder.img
        var tv_msg : TextView = holder.tv_msg

        var KakaoMessage : KakaoVO = data.get(position)

        img.setImageResource(KakaoMessage.imgID)
        tv_msg.text = KakaoMessage.msg


        //ver2.
        holder.tv_name.text = data.get(position).name
        holder.tv_time.text = data.get(position).time

    }


}