package com.kaspersky.kaspresso.kautomatorsample.model

import kotlin.random.Random

data class SimpleModel(
    val text: String,
    val number: Int
) {
    companion object {
        private val POOL: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        private const val LENGTH = 10L
        private const val MAX_NUMBER_VALUE = 1000

        fun randomizeNewItem(): SimpleModel {
            val randomText = (1..LENGTH)
                .map { Random.nextInt(0, POOL.size) }
                .map(POOL::get)
                .joinToString("")

            val randomNumber = Random.nextInt(MAX_NUMBER_VALUE)
            return SimpleModel(
                randomText,
                randomNumber
            )
        }

        fun richWithLabels(list: MutableList<SimpleModel>) = list.apply {
            add(0, SimpleModel("Beginning", 0))
            add(size / 2, SimpleModel("Center", 1))
            add(SimpleModel("End", 2))
        }
    }
}
