package io.qameta.allure.model

import com.google.gson.annotations.SerializedName

enum class Stage(val s: String) {
    @SerializedName("scheduled")
    SCHEDULED("scheduled"),
    @SerializedName("running")
    RUNNING("scheduled"),
    @SerializedName("finished")
    FINISHED("scheduled"),
    @SerializedName("pending")
    PENDING("scheduled"),
    @SerializedName("interrupted")
    INTERRUPTED("scheduled");
}