@file:Suppress("unused")

package com.agoda.kakao.screen

import android.view.View
import android.support.test.espresso.Espresso
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.common.KakaoDslMarker
import com.agoda.kakao.configurator.KakaoConfigurator
import com.agoda.kakao.delegates.ViewInteractionDelegate

/**
 * Container class for UI elements.
 *
 * This class groups UI elements and grants access to basic actions,
 * such as tapBack() and closeSoftKeyboard()
 *
 * @param T type of your screen, done to enable invoke() for its children
 *
 * @see ScreenActions
 */
@Suppress("UNCHECKED_CAST")
@KakaoDslMarker
open class Screen<out T: Screen<T>>: ScreenActions {
    override val view: ViewInteractionDelegate = KakaoConfigurator.createViewInteractionDelegate(
            Espresso.onView(ViewMatchers.isRoot())
    )

    operator fun invoke(function: T.() -> Unit) = function.invoke(this as T)

    companion object {
        /**
         * Idles for given amount of time
         *
         * @param duration Time to idle in milliseconds (1 second by default)
         */
        fun idle(duration: Long = 1000L) {
            Espresso.onView(ViewMatchers.isRoot()).perform(object : ViewAction {
                override fun getDescription() = "Idle for $duration milliseconds"

                override fun getConstraints() = ViewMatchers.isAssignableFrom(View::class.java)

                override fun perform(uiController: UiController?, view: View?) {
                    uiController?.loopMainThreadForAtLeast(duration)
                }
            })
        }

        inline fun <reified T : Screen<T>> onScreen(function: T.() -> Unit): T =
                T::class.java.newInstance().apply { function.invoke(this) }
    }
}