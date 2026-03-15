package com.kaspersky.kaspresso.kautomatorsample.model

import kotlin.random.Random

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
