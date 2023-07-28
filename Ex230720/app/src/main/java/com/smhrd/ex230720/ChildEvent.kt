package com.smhrd.ex230720



import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class ChildEvent(var data : ArrayList<KakaoVO>, var adapter: KakaoAdapter) : ChildEventListener {
    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        // snapshot => firebase database에 저장된 데이터
        // json 구조로 응답함 => KakaoVO 형태로 반환
        var temp : KakaoVO? = snapshot.getValue(KakaoVO::class.java)
        data.add(temp!!) //temp 가 null 일수도 있음을 표기
        adapter.notifyDataSetChanged()


    }

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        // 데이터 변경 감지
        // ArrayList에 추가된 데이터 추가하고 Adapter 새로고침
        data.add(snapshot.getValue(KakaoVO::class.java)as KakaoVO)
        adapter.notifyDataSetChanged()

    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        // 제거
    }

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        // 옮겨짐
    }

    override fun onCancelled(error: DatabaseError) {
        // 뭔가 문제가 발생한걸 감지!

    }
}