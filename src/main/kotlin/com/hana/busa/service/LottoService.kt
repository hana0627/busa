package com.hana.busa.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.hana.busa.domain.Lotto
import com.hana.busa.dto.response.LottoResponse
import com.hana.busa.dto.response.OneNumberResponse
import com.hana.busa.repository.LottoRepository
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


    fun queryDslTest(): List<Int> {
        return lottoRepository.findAllWithQueryDsl()
    }

    fun findIdxNum(idx: Int): List<OneNumberResponse> {
//        val results = mutableListOf<OneNumberResponse>()
//        val numbers = lottoRepository.findIdxNum(idx)
//        for (number in numbers) {
//            results.firstOrNull{dto -> dto.number == number} ?. plusOne()
//                ?: results.add(OneNumberResponse(number,1))
//        }
//        results.sortBy { e -> e.number }
//        return results

        // 이렇게 쿼리로 해결 가능하다.
        return lottoRepository.findIdxNumWithQuery(idx)
    }




    fun findByDrwNo(drwNo: Int) {
        val headers = HttpHeaders()
        headers.set("Accept", "application/json")
        val httpEntity = HttpEntity<Any>(headers)

        val url = BASE_URL + drwNo
        val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String::class.java).body
        val json = objectMapper.readValue(response, LottoResponse::class.java);
        println(json)
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
            val dto = objectMapper.readValue(response, LottoResponse::class.java);
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




