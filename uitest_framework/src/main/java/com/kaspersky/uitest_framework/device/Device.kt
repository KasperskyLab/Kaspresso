package com.kaspersky.uitest_framework.device

import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.uiautomator.*
import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.kakaoext.KLKeyboard
import com.kaspersky.uitest_framework.util.PERMISSION_ALLOW_BUTTON_ID
import com.kaspersky.uitest_framework.util.PERMISSION_DENY_BUTTON_ID
import com.kaspersky.uitest_framework.espressoext.viewactions.OrientationChangeAction
import com.kaspersky.uitest_framework.kakao.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.kakao.common.views.KView
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.kakao.proxy.ViewActionProxy

object Device {

    private val uiDevice: UiDevice =
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val interactionDelegate: ViewInteractionDelegate
        get() = ViewInteractionDelegate(Espresso.onView(ViewMatchers.isRoot()))

    private val rootElement: KView
        get() = KView { ViewMatchers.isRoot() }

    val appsManager: AppsManager =
            AppsManager(uiDevice) { InstrumentationRegistry.getInstrumentation().context }

    val activitiesManager: ActivitiesManager = ActivitiesManager()

    val keyboard = KLKeyboard()

    fun rotate() {

        val viewActionProxy = ViewActionProxy(
                OrientationChangeAction.toggle(activitiesManager.getResumedActivity()),
                InterceptorsHolder.viewActionInterceptors,
                InterceptorsHolder.executingInterceptor
        )

        interactionDelegate.perform(viewActionProxy)
    }

    fun handlePermissionRequest(shouldAllowPermissions: Boolean) {
        try {
            val btnSelector = UiSelector()
                    .clickable(true)
                    .checkable(false)
                    .resourceId(
                            if (shouldAllowPermissions)
                                PERMISSION_ALLOW_BUTTON_ID
                            else
                                PERMISSION_DENY_BUTTON_ID
                    )

            val btn = find(btnSelector)

            if (btn.exists()) {
                btn.click()
            }
        } catch (e: UiObjectNotFoundException) {
            Configuration.logger.e("There are no permissions dialog to interact with.")
        }
    }

    fun pressBack(failTestIfAppUnderTestClosed: Boolean = false) {

        if (failTestIfAppUnderTestClosed) {
            Espresso.pressBack()
        } else {
            Espresso.pressBackUnconditionally()
        }
    }

    fun pressHome(): Boolean = uiDevice.pressHome()

    fun <T> wait(condition: SearchCondition<T>, timeout: Long): T = uiDevice.wait(condition, timeout)

    fun find(selector: UiSelector): UiObject = uiDevice.findObject(selector)

    fun findWithText(text: String): UiObject = find(UiSelector().text(text))

    fun isSdkVersionHigherThan(version: Int): Boolean = Build.VERSION.SDK_INT >= version
}