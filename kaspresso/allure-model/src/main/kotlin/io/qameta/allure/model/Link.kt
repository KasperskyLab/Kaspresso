package io.qameta.allure.model

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("type")
    var type: String? = null
)
