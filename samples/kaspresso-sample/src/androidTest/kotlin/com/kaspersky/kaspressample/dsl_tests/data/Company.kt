package com.kaspersky.kaspressample.dsl_tests.data

data class Company(
    var name: String? = null,
    var city: String? = null,
    var country: String? = null,
    var owner: Owner? = null
)
