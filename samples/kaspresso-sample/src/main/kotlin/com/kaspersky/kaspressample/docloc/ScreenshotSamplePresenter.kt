package com.kaspersky.kaspressample.docloc

import android.graphics.Color
import java.lang.ref.WeakReference

class ScreenshotSamplePresenter(view: ScreenshotSampleView) {
    var value = 0

    private val viewRef: WeakReference<ScreenshotSampleView>
    init {
        viewRef = WeakReference(view)
    }

    fun init() {
        viewRef.get()?.setCounterValue(value)
        viewRef.get()?.setBackgroundColor(Color.WHITE)
    }

    fun increment() {
        viewRef.get()?.setCounterValue(++value)
    }

    fun decrement() {
        viewRef.get()?.setCounterValue(--value)
    }

    fun setBackgroundColor(color: Int) {
        viewRef.get()?.setBackgroundColor(color)
    }
}
