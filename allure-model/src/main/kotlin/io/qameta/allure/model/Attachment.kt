package io.qameta.allure.model

import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("source")
    var source: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("type")
    var type: String? = null
)