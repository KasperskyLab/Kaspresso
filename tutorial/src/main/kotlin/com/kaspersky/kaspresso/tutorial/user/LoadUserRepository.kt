package com.kaspersky.kaspresso.tutorial.user

import kotlinx.coroutines.delay

object LoadUserRepository {

    private val errors = arrayOf(false, false, true)
    private val users = arrayOf(
        User(name = "John", lastName = "Smith"),
        User(name = "Max", lastName = "Thomson"),
        User(name = "Helen", lastName = "West"),
    )

    private var lastIndex = 0

    suspend fun loadUser(): User {
        delay(3000)
        val index = lastIndex % errors.size
        lastIndex++
        val isError = errors[index]
        if (!isError) {
            return users[index]
        } else {
            throw RuntimeException("Error when getting user")
        }
    }
}
