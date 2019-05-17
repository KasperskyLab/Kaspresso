package com.kaspersky.kaspresso.testcases.models

data class StepInfo(
    val description: String,
    val testClassName: String,
    val level: Int,
    val number: String,
    val ordinal: Int
)