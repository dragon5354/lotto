package com.example.lotto

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

object LottoNumberMaker {

    // 랜덤으로 1~45 번호중 하나의 번호를 생성하는 함수
    fun getRandomLottoNumber() : Int {
        // Random.nextInt는 0~전달받은 값 미만의 번호를 생성하므로, 1을 더해준다.
        return Random().nextInt(45) + 1
    }

    // 랜덤으로 6개의 로또 번호를 만드는 함수(랜덤함수 6번실행)
    fun getRandomLottoNumbers() : MutableList<Int> {
        // 리스트 생성
        val lottoNumbers = mutableListOf<Int>()

        // 6번 반복
        for (i in 1..6) {
            // 임시로 사용할 변수. 중복 방지용
            var number = 0
            do {
                // 랜덤한 번호를 추출해 number에 저장. 중복 방지용
                number = getRandomLottoNumber()

            } // lottoNumbers에 number 변수값이 없을때까지 반복
            while (lottoNumbers.contains(number))
            // 리스트에 생성한 번로를 추가
            lottoNumbers.add(getRandomLottoNumber())
        }
        return lottoNumbers
    }

    // 셔플 (위쪽 2개 대신 사용가능)
    fun getSuffleLottoNumbers() : MutableList<Int> {
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

    // 입력받은 이름에 대한 해시코드를 사용, 로또 번호를 섞은 다음 결과를 반환한다.
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