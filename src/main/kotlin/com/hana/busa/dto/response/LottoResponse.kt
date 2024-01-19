package com.hana.busa.dto.response

import com.hana.busa.domain.Lotto

data class LottoResponse(
    val firstNum: Int,
    val secondNum: Int,
    val thirdNum: Int,
    val fourthNum: Int,
    val fifthNum: Int,
    val sixthNum: Int,
) {

    constructor(list: List<Int>) : this(
        firstNum = list[0],
        secondNum = list[1],
        thirdNum = list[2],
        fourthNum = list[3],
        fifthNum = list[4],
        sixthNum = list[5]
    )

    constructor(entity: Lotto) : this(
        firstNum = entity.drwtNo1,
        secondNum = entity.drwtNo2,
        thirdNum = entity.drwtNo3,
        fourthNum = entity.drwtNo4,
        fifthNum = entity.drwtNo5,
        sixthNum = entity.drwtNo6,
    )

}
