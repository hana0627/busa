package com.hana.busa.controller

import com.hana.busa.service.LottoService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class LottoController (
    private val lottoService: LottoService
) {


    @PostMapping("/busa/init")
    fun findByDrwNo(): String {
        return lottoService.init();
    }
}

