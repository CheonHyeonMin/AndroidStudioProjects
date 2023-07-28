package com.ksy.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ksy.fragment.VO.BoardVO
import org.json.JSONArray


class Fragment3 : Fragment() {


    lateinit var rc:RecyclerView

    lateinit var btnWriteAct : Button
    lateinit var reqQueue : RequestQueue


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_3, container, false)

//        var btnWriteAct = view.findViewById<Button>(R.id.btnWriteAct)
//        var rcBoard = view.findViewById<RecyclerView>(R.id.rcBoard)
        btnWriteAct = view.findViewById(R.id.btnWriteAct)

        btnWriteAct.setOnClickListener {
            val intent = Intent(requireActivity(), BoardWriteActivity::class.java)
            startActivity(intent)
        }


        rc = view.findViewById(R.id.rcBoard)

        reqQueue = Volley.newRequestQueue(requireActivity())

        val data = ArrayList<BoardVO>()

        val request = object:StringRequest(
            Request.Method.GET,
            "http://172.30.1.43:8888/board/",
            {
                response ->
                Log.d("response", response.toString())

                val result = JSONArray(response)

                for(i in 0 until result.length()){
                    val board = Gson().fromJson(result.get(i).toString(), BoardVO::class.java)
                    data.add(board)

                }

                val adapter = BoardAdapter(requireActivity(), data)

                rc.layoutManager = LinearLayoutManager(view.context)
                rc.adapter = adapter
            },
            {
                error ->
                Log.d("error", error.toString())
            }
        ){}

        reqQueue.add(request)


//        data.add(BoardVO(null, "제목1", "좋아", "작성자작성자작성자1", null, 10))
//        data.add(BoardVO(null, "제목2", "좋아2", "작성자작성자작성자2", null, 20))
//        data.add(BoardVO(null, "제목3", "좋아3", "작성자작성자작성자3", null, 30))




        return view


    }



}