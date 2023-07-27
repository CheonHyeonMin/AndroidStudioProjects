package com.smhrd.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class Fragment2 : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // setContentView랑 똑같음
        val view = inflater.inflate(R.layout.fragment_2, container, false)

        //component 가져오기
        var etUrl : EditText = view.findViewById(R.id.etUrl)
        var btnUrl : Button = view.findViewById(R.id.btnUrl)
        //버튼을 클릭하면 사용자가 작성한 url 값을 가져오기
        btnUrl.setOnClickListener {
            val url = etUrl.text.toString()

            val spf = requireActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE)
            //MODE_PRIVATE : 내부 캐시에 저장 -> 노출 x
            // - editor 사용
            val editor = spf.edit()
            editor.putString("url", url)
            editor.commit()

        }

        //url 값 저장 (SharedPreference -> 내부 메모리)


        return view
    }



}