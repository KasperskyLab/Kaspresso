package com.kaspersky.kaspresso.device.logcat

data class LogcatBufferSize(val size: Int, val dimension: Dimension) {
    enum class Dimension(val stringValue: String) {
        KILOBYTES("K"),
        MEGABYTES("M");
    }

    override fun toString(): String {
        return "$size${dimension.stringValue}"
    }
}
