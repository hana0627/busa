package com.hana.busa.dto.response

import java.time.LocalDateTime

data class LottoResponse(
    val totSellamnt: Long, // 총상금액
    val returnValue: String, // 수신결과
    val drwNo: Int, //로또 회차
    val drwNoDate: String, // 추첨일
    val firstWinamnt: Long, // 1등 상금액
    val firstPrzwnerCo: Int, // 1등 당첨인원
    val firstAccumamnt: Long,
    val drwtNo1: Int, // 로또번호1
    val drwtNo2: Int, // 로또번호2
    val drwtNo3: Int, // 로또번호3
    val drwtNo4: Int, // 로또번호4
    val drwtNo5: Int, // 로또번호5
    val drwtNo6: Int, // 로또번호6
    val bnusNo: Int, // 보너스 번호

) {


    companion object {
        fun parseLottoResponse(String: Int) {

        }
    }
}