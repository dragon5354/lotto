package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.cardview.widget.CardView
import com.example.lotto.databinding.ActivityConstellationBinding
import com.example.lotto.databinding.ActivityResultBinding
import java.util.Calendar

class ConstellationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 바인딩
        val coAB = ActivityConstellationBinding.inflate(layoutInflater)
        val bView = coAB.root

        super.onCreate(savedInstanceState)
        setContentView(bView)

        // 별자리 선택후 누르면 결과로
        coAB.goResultButton.setOnClickListener {
            val intent = Intent(this,ResultActivity::class.java)

            // 별자리의 해시코드로 생성한 로또 번호
            intent.putIntegerArrayListExtra("result",
                ArrayList(LottoNumberMaker.getLottoNumbersFromHash(
                    LottoNumberMaker.makeConstellationString(
                        coAB.datePicker.month,
                        coAB.datePicker.dayOfMonth))))

            // 별자리 추가로 전달
            intent.putExtra("constellation",
                LottoNumberMaker.makeConstellationString(
                    coAB.datePicker.month,
                    coAB.datePicker.dayOfMonth))

            // resultActivity를 시작하는 Intent를 만들고 실행
            startActivity(intent)
        }

        // 현재 DatePicker의 월, 일 정보로 별자리 텍스트 변경
        coAB.textView.text = LottoNumberMaker.makeConstellationString(coAB.datePicker.month, coAB.datePicker.dayOfMonth)

        // DatePicke의 날짜가 변화하면 별자리를 보여주는 텍스트뷰도 변경
        val calendar = Calendar.getInstance()
        coAB.datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            object:CalendarView.OnDateChangeListener,
                DatePicker.OnDateChangedListener {
                    override fun onDateChanged(
                        view: DatePicker?,
                        year: Int,
                        monthOfYear: Int,
                        dayOfMonth: Int
                    ) {
                        // 변경된 시점의 DatePicker의 일, 월 정보로 별자리 텍스트 변경
                        coAB.textView.text = LottoNumberMaker.makeConstellationString(coAB.datePicker.month, coAB.datePicker.dayOfMonth)
                    }

                    override fun onSelectedDayChange(view: CalendarView, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                        // 내용없음
                    }
                })


    }


}