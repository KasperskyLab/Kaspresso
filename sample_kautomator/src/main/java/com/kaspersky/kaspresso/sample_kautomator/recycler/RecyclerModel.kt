package com.kaspersky.kaspresso.sample_kautomator.recycler

import kotlin.random.Random

data class RecyclerModel(
    val text: String,
    val number: Int
) {
    companion object {
        private val POOL: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        private const val LENGTH = 10L
        private const val MAX_NUMBER_VALUE = 1000

        fun randomizeNewItem(): RecyclerModel {
            val randomText = (1..LENGTH)
                .map { Random.nextInt(0, POOL.size) }
                .map(POOL::get)
                .joinToString("")

            val randomNumber = Random.nextInt(MAX_NUMBER_VALUE)
            return RecyclerModel(randomText, randomNumber)
        }
    }
}