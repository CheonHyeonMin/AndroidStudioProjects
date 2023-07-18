package com.smhrd.ex0718

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

class WeatherActivity : AppCompatActivity() {

    lateinit var list: ListView
    lateinit var btn: Button

    lateinit var reqQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        //component

        list = findViewById(R.id.list)
        btn = findViewById(R.id.btn)

        //RequestQueue 생성
        reqQueue = Volley.newRequestQueue(this@WeatherActivity)

        //날씨 데이터 가지고 오고 싶은 도시 정의(-> 배열)
        val cityList : Array<String> = arrayOf("Gwangju", "Seoul", "Jeju-do", "London", "New York")

        val weatherList = ArrayList<WeatherVO>()

        //버튼이 클릭되면 OpenWeatherAPI를 통해서 현재 날씨 데이터를 요청 (도시이름 배열에 저장) 응답 값 처리
        btn.setOnClickListener{

            for(i in 0 until cityList.size){
                val request = StringRequest(
                    Request.Method.GET,
                    "https://api.openweathermap.org/data/2.5/weather?q=${cityList.get(i)}&appid=af637194cfd429c209a48b97e8e12eee",
                    {
                           response ->
                        Log.d("response", response.toString())
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
                )

                reqQueue.add(request)
            }

        }
    }
}