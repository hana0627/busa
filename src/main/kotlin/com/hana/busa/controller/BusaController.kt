package com.hana.busa.controller

import com.hana.busa.service.BusaService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class BusaController (
    private val busaService: BusaService
) {


    @GetMapping("/busa/{drwNo}")
    fun findByDrwNo(@PathVariable(name = "drwNo") drwNo: Int) {
        busaService.findByDrwNo(drwNo)
    }
}