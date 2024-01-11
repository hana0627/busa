package com.hana.busa.repository

import com.hana.busa.domain.Lotto
import com.hana.busa.dto.response.LottoResponse
import org.springframework.data.jpa.repository.JpaRepository

interface LottoRepository : JpaRepository<Lotto, Long>, LottoRepositoryCustom {
    fun findByDrwNo(drwNo: Int): Lotto?
}
