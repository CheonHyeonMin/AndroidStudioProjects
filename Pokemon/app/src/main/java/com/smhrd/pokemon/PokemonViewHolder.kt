package com.smhrd.pokemon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var ivPokemon : ImageView
    lateinit var tvPokemon : TextView

    init{ //component 초기화
        ivPokemon = itemView.findViewById(R.id.dtpokemon)
        tvPokemon = itemView.findViewById(R.id.tvPokemon)
    }

}
