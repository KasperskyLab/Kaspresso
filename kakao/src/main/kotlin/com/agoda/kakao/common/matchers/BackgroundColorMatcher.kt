@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Matches given background color with the current one
 *
 * @param resId Background color resource to be matched (default is -1)
 * @param colorCode Background color string code to be matched (default is null)
 */
class BackgroundColorMatcher(@ColorRes private val resId: Int = -1,
                             private val colorCode: String? = null) : TypeSafeMatcher<View>() {

    override fun matchesSafely(item: View?): Boolean {
        if (resId == -1 && colorCode.isNullOrEmpty()) {
            return item?.background == null
        }

        return item?.let{
            val expectedColor = if (resId != -1) {
                ContextCompat.getColor(it.context, resId)
            } else {
                Color.parseColor(colorCode)
            }

            it.background != null &&
            it.background.current is ColorDrawable &&
            (it.background.current as ColorDrawable).color == expectedColor
        }?: false
    }

    override fun describeTo(description: Description) {
        description.appendText("with background color: $resId or $colorCode")
    }
}