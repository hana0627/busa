package com.hana.busa.service

import com.hana.busa.repository.LottoRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
    @DisplayName("쿼리Dsl이 정상적으로 동작하는지 확인한다.")
    fun test() {
        // given

        // when
        val results = sut.queryDslTest()
        //then
        Assertions.assertThat(results).isNotEmpty
    }

}