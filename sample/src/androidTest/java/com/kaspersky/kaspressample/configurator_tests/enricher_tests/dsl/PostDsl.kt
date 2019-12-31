package com.kaspersky.kaspressample.configurator_tests.enricher_tests.dsl

@EnricherTestCaseDsl
class PostDsl(
    val uniqueTestId: String,
    val userId: Long,
    var id: Long = 1L,
    var title: String = "Post title $id",
    var body: String = "Post body $id"
)
