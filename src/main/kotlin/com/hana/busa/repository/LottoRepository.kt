package com.hana.busa.repository

import com.hana.busa.domain.Lotto
import org.springframework.data.jpa.repository.JpaRepository

interface LottoRepository : JpaRepository<Lotto, Long> {
}