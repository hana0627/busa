package com.hana.busa.repository

import com.hana.busa.dto.response.OneNumberResponse

interface LottoRepositoryCustom {
   fun findAllWithQueryDsl(): List<Int>
   fun findIdxNum(idx: Int): List<Int>
   fun findIdxNumWithQuery(idx: Int): List<OneNumberResponse>
}