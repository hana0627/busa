package com.hana.busa.lotto

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
class NumberGeneratorTest{


    @Test
    @DisplayName("랜덤하게 숫자 1 부터 45까지의 숫자가 겹치지 않게 6개 숫자가 나온다")
    fun random() {
        //given
        val numberGenerator = NumberGenerator()

        //when
        val results = mutableListOf<List<Int>>()
        for (i in 0 until 250) {
            val result: List<Int> = numberGenerator.random()
            results.add(result)
        }

        // Then
        for (i in 0 until 250) {
            assertThat(results[i]).hasSize(6)
            assertThat(results[i].toSet()).hasSize(6) // 중복값 확인
        }

    }
}