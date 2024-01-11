package com.hana.busa.repository

import com.hana.busa.domain.QLotto
import com.hana.busa.domain.QLotto.lotto
import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
class LottoRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
) : LottoRepositoryCustom {
    override fun findAllWithQueryDsl(): List<Int> {
        return queryFactory.select(lotto.drwtNo1)
            .from(lotto)
            .fetch()
    }
}