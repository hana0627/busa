package com.hana.busa.dto.response

import com.hana.busa.domain.Lotto

data class LottoApiResponse(
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


    fun toEntity() : Lotto {
        return Lotto(
            totSellamnt = this.totSellamnt,
            returnValue = this.returnValue,
            drwNo = this.drwNo,
            drwNoDate = this.drwNoDate,
            firstWinamnt = this.firstWinamnt,
            firstPrzwnerCo = this.firstPrzwnerCo,
            firstAccumamnt = this.firstAccumamnt,
            drwtNo1 = this.drwtNo1,
            drwtNo2 = this.drwtNo2,
            drwtNo3 = this.drwtNo3,
            drwtNo4 = this.drwtNo4,
            drwtNo5 = this.drwtNo5,
            drwtNo6 = this.drwtNo6,
            bnusNo = this.bnusNo,
            )
    }
}
