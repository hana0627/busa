package com.hana.busa.dto.response

/**
 * 당첨갯수확인
 * {1,20} => 1번이 20번 당첨번호로 등장
 */
data class OneNumberResponse(
    val number: Int,
    var count: Long,
) {


    fun plusOne() {
        this.count++;
    }
}