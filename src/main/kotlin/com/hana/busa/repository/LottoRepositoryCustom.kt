package com.hana.busa.repository

interface LottoRepositoryCustom {
   fun findAllWithQueryDsl(): List<Int>
}