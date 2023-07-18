package com.smhrd.ex0718

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {
    lateinit var mv_btn : Button
    lateinit var list_item : ListView
    lateinit var reqQueue : RequestQueue
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)


        //component 가져오기

        mv_btn = findViewById(R.id.mv_btn)
        list_item = findViewById(R.id.list_item)

        //RequestQueue 생성

        reqQueue = Volley.newRequestQueue(this@MovieActivity)

        val dailyBoxOfficeList : ArrayList<MovieVO>()

        //버튼이 클릭되면 영화api를 통해 20230717 영화 순위 데이터 요청 후 응답값(순위, 영화제목, 개봉날짜 -> MovieVO (data class) ) 처리
        mv_btn.setOnClickListener {
            val request = StringRequest(
                Request.Method.GET,
                "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20230717",
                {


                        response ->
//                    Log.d("response", response.toString())
                    val result = JSONObject(response)
                    val weather = result.getJSONArray("weather").get(0) as JSONObject
                    val state = weather.getString("main")

                    val main = result.getJSONObject("main")
                    val temp = main.getInt("temp")
//                        Log.d("Gwangju",state)
//                        Log.d("Gwangju_temp",temp.toString())
                    weatherList.add(WeatherVO(cityList.get(i), state, temp-273)) //데이터 가져옴

                    //view에 data(가지고온 날씨 데이터) 적용하기 위해
                    val adapter = ArrayAdapter<WeatherVO>(applicationContext, android.R.layout.simple_list_item_1, weatherList)
                    list.adapter = adapter
                },
                {
                        error->
                    Log.d("error", error.toString())
                    Toast.makeText(this, "error 발생", Toast.LENGTH_SHORT).show()
                }
                }
            )
        }
        //https://www.kobis.or.kr/kobisopenapi/homepg/main/main.do
        //rank, movieNm, openDt

        //1. 전체 json 데이터 가져오기
        // ** 요청은 한번만 하면됨
        //targetDt = ? 여기만 값 바꿔주면 됨


    }
}