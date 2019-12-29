package com.kaspersky.kaspressample.configurator_tests.enricher_tests.dsl

import java.util.*

@EnricherTestCaseDsl
class EnricherTestDsl {

    val users = mutableListOf<UserDsl>()

    private val uniqueTestId = UUID.randomUUID().toString()


    fun user(block: UserDsl.() -> Unit) {
        val userDsl = UserDsl(uniqueTestId)
        userDsl.block()

        users += userDsl
    }

}