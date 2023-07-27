package com.leb.directapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

class DirectAdapter(var context : Context, var template : Int, var data : ArrayList<VO>) : Adapter<DirectHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectHolder {
        return DirectHolder(
            LayoutInflater.from(context).inflate(template, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DirectHolder, position: Int) {
        holder.tv_title.text = data.get(position).title
        holder.tv_address.text = data[position].url
        holder.btn_go.setOnClickListener {
            var it = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].url))
            // Activity가 아닌 곳에서 Intent를 실행시키고 싶다?
            // 새로운 테스크를 생성해야함!
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // MainActivity화면 정보를 생성자의 context 변수로 전달받았음!
            context.startActivity(it!!)
        }

    }
}











