package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.lotto.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 바인딩
        val mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        val bView = mainActivityBinding.root

        super.onCreate(savedInstanceState)
        setContentView(bView)

        // 1. 랜덤 번호 생성. 바로 결과창으로
        mainActivityBinding.randomCard.setOnClickListener {

            // ResultActivity를 시작하는 Intent 생성
            val intent = Intent(this,ResultActivity::class.java)
            // intent의 결과 데이터를 전달. 리스트를 전달하기 때문에 저런 형식으로 쓴다.
            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMaker.getSuffleLottoNumbers()))

            // intent를 만들고 startActivity로 실행
            startActivity(intent)
        }

        // 2. 별자리 번호 생성. 해당 페이지로
        mainActivityBinding.constellationCard.setOnClickListener {
            startActivity(Intent(this,ConstellationActivity::class.java))
        }

        // 3. 이름 번호 생성. 해당 페이지로2
        mainActivityBinding.nameCard.setOnClickListener {
            startActivity(Intent(this,NameActivity::class.java))
        }
    }
}