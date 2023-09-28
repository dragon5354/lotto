package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView

class ResultActivity : AppCompatActivity() {

    // 로또 1번 공 이미지의 id를 사용
    // onCreate 밖에서 findViewById 같은 걸 놓게 되면 에러뜸
    val lottoImageStartId = R.drawable.ball_01

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // MainActivity에서 전달받은 결과 배열을 가져옴
        val result = intent.getIntegerArrayListExtra("result")

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