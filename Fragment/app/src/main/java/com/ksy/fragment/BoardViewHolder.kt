package com.ksy.fragment

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BoardViewHolder(var itemView : View) : RecyclerView.ViewHolder(itemView) {

    var b_title : TextView
    var b_writer : TextView
//    var b_img : ImageView
    var b_likecnt : TextView

    init{
        b_title = itemView.findViewById(R.id.b_title)
        b_writer = itemView.findViewById(R.id.b_writer)
//        b_img = itemView.findViewById(R.id.b_img)
        b_likecnt = itemView.findViewById(R.id.b_likecnt)
    }
}