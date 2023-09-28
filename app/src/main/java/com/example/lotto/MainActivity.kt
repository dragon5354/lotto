package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import java.util.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // '코틀린 안드로이드 익스텐션' 이 2021년부터 지원되지 않기 때문에, 일단 JAVA처럼 임시로 findViewById를 썼음
        var randomCard : CardView = findViewById<CardView>(R.id.randomCard)
        var constellationCard : CardView = findViewById<CardView>(R.id.constellationCard)
        var nameCard : CardView = findViewById<CardView>(R.id.nameCard)

        // 1. 랜덤 번호 생성. 바로 결과창으로
        randomCard.setOnClickListener {

            // ResultActivity를 시작하는 Intent 생성
            val intent = Intent(this,ResultActivity::class.java)
            // intent의 결과 데이터를 전달. 리스트를 전달하기 때문에 저런 형식으로 쓴다.
            intent.putIntegerArrayListExtra("result", ArrayList(getRandomLottoNumbers()))

            // intent를 만들고 startActivity로 실행
            startActivity(intent)
        }

        // 2. 별자리 번호 생성. 해당 페이지로
        constellationCard.setOnClickListener {
            startActivity(Intent(this,ConstellationActivity::class.java))
        }

        // 3. 이름 번호 생성. 해당 페이지로2
        nameCard.setOnClickListener {
            startActivity(Intent(this,NameActivity::class.java))
        }
    }

    /**
     * 랜덤으로 번호 생성에 사용되는 부분
     * 랜덤함수 + 반복문 or 랜덤함수 + 셔플
     */
    // 랜덤으로 1~45 번호중 하나의 번호를 생성하는 함수
    fun getRandomLottoNumber() : Int {
        // Random.nextInt는 0~전달받은 값 미만의 번호를 생성하므로, 1을 더해준다.
        return Random().nextInt(45) + 1
    }

//    // 랜덤으로 6개의 로또 번호를 만드는 함수(랜덤함수 6번실행)
//    fun getRandomLottoNumbers() : MutableList<Int> {
//        // 리스트 생성
//        val lottoNumbers = mutableListOf<Int>()
//
//        // 6번 반복
//        for (i in 1..6) {
//            // 임시로 사용할 변수. 중복 방지용
//            var number = 0
//            do {
//                // 랜덤한 번호를 추출해 number에 저장. 중복 방지용
//                number = getRandomLottoNumber()
//
//            } // lottoNumbers에 number 변수값이 없을때까지 반복
//            while (lottoNumbers.contains(number))
//            // 리스트에 생성한 번로를 추가
//            lottoNumbers.add(getRandomLottoNumber())
//        }
//        return lottoNumbers
//    }

    // 셔플 (위쪽 주석 대신 사용가능)
    fun getRandomLottoNumbers() : MutableList<Int> {
        // 리스트 생성
        val list = mutableListOf<Int>()

        // 6번 반복
        for (number in 1..45) {
            list.add(number)
        }

        // 리스트 무작위로 섞기
        list.shuffle()

        // 리스트를 앞에서부터 순서대로 6개 잘라서 결과 반환
        return list.subList(0,6)
    }
}