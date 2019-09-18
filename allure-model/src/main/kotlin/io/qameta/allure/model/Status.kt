package io.qameta.allure.model

import com.google.gson.annotations.SerializedName

enum class Status(val s: String) {
    @SerializedName("failed")
    FAILED("failed"),
    @SerializedName("broken")
    BROKEN("broken"),
    @SerializedName("passed")
    PASSED("passed"),
    @SerializedName("skipped")
    SKIPPED("skipped"),
    @SerializedName("unknown")
    UNKNOWN("unknown");

    companion object {
        @JvmStatic
        fun fromThrowable(e: Throwable?): Status {
            when (e) {
                is AssertionError -> return FAILED
                else -> return BROKEN
            }
        }
    }
}
