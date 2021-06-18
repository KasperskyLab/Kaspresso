package com.kaspersky.kaspresso_sample_core.docloc

import android.graphics.Color

class ScreenshotSamplePresenter(private val view: ScreenshotSampleView) {
    var value = 0

    fun init() {
        view.setCounterValue(value)
        view.setBackgroundColor(Color.WHITE)
    }

    fun increment() {
        view.setCounterValue(++value)
    }

    fun decrement() {
        view.setCounterValue(--value)
    }

    fun setBackgroundColor(color: Int) {
        view.setBackgroundColor(color)
    }
}
