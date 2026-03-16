package com.kaspersky.components.kautomator.common.resources

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

internal object KString {
    fun getString(@StringRes resId: Int): String =
        InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)
}
