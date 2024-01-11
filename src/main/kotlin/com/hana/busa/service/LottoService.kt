package com.hana.busa.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.hana.busa.domain.Lotto
import com.hana.busa.dto.response.LottoApiResponse
import com.hana.busa.dto.response.LottoResponse
import com.hana.busa.dto.response.OneNumberResponse
import com.hana.busa.lotto.NumberGenerator
import com.hana.busa.repository.LottoRepository
import jakarta.persistence.EntityNotFoundException
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class LottoService(
    private val lottoRepository: LottoRepository,
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
) {
    private val BASE_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="

    fun findIdxNum(idx: Int): List<OneNumberResponse> {
        return lottoRepository.findIdxNumWithQuery(idx)
    }

    fun getNumbers(game: Int): MutableList<LottoResponse> {
        val results =  mutableListOf <LottoResponse>()
        var randomList: List<Int>
        for(i in 0..game) {
            while(true) {
                randomList = NumberGenerator().random()
                if(randomList[0] <=20 && randomList[5] >=30) {
                    break;
                }
            }
            results.add(LottoResponse(randomList))
        }
        return results
    }

    fun getByDrwNo(drwNo: Int): LottoResponse {
        return LottoResponse(lottoRepository.findByDrwNo(drwNo)
            ?: throw EntityNotFoundException("존재하지 않는 회차입니다."))
    }









    @Transactional
    fun init(): String {
        val headers = HttpHeaders()
        headers.set("Accept", "application/json")
        val httpEntity = HttpEntity<Any>(headers)

        var i = 0
        var results = mutableListOf<Lotto>()
        while(true) {
            i++
            val url = BASE_URL + i
            val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String::class.java).body

            if(i> 1000) {
                if(response == "{\"returnValue\":\"fail\"}") {
                    break;
                }
            }
            val dto = objectMapper.readValue(response, LottoApiResponse::class.java);
            val entity: Lotto = dto.toEntity()
            results.add(entity)
        }
        if (results.isNotEmpty()) {
            results.removeAt(results.size - 1)
            lottoRepository.saveAll(results)
        }
        return "success"
    }
}




