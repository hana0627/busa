package com.hana.busa.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.hana.busa.dto.response.LottoResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
@RequiredArgsConstructor
class BusaService(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
) {
    private val BASE_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="


    fun findByDrwNo(drwNo: Int) {
        val headers = HttpHeaders()
        headers.set("Accept", "application/json")
        val httpEntity = HttpEntity<Any>(headers)

        val url = BASE_URL + drwNo
        val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String::class.java).body
        val json = objectMapper.readValue(response, LottoResponse::class.java);
        println(json)
    }
}
