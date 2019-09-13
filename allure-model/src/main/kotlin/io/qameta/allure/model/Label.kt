package io.qameta.allure.model

import com.google.gson.annotations.SerializedName

data class Label(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("value")
    var value: String? = null
)