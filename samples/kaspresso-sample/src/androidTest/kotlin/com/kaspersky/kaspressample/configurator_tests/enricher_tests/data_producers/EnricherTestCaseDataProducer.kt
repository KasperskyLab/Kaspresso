package com.kaspersky.kaspressample.configurator_tests.enricher_tests.data_producers

import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.EnricherTestData
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.Post
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.data.User
import com.kaspersky.kaspressample.configurator_tests.enricher_tests.dsl.EnricherTestDsl

class EnricherTestCaseDataProducer {

    fun initData(action: (EnricherTestDsl.() -> Unit)?): EnricherTestData {
        val testCaseDsl = EnricherTestDsl().also { testCaseDsl -> action?.let { testCaseDsl.apply(it) } }

        val users = testCaseDsl.users.map { userDsl ->
            User(
                id = userDsl.id,
                name = userDsl.name
            )
        }
        val posts = testCaseDsl.users.map { it.posts }.flatten().map { postDsl ->
            Post(
                id = postDsl.id,
                userId = postDsl.userId,
                title = postDsl.title,
                body = postDsl.body
            )
        }

        return EnricherTestData(users, posts)
    }
}
