package com.kaspersky.uitest_framework.kakaoext

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.ScreenActions
import com.agoda.kakao.ScreenMarker

/**
 * Created by egor.kurnikov on 01.03.2019
 */

@Suppress("UNCHECKED_CAST")
@ScreenMarker
abstract class KLScreen<out T: KLScreen<T>>: ScreenActions {

    abstract val layoutId: Int?

    override val view: ViewInteraction = Espresso.onView(ViewMatchers.isRoot())

    operator fun invoke(function: T.() -> Unit) = function.invoke(this as T)
}