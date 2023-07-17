package com.example.ex20230703

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class CalculatorActivity : AppCompatActivity() {

//    private lateinit var num1 : EditText
//    private lateinit var num2 : EditText
//    private lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View를 세팅하기 이전에 findViewById를 하는건 불가능
        //NPE가 발생한다!
        setContentView(R.layout.activity_calculator) //xml + kt 연결하는 코드

        //btnPlus를 클릭했을 때, 이벤트가 일어나는지 확인
        //xml에 부여했던 id값을 class에서 바로 참조하는 건 불가능
        //xml에 부여한 id --> R.id에 저장이 된다(16진수의 랜덤한 값)
        // --> class에서 findViewById()
        // Id값(16진수의 랜덤한 값)을 통해서 View를 찾아온다!
        val plus : Button = findViewById(R.id.plus)
        //val Plus : Button = findViewById<Button>(R.id.btnPlus)
        // : 변수의 자료형을 통해서 추론이 가능하기 때문에
        //<Button> 생략이 가능하다

        //TypeMismatchException : 내가 찾아오려는 View랑
        // 변수의 View타입이 일치하지 않을경우에 발생하는 예외상황

        // 1) Toast창을 Emulator에 띄워보자 (문구 : 클릭!!)
        // 2) Log를 통해 확인해보자

        plus.setOnClickListener {
            //Plus를 클릭했을 때 실행시킬 코드
            Toast.makeText(this@CalculatorActivity,"클릭!!",Toast.LENGTH_SHORT).show()
            // 1) context : 화면정보(어디에 토스트를 보여지게 만들거냐
            //this@CaculatorActivity
            // 2) text : CharSequence! --> String의 문구
            // Int자료형을 띄울 수는 있음 단, View의 Id값만 가능!
            // 3) duration : 토스창의 지속시간(몇초동안 띄울거냐)

        }





//        num1 = findViewById(R.id.num1)
//        num2 = findViewById(R.id.num2)
//        result = findViewById(R.id.result)





    }

//    fun Plus(view : View) {
//        val num1 = num1.text.toString().toInt()
//        val num2 = num2.text.toString().toInt()
//        val sum = num1 + num2
//        result.text = "연산결과 : $sum"
//    }
//    fun Minus(view: View){
//        val num1 = num1.text.toString().toInt()
//        val num2 = num2.text.toString().toInt()
//        val minus = num1 - num2
//        result.text = "연산결과 : $minus"
//    }
//    fun Multiple(view: View){
//        val num1 = num1.text.toString().toInt()
//        val num2 = num2.text.toString().toInt()
//        val multiple = num1 * num2
//        result.text = "연산결과 : $multiple"
//    }
//    fun Nanu(view: View){
//        val num1 = num1.text.toString().toInt()
//        val num2 = num2.text.toString().toInt()
//        val nanu = num1/num2
//        result.text = "연산결과 : $nanu"
//    }











}