package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        // '코틀린 안드로이드 익스텐션' 이 2021년부터 지원되지 않기 때문에, 일단 JAVA처럼 임시로 findViewById를 썼음
        var goButton : Button = findViewById<Button>(R.id.goButton)
        var backButton : Button = findViewById<Button>(R.id.backButton)

        // 위랑 같은데 번호 넘기는 용도
        var editText : EditText = findViewById<EditText>(R.id.editText)


        // 번호생성 버튼. 이름 입력후 누르면 결과로
        goButton.setOnClickListener {

            // 입력된 이름이 없으면 토스트 출력후 리턴
            if(TextUtils.isEmpty(editText.text.toString()))return@setOnClickListener

            val intent = Intent(this,ResultActivity::class.java)

            // intent의 결과 데이터를 전달. 리스트를 전달하고, 해시코드로 생성한 로또 번호
            intent.putIntegerArrayListExtra("result", ArrayList(getLottoNumbersFromHash(editText.text.toString())))

            // 입력받은 이름을 추가로 전달
            intent.putExtra("name",editText.text.toString())

            // intent를 만들고 startActivity로 실행
            startActivity(intent)
        }

        // 뒤로가기 버튼. 이전 페이지로
        backButton.setOnClickListener {
            finish()
        }
    }
    /**
     * 이름으로 번호 생성에 사용되는 부분
     * 입력받은 이름을 해시코드로 변환해서 로또 번호로 바꿈
     */
    fun getLottoNumbersFromHash(name : String) : MutableList<Int> {
        // 1 ~ 45 번호 저장할 리스트 생성
        val list = mutableListOf<Int>()

        // 1 ~ 45 for 리스트 생성
        for(number in 1..45) {
            list.add(number)
        }

        // 이름만으로 하면 매번 같은 번호가 나오기 때문에, 날짜의 시간값을 추가해서 변동시켜줌(매일 로또번호가 바뀜)
        val targetString = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date()) + name

        // 셔플, 랜덤함수, 입력받은 이름 해시코드 변환
        list.shuffle(Random(targetString.hashCode().toLong()))

        // 결과 반환(6개)
        return list.subList(0, 6)

    }
}