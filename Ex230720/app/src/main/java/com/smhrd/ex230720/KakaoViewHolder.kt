package com.smhrd.ex230720

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class KakaoViewHolder(var itemView : View) : ViewHolder(itemView) {
    //class KakaoViewHolder extends ViewHolder{
    //      KakaoViewHolder(View itemView){  생성자
    //              super(itemView); 상위 클래스의 생성자 호출
    //              }
    //  }


    var img : ImageView
    var tv_name : TextView
    var tv_msg : TextView
    var tv_time : TextView

    //default 생성자 => 매개변수가 하나도 없는 생성자
    //KakaoViewHolder(){}
    init {
        img = itemView.findViewById(R.id.img)
        tv_msg = itemView.findViewById(R.id.tv_msg)
        tv_name = itemView.findViewById(R.id.tv_name)
        tv_time = itemView.findViewById(R.id.tv_time)

    }
}