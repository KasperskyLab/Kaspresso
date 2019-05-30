package com.kaspersky.kaspressample.data

@TestCaseDslMarker
class TestCaseDsl {
    val list: MutableList<Int> = mutableListOf()

    fun rawData(rawData: Int) {
        list.add(rawData)
    }
}