package com.smhrd.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {



    override fun onCreateView( //화면 보이게 해주는 곳
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_1, container, false)

        //WebView Component 가져오기
        var wv: WebView = view.findViewById(R.id.wv)

        //SharedPreferences 생성
        val spf = requireActivity().getSharedPreferences(
            "mySPF",
            Context.MODE_PRIVATE
        )

        //보여주고 싶은 web url 지정
//        val url = "https://smhrd.or.kr/"
        //++ SharedPreference에서 저장된 url 꺼내서 사용
        //                  가지고 오고 싶은 값의key,  기본값
        val url = spf.getString("url", "hhtps://www.google.com")

        //web setting
        val ws  = wv.settings

        //1. javascript 사용 가능하도록 허용
        ws.javaScriptEnabled = true

        // WebView 클라이언트 설정
        wv.webViewClient = WebViewClient()

        //WebView에  url 적용
        //url? -> null 값 허용하겠다
        //url!! -> null 값이 들어오지 않는다.
        wv.loadUrl(url!!)

        return view

    }

}