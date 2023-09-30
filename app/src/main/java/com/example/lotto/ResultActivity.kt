package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import com.example.lotto.databinding.ActivityNameBinding
import com.example.lotto.databinding.ActivityResultBinding
import java.text.SimpleDateFormat
import java.util.Date

class ResultActivity : AppCompatActivity() {

    // onCreate 밖에서 findViewById 같은 걸 놓게 되면 에러뜸

    override fun onCreate(savedInstanceState: Bundle?) {
        // 바인딩
        val resultActivityBinding = ActivityResultBinding.inflate(layoutInflater)
        val bView = resultActivityBinding.root

        super.onCreate(savedInstanceState)
        setContentView(bView)

        // MainActivity에서 전달받은 결과 배열을 가져옴
        val result = intent.getIntegerArrayListExtra("result")

        // 전달받은 이름을 가져옴
        val name = intent.getStringExtra("name")

        // 결과화면 기본 텍스트
        resultActivityBinding.resultLabel.text = "랜덤으로 생성된\n로또번호입니다."

        // name이 전달된 경우 결과화면의 텍스트를 변경
        if(!TextUtils.isEmpty(name)) {
            resultActivityBinding.resultLabel.text =
                "${name}님의 ${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())}\n로또 번호입니다."
        }

        // 전달받은 결과가 있는 경우에만 실행
        result?.let {
            // 결과에 맞게 공 이미지 업데이트. 전달을 위해 정렬도 넣는다.
            updateLottoBallImage(result.sortedBy { it }, resultActivityBinding)
        }
    }

    /**
     * 결과에 따라 로또 공 이미지를 업데이트
     * 바인딩 해줬음
     */
    fun updateLottoBallImage(result: List<Int>, binding: ActivityResultBinding) {

        // 로또 1번 공 이미지의 id를 사용
        val lottoImageStartId = R.drawable.ball_01

        // 결과가 6개 미만일 경우 에러가 발생할 수 있어서 바로 리턴해버림
        if(result.size < 6) return

        // ball_01부터 이미지 id가 있으므로, 결과값-1이 목표 이미지가 됨
        binding.imageView01.setImageResource(lottoImageStartId + (result[0] - 1))
        binding.imageView02.setImageResource(lottoImageStartId + (result[1] - 1))
        binding.imageView03.setImageResource(lottoImageStartId + (result[2] - 1))
        binding.imageView04.setImageResource(lottoImageStartId + (result[3] - 1))
        binding.imageView05.setImageResource(lottoImageStartId + (result[4] - 1))
        binding.imageView06.setImageResource(lottoImageStartId + (result[5] - 1))
    }
}