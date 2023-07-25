package com.smhrd.pokemon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class DetaillActivity : AppCompatActivity() {
    lateinit var tvPokemonNm : TextView
    lateinit var tvType1 : TextView
    lateinit var tvType2 : TextView
    lateinit var tvHeight : TextView
    lateinit var tvWeight : TextView
    lateinit var imgPokemon : ImageView

    lateinit var reqQueue : RequestQueue

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaill)
        tvPokemonNm = findViewById(R.id.tvPokemonNm)
        tvType1 = findViewById(R.id.tvType1)
        tvType2 = findViewById(R.id.tvType2)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)
        imgPokemon = findViewById(R.id.imgPokemon)

        reqQueue = Volley.newRequestQueue(this@DetaillActivity)

        val name = intent.getStringExtra("pokemonNm")
        val imgPath = intent.getStringExtra("pokemonImgPath")
        val id = intent.getIntExtra("pokemonID",0)

        tvPokemonNm.text = name
        Glide.with(this@DetaillActivity).load(imgPath).into(imgPokemon)

        val url = "https://pokeapi.co/api/v2/pokemon/$id"

        val request = object: StringRequest(
            Request.Method.GET,
            url,
            {
                response ->
                Log.d("response", response.toString())
                val result = JSONObject(response)

                val types = result.getJSONArray("types")
                val type1 = types.getJSONObject(0).getJSONObject("type").getString("name")
                var type2 ="none"
                if(types.length()>1){
                    type2 = types.getJSONObject(1).getJSONObject("type").getString("name")
                }

                val weight = result.getDouble("weight")
                val height = result.getDouble("height")
                tvType1.text= type1
                tvType2.text = type2
                tvHeight.text = height.toString()+"cm"
                tvWeight.text = weight.toString()+"kg"
            },
            {
                error ->
                Log.d("error", error.toString())
                Toast.makeText(this, "error  발생", Toast.LENGTH_SHORT).show()
            }

        ){}
        reqQueue.add(request)
    }
}