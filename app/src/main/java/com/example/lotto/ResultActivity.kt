package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import java.text.SimpleDateFormat
import java.util.Date

class ResultActivity : AppCompatActivity() {

    // onCreate 밖에서 findViewById 같은 걸 놓게 되면 에러뜸

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // '코틀린 안드로이드 익스텐션' 이 2021년부터 지원되지 않기 때문에, 일단 JAVA처럼 임시로 findViewById를 썼음
        var resultLavel : TextView = findViewById<TextView>(R.id.resultLabel)

        // MainActivity에서 전달받은 결과 배열을 가져옴
        val result = intent.getIntegerArrayListExtra("result")

        // 전달받은 이름을 가져옴
        val name = intent.getStringExtra("name")

        // 결과화면 기본 텍스트
        resultLavel.text = "랜덤으로 생성된\n로또번호입니다."

        // name이 전달된 경우 결과화면의 텍스트를 변경
        if(!TextUtils.isEmpty(name)) {
            resultLavel.text = "${name}님의 ${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())}\n로또 번호입니다."
        }

        // 전달받은 결과가 있는 경우에만 실행
        result?.let {
            // 결과에 맞게 공 이미지 업데이트. 전달을 위해 정렬도 넣는다.
            updateLottoBallImage(result.sortedBy { it })
        }
    }

    /**
     * 결과에 따라 로또 공 이미지를 업데이트
     */
    fun updateLottoBallImage(result: List<Int>) {

        // 로또 1번 공 이미지의 id를 사용
        val lottoImageStartId = R.drawable.ball_01
        // '코틀린 안드로이드 익스텐션' 이 2021년부터 지원되지 않기 때문에, 일단 JAVA처럼 임시로 findViewById를 썼음
        var imageView01 : ImageView = findViewById<ImageView>(R.id.imageView01)
        var imageView02 : ImageView = findViewById<ImageView>(R.id.imageView02)
        var imageView03 : ImageView = findViewById<ImageView>(R.id.imageView03)
        var imageView04 : ImageView = findViewById<ImageView>(R.id.imageView04)
        var imageView05 : ImageView = findViewById<ImageView>(R.id.imageView05)
        var imageView06 : ImageView = findViewById<ImageView>(R.id.imageView06)

        // 결과가 6개 미만일 경우 에러가 발생할 수 있어서 바로 리턴해버림
        if(result.size < 6) return

        // ball_01부터 이미지 id가 있으므로, 결과값-1이 목표 이미지가 됨
        imageView01.setImageResource(lottoImageStartId + (result[0] - 1))
        imageView02.setImageResource(lottoImageStartId + (result[1] - 1))
        imageView03.setImageResource(lottoImageStartId + (result[2] - 1))
        imageView04.setImageResource(lottoImageStartId + (result[3] - 1))
        imageView05.setImageResource(lottoImageStartId + (result[4] - 1))
        imageView06.setImageResource(lottoImageStartId + (result[5] - 1))
    }
}