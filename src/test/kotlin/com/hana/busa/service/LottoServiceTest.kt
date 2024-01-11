package com.hana.busa.service

import com.hana.busa.dto.response.LottoResponse
import com.hana.busa.repository.LottoRepository
import jakarta.persistence.EntityNotFoundException
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class LottoServiceTest @Autowired constructor(
    private val lottoRepository: LottoRepository,
    private val sut: LottoService,

) {

    @Test
    @DisplayName("idx에 1~6사이의 숫자를 넣으면 해당 번째 번호가 몇개씩 나왔는지 확인가능하다.")
    fun findIdxNumTest() {
        // given

        // when
        val results = sut.findIdxNum(6)

        // then
        println(results)
    }
    @Test
    @DisplayName("첫번째 숫자는 20이하, 마지막숫자는 30이 넘게 6자리 수를 추출한다.")
    fun getNumbersTest() {
        //given

        //when
        val results:List<LottoResponse> = sut.getNumbers(100)

        //then
        results.forEach{println(it)}
        results.forEach{assertNumber(it)}
    }


    private fun assertNumber(result: LottoResponse) {
        assertThat(result.firstNum).isLessThan(21)
        assertThat(result.sixthNum).isGreaterThan(29)
    }


    @Test
    @DisplayName("회차별 조회_성공")
    fun getByDrwNoTest() {
        //given
        val drwNo: Int = 800

        //when
        val result: LottoResponse = sut.getByDrwNo(drwNo)

        //then
        assertThat(result).isNotNull
        assertThat(result.firstNum).isEqualTo(1)
        assertThat(result.secondNum).isEqualTo(4)
        assertThat(result.thirdNum).isEqualTo(10)
        assertThat(result.fourthNum).isEqualTo(12)
        assertThat(result.fifthNum).isEqualTo(28)
        assertThat(result.sixthNum).isEqualTo(45)
    }



    @Test
    @DisplayName("회차별 조회_없는회차 조회")
    fun getByDrwNo_Fail_Test() {
        //given
        val drwNo: Int = 999999

        //when

        val message = assertThrows<EntityNotFoundException> {
            sut.getByDrwNo(drwNo)
        }.message

        //then
        assertThat(message).isEqualTo("존재하지 않는 회차입니다.")
    }


}



/* 1 ~ 1100 회차정리

분석하려다가 포기
머신러닝이나 막 알고리즘 함수 알아야할듯,,

draw1
(number=1, count=152)
(number=2, count=121)
(number=3, count=111)
(number=4, count=99)
(number=5, count=88)
(number=6, count=84)
(number=7, count=69)
(number=8, count=54)
(number=9, count=39)
(number=10, count=41)
(number=11, count=37)
(number=12, count=40)
(number=13, count=30)
(number=14, count=32)
(number=15, count=13)
(number=16, count=22)
(number=17, count=19)
(number=18, count=7)
(number=19, count=12)
(number=20, count=5)
(number=21, count=8)
(number=22, count=5)
(number=23, count=3)
(number=24, count=2)
(number=25, count=1)
(number=26, count=3)
(number=27, count=1)
(number=29, count=1)
(number=35, count=1)


[OneNumberResponse(number=2, count=21)
OneNumberResponse(number=3, count=34)
OneNumberResponse(number=4, count=44)
OneNumberResponse(number=5, count=46)
OneNumberResponse(number=6, count=44)
OneNumberResponse(number=7, count=68)
OneNumberResponse(number=8, count=66)
OneNumberResponse(number=9, count=54)
OneNumberResponse(number=10, count=70)
OneNumberResponse(number=11, count=65)
OneNumberResponse(number=12, count=64)
OneNumberResponse(number=13, count=49)
OneNumberResponse(number=14, count=54)
OneNumberResponse(number=15, count=53)
OneNumberResponse(number=16, count=40)
OneNumberResponse(number=17, count=46)
OneNumberResponse(number=18, count=52)
OneNumberResponse(number=19, count=29)
OneNumberResponse(number=20, count=33)
OneNumberResponse(number=21, count=30)
OneNumberResponse(number=22, count=21)
OneNumberResponse(number=23, count=23)
OneNumberResponse(number=24, count=15)
OneNumberResponse(number=25, count=17)
OneNumberResponse(number=26, count=14)
OneNumberResponse(number=27, count=16)
OneNumberResponse(number=28, count=4)
OneNumberResponse(number=29, count=5)
OneNumberResponse(number=30, count=7)
OneNumberResponse(number=31, count=4)
OneNumberResponse(number=32, count=3)
OneNumberResponse(number=33, count=3)
OneNumberResponse(number=34, count=3)
OneNumberResponse(number=36, count=2)
OneNumberResponse(number=37, count=1)


 */




