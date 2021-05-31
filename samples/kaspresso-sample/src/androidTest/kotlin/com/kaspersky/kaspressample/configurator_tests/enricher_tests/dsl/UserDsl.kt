package com.kaspersky.kaspressample.configurator_tests.enricher_tests.dsl

@EnricherTestCaseDsl
class UserDsl(
    val uniqueTestId: String,
    var id: Long = 1L,
    var name: String = "Test user $id"
) {

    val posts = mutableListOf<PostDsl>()

    fun post(block: PostDsl.() -> Unit) {
        val postDsl = PostDsl(uniqueTestId, id)
        postDsl.block()

        posts += postDsl
    }
}
