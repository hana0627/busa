package com.hana.busa.controller

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    
    
    @GetMapping("/health_check")
    fun healthCheck(): String {
        return "<h4>안녕</h4>"
    }
}