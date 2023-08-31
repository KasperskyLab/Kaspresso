package com.kaspersky.kaspresso.tutorial.user

object LoadUserRepository {

    private val apiService = ApiFactory.apiService

    suspend fun loadUser(): User = apiService.loadUser()
}
