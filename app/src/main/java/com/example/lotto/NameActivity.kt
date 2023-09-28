package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        // '코틀린 안드로이드 익스텐션' 이 2021년부터 지원되지 않기 때문에, 일단 JAVA처럼 임시로 findViewById를 썼음
        var goButton : Button = findViewById<Button>(R.id.goButton)
        var backButton : Button = findViewById<Button>(R.id.backButton)


        // 번호생성 버튼. 이름 입력후 누르면 결과로
        goButton.setOnClickListener {
            startActivity(Intent(this,ResultActivity::class.java))
        }

        // 뒤로가기 버튼. 이전 페이지로
        backButton.setOnClickListener {
            finish()
        }
    }
}