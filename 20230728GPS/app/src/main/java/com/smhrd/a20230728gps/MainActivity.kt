package com.smhrd.a20230728gps

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //플랫폼API를 이용해 사용자의 위치를 얻을 때 사용
        val manager = getSystemService(LOCATION_SERVICE) as LocationManager

        //현재 기기에 어떤 위치 제공자가 있는지 알고 싶다면 LoactionManager의 allProviders 프로퍼티 사용
        //  allProviders는 MutableList<String> 타입의 결과를 반환
        var result = "All Providers :"
        val providers = manager.allProviders
        for(provider in providers){
            result+="$provider"
        }
         Log.d("kkang", result)

        //지금 사용할 수 있는 위치 제공자를 알아보려면 getProviders() 함수 사용
        result = "Enabled Providers :"
        val enabledProviders = manager.getProviders(true)
        for(provider in enabledProviders){
            result+="$provider"
        }
        Log.d("kkang", result) // Enabled Providers : passive, gps, network

        //사용자의 위치 정보를 얻으려면 LocationManager의 getLastKnownLocation() 함수를 사용
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
        ) === PackageManager.PERMISSION_GRANTED
        ){
        val location: Location? =
            manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        location?.let{
            val latitude = location.latitude
            val longitude = location.longitude
            val accuracy = location.accuracy
            val time = location.time

            Log.d("kkang", "$latitude, $longitude, $accuracy, $time")
        }

    }
}
}