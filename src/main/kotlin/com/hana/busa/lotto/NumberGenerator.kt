package com.hana.busa.lotto

class NumberGenerator {

    fun random(): List<Int> {
        val randomList = mutableListOf<Int>()

        while(randomList.size < 6) {
            val num = ((Math.random() * 45) + 1).toInt()
            if(!randomList.contains(num)) {
                randomList.add(num)
            }
        }

        randomList.sort()
        return randomList

    }
}