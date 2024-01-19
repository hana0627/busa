package com.hana.busa.repository

import com.hana.busa.domain.QLotto
import com.hana.busa.domain.QLotto.lotto
import com.hana.busa.dto.response.OneNumberResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import lombok.RequiredArgsConstructor
import java.lang.IllegalArgumentException

@RequiredArgsConstructor
class LottoRepositoryCustomImpl (
    private val queryFactory: JPAQueryFactory,
) : LottoRepositoryCustom {
    override fun findAllWithQueryDsl(): List<Int> {
        return queryFactory.select(lotto.drwtNo1)
            .from(lotto)
            .fetch()
    }

    override fun findIdxNum(idx: Int): List<Int> {
        val query = when (idx) {
            1 -> queryFactory.select(lotto.drwtNo1)
            2 -> queryFactory.select(lotto.drwtNo2)
            3 -> queryFactory.select(lotto.drwtNo3)
            4 -> queryFactory.select(lotto.drwtNo4)
            5 -> queryFactory.select(lotto.drwtNo5)
            6 -> queryFactory.select(lotto.drwtNo6)
            else -> throw IllegalArgumentException("1부터 6까지의 숫자를 입력해주세요!")
        }

        return query.from(lotto).fetch();
    }

    override fun findIdxNumWithQuery(idx: Int): List<OneNumberResponse> {
        require(idx in 1..6) {"1부터 6까지의 숫자를 입력해주세요!"}

        val lottoNumberField = when (idx) {
            1 -> lotto.drwtNo1
            2 -> lotto.drwtNo2
            3 -> lotto.drwtNo3
            4 -> lotto.drwtNo4
            5 -> lotto.drwtNo5
            6 -> lotto.drwtNo6
            else -> throw IllegalArgumentException("1부터 6까지의 숫자를 입력해주세요!")
        }

        return queryFactory.select(Projections.constructor(OneNumberResponse::class.java,
            lottoNumberField,
            lottoNumberField.count()))
            .from(lotto)
            .groupBy(lottoNumberField)
            .orderBy(lottoNumberField.asc())
            .fetch()

    }
}