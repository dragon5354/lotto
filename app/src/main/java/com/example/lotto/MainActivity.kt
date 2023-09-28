package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // '코틀린 안드로이드 익스텐션' 이 2021년부터 지원되지 않기 때문에, 일단 JAVA처럼 임시로 findViewById를 썼음
        var randomCard : CardView = findViewById<CardView>(R.id.randomCard)
        var constellationCard : CardView = findViewById<CardView>(R.id.constellationCard)
        var nameCard : CardView = findViewById<CardView>(R.id.nameCard)


        // 랜덤 번호 생성. 바로 결과창으로
        randomCard.setOnClickListener {
            startActivity(Intent(this,ResultActivity::class.java))
        }

        // 별자리 번호 생성. 해당 페이지로
        constellationCard.setOnClickListener {
            startActivity(Intent(this,ConstellationActivity::class.java))
        }

        //이름 번호 생성. 해당 페이지로2
        nameCard.setOnClickListener {
            startActivity(Intent(this,NameActivity::class.java))
        }
    }
}