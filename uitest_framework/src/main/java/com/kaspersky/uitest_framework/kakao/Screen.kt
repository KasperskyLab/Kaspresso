package com.kaspersky.uitest_framework.kakao

import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.ScreenMarker
import com.kaspersky.uitest_framework.kakao.dispatchers.ViewDispatcher

/**
 * Created by egor.kurnikov on 05.03.2019
 */

@Suppress("UNCHECKED_CAST")
@ScreenMarker
abstract class Screen<out T: Screen<T>>: ScreenActions {

    override val view: ViewDispatcher = ViewDispatcher(Espresso.onView(ViewMatchers.isRoot()))

    operator fun invoke(function: T.() -> Unit) = function.invoke(this as T)
}