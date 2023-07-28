package com.ksy.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksy.fragment.VO.BoardVO

class BoardAdapter(var context : Context, var data : ArrayList<BoardVO>):
    RecyclerView.Adapter<BoardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {

        return BoardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        )

//        var template_View : View = LayoutInflater.from(context).inflate(template, parent, false)
//        var VH : BoardViewHolder = BoardViewHolder(template_View)
//        return VH
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {

        val board = data[position]


//        var img : ImageView = holder.b_img
//        var like : TextView = holder.b_likecnt
//        var BoardMessage : BoardVO = data.get(position)
//        img.setImageResource(BoardMessage.img)
//        like.text = BoardMessage.likecnt.toString()

        holder.b_title.text = board.title
        holder.b_writer.text = board.writer
        holder.b_likecnt.text = board.likecnt.toString()
//        holder.b_title.text = data.get(position).title
//        holder.b_writer.text = data.get(position).writer

    }


}