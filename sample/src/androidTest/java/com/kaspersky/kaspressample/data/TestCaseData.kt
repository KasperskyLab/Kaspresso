package com.kaspersky.kaspressample.data

class TestCaseData(
    val list: MutableList<String> = mutableListOf()
) {
    fun addString(string: String) {
        list.add(string)
    }
}