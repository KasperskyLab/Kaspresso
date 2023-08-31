package com.kaspersky.kaspresso.tutorial.user

import retrofit2.http.GET

interface ApiService {

    @GET("api/users/random_user")
    suspend fun loadUser(): User
}
