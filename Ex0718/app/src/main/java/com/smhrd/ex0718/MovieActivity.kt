package com.smhrd.ex0718

import android.annotation.SuppressLint
import android.graphics.Movie
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

        val dailyBoxOfficeList = ArrayList<MovieVO>()

        //버튼이 클릭되면 영화api를 통해 20230717 영화 순위 데이터 요청 후 응답값(순위, 영화제목, 개봉날짜 -> MovieVO (data class) ) 처리
        mv_btn.setOnClickListener {
            val request = StringRequest(
                Request.Method.GET,
                "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20230718",
                {


                        response ->
                    Log.d("response", response.toString())
                    val result = JSONObject(response).getJSONObject("boxOfficeResult")
                    val movieList = result.getJSONArray("dailyBoxOfficeList")

                    for(i in 0 until movieList.length()){
                        val movie = movieList.get(i) as JSONObject
                        val movieNm = movie.getString("movieNm")
                        val openDt = movie.getString("openDt")
                        val rank = movie.getString("rank")
                        dailyBoxOfficeList.add(MovieVO(rank,openDt,movieNm))

                    }
                    val adapter = ArrayAdapter<MovieVO>(applicationContext, android.R.layout.simple_list_item_1, dailyBoxOfficeList)
                    list_item.adapter = adapter
                },
                {
                        error->
                    Log.d("error", error.toString())
                    Toast.makeText(this, "error 발생", Toast.LENGTH_SHORT).show()
                }
            )
            //여러번 요청할 경우 캐시 누적됨, 이전 결과가 있어도 새로 요청하여 응답을 보여주고 싶은 경우
            request.setShouldCache(false)
            reqQueue.add(request)
                }

        }
        //https://www.kobis.or.kr/kobisopenapi/homepg/main/main.do
        //rank, movieNm, openDt

        //1. 전체 json 데이터 가져오기
        // ** 요청은 한번만 하면됨
        //targetDt = ? 여기만 값 바꿔주면 됨


    }
