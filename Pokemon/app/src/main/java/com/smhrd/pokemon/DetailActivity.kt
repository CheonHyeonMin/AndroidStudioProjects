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

class DetailActivity : AppCompatActivity() {
    lateinit var dtpokemon : ImageView
    lateinit var type1 : TextView
    lateinit var type2 : TextView
    lateinit var dtweight : TextView
    lateinit var tvpokemon : TextView
    var pokemonList = ArrayList<String>()

    lateinit var reqQueue : RequestQueue
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        dtpokemon = findViewById(R.id.dtpokemon)
        type1 = findViewById(R.id.type1)
        type2 = findViewById(R.id.type2)
        dtweight = findViewById(R.id.dtweight)
        tvpokemon = findViewById(R.id.tvPokemon)


        reqQueue = Volley.newRequestQueue(this@DetailActivity)

        //component 가져오기 -> 이미지, 텍스트

        //intent -> 이름, 이미지 경로(Glide), id(상세정보 가져올 때 사용)


        val intent =  getIntent()
        var id = intent.getIntExtra("pokemonID", -1)
        Log.d("id", id.toString())



//        val imgUrl = "https:/raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        val imgUrl = intent.getStringExtra("pokemonImgPath")
        val name = intent.getStringExtra("pokemonNm")

        val dataurl = "https://pokeapi.co/api/v2/pokemon/$id"
        var img = Glide.with(this.applicationContext).load(imgUrl).into(dtpokemon)

        val request = object : StringRequest(

            Request.Method.GET,
            dataurl,
            {
                response ->


                val result = JSONObject(response)

//                val pokemon = PokemonVO(id, imgUrl, name)
//                pokemonList.add(pokemon)
                var type = result.getJSONArray("types")
                Log.d("type", type.toString())
                for(index in 0 until type.length()){
                    pokemonList.add(type.getJSONObject(index).getJSONObject("type").getString("name"))
                }
                type1.text = pokemonList.get(0)
                if(pokemonList.size == 2){
                    type2.text= pokemonList.get(1)
                }
                else{
                    type2.text = ""
                }
                dtweight.text = result.getDouble("weight").toString()
                tvpokemon.text = name



//                var name = result.getJSONArray("names").getJSONObject(2).getString("name")
//                dtname.text= intent.getStringExtra("pokemonNm")
//                type1.text = type.getJSONObject(id).getJSONObject("type").getString("name")
//                type2.text = type.getJSONObject(id).getJSONObject("type").getString("slot")
//                dtpokemon = img.view


            },
            {
                error ->
                Log.d("error", error.toString())
                Toast.makeText(this, "error발생", Toast.LENGTH_SHORT).show()

            }

        ){}
        reqQueue.add(request)


        //https://pokeapi.co/api/v2/pokemon/$id

        //response -> JSONObject형태로 바꿔주기 -> result
        //var type = result.getJSONArray("types")
        // type.getJSONObject(index).getJSONObject("type").getString("name)
        //                 index ->0,1 (1번째 없을 수도 있음)

        //result.getDouble("weight")
        //result.getDouble("height")



    }
}