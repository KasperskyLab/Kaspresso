package com.kaspersky.kaspresso.tutorial.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name") val name: String,
    @SerializedName("last_name") val lastName: String
)
