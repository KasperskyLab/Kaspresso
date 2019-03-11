package com.kaspersky.uitest_framework.kakaoext

import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import com.kaspersky.uitest_framework.kakao.common.KakaoDslMarker
import com.kaspersky.uitest_framework.kakao.configuration.DelegatesFactory
import com.kaspersky.uitest_framework.kakao.screen.ScreenActions

@Suppress("UNCHECKED_CAST")
@KakaoDslMarker
abstract class ExtScreen<out T: ExtScreen<T>>: ScreenActions {

    abstract val layoutId: Int?

    abstract val viewClass: Class<out Any>?

    override val view = DelegatesFactory.createViewInteractionDelegate(
            Espresso.onView(ViewMatchers.isRoot())
    )

    operator fun invoke(function: T.() -> Unit) = function.invoke(this as T)
}