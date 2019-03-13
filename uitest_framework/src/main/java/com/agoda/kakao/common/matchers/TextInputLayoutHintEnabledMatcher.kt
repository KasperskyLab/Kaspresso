@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.support.design.widget.TextInputLayout
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class TextInputLayoutHintEnabledMatcher(private val enabled: Boolean) : TypeSafeMatcher<View>() {
    override fun matchesSafely(item: View?): Boolean {
        return item?.let {
            if (item is TextInputLayout) {
                item.isHintEnabled == enabled
            } else false
        } ?: false
    }

    override fun describeTo(desc: Description) {
        desc.appendText("with hint state: ")
                .appendValue(enabled)
    }
}