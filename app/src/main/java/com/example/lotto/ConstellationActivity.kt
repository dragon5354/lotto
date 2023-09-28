package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class ConstellationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)

        // '코틀린 안드로이드 익스텐션' 이 2021년부터 지원하지 않기 때문에, 일단 JAVA처럼 임시로 findViewById를 썼음
        var goResultButton : Button = findViewById<Button>(R.id.goResultButton)


        // 별자리 선택후 누르면 결과로
        goResultButton.setOnClickListener {
            startActivity(Intent(this,ResultActivity::class.java))
        }
    }
}