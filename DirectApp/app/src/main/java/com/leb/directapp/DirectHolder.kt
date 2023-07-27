package com.leb.directapp

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class DirectHolder(itemView : View) : ViewHolder(itemView) {
    var tv_title : TextView
    var tv_address : TextView
    var btn_go : Button

    init {
        tv_title = itemView.findViewById(R.id.tv_title)
        tv_address = itemView.findViewById(R.id.tv_address)
        btn_go = itemView.findViewById(R.id.btn_go)
    }
}