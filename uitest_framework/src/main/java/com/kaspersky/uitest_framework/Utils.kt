package com.kaspersky.uitest_framework

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.UiAutomation
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.*
import android.support.test.espresso.action.GeneralLocation
import android.support.test.espresso.action.GeneralSwipeAction
import android.support.test.espresso.action.Press
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.view.View
import android.widget.TextView
import com.agoda.kakao.*
import com.kaspersky.uitest_framework.matcher.CanScrollMatcher
import com.kaspersky.uitest_framework.matcher.ScrollDirection
import com.squareup.spoon.Spoon
import org.hamcrest.Matcher
import org.hamcrest.Matchers

fun makeScreenshot() {
    val activityInstance = getActivityInstance()
    if (activityInstance != null) {
        Spoon.screenshot(activityInstance, "screen")
    }
}

fun getActivityInstance(): Activity? {
    var currentActivity: Activity? = null
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
        val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
        for (act in resumedActivities) {
            currentActivity = act
            break
        }
    }
    return currentActivity
}

fun KBaseView<Any>.clickWithWait(timeout: Int = 25) {
    var bool = true
    var tryCount = timeout
    while (bool && tryCount > 0) {
        try {
            tryCount -= 1
            click()
            bool = false
        } catch (e: Exception) {
            if (tryCount <= 0) {
                makeScreenshot()
                throw e
            }
            Thread.sleep(500)
        }
    }
    makeScreenshot()
}

fun KBaseView<Any>.isVisibleWithWait(timeout: Int = 10) {
    var bool = true
    var tryCount = timeout
    while (bool && tryCount > 0) {
        try {
            tryCount -= 1
            isVisible()
            bool = false
        } catch (e: Exception) {
            if (tryCount <= 0) {
                makeScreenshot()
                throw e
            }
            Thread.sleep(500)
        }
    }
    makeScreenshot()
}


fun clickOnPermissionDialog(clickCount: Int = 1) {
    val PERMISSIONS_DIALOG_ALLOW_ID = "com.android.packageinstaller:id/permission_allow_button"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val allowPermissions = device.findObject(
            UiSelector()
                .clickable(true)
                .checkable(false)
                .resourceId(PERMISSIONS_DIALOG_ALLOW_ID)
        )
        if (allowPermissions.exists()) {
            repeat(clickCount) {
                allowPermissions.click()
            }
        }
    }
}

@TargetApi(Build.VERSION_CODES.N)
fun enableAccessibility() {
    val packageName = "com.kms.free"
    val className = "com.kaspersky.components.accessibility.KasperskyAccessibility"
    val string = "enabled_accessibility_services"
    val cmd = "settings put secure $string $packageName/$className"
    InstrumentationRegistry.getInstrumentation()
        .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
        .executeShellCommand(cmd)
        .close()

}


@SuppressLint("WifiManagerLeak")
fun setWiFiState(enable: Boolean) {
    val wifiManager = InstrumentationRegistry.getTargetContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
    wifiManager.isWifiEnabled = enable
}

fun setWiFiAndMobileState(b: Boolean) {
    setWiFiState(b)
    ServerClass.makeAdbRequest(
        "shell svc data ${if (b) {
            Thread.sleep(5000)
            "enable"
        } else "disable"}"
    )
}

fun silentlyRunAdbCommand(array: List<String>) {
    setWiFiState(enable = true)
    var count = 50
    while (count != 0) {
        Thread.sleep(200)
        count -= 1
    }
    for (str in array) {
        ServerClass.makeAdbRequest(str)
    }
    setWiFiState(enable = false)
}

fun TextViewActions.getText(): String {
    var stringHolder = "_"
    view.perform(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(TextView::class.java)
        }

        override fun getDescription(): String {
            return "getting text from a TextView"
        }

        override fun perform(uiController: UiController?, view: View?) {
            val tv = view as TextView
            stringHolder = tv.text.toString()
        }
    })
    return stringHolder
}


//        object: MouseAdapter() {
//
//            override fun mouseClicked(e: MouseEvent) { ... }

//fun enableAirplaneMode(enable: Boolean) {
//    val int = if (enable) 1 else 0
//    val cmd = "settings put global airplane_mode_on $int"
//    InstrumentationRegistry.getInstrumentation()
//        .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
//        .executeShellCommand(cmd)
//        .close()
//
//    Thread.sleep(3000)
//
//    val cmd1 = "am broadcast -a android.intent.action.AIRPLANE_MODE_CHANGED"
//    InstrumentationRegistry.getInstrumentation()
//        .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
//        .executeShellCommand(cmd1)
//        .close()
//}
//

class KSwitch : KBaseView<KSwitch>, CheckableActions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteraction, function: ViewBuilder.() -> Unit) : super(parent, function)
}

/**
 * Perform scroll actions to find view, that matches input builder
 * @return view interaction for next interaction
 */
fun BaseActions.scrollToView(
    direction: ScrollDirection = ScrollDirection.Up,
    childBuilder: ViewBuilder.() -> Unit
): ViewInteraction {
    return scrollToView(
        direction,
        ViewBuilder().apply(childBuilder).getViewMatcher()
    )
}

/**
 * Scroll to find view, that matches input matcher
 * @return view interaction for next interaction
 */
fun BaseActions.scrollToView(direction: ScrollDirection = ScrollDirection.Up, matcher: Matcher<View>): ViewInteraction {
    while (true) {
        try {
            return Espresso.onView(matcher).check(
                ViewAssertions.matches(ViewMatchers.isDisplayingAtLeast(90))
            )
        } catch (throwable: Throwable) {
            view.check(ViewAssertions.matches(CanScrollMatcher(direction)))
            val endCoordinates = when (direction) {
                ScrollDirection.Up -> GeneralLocation.TOP_CENTER
                ScrollDirection.Down -> GeneralLocation.BOTTOM_CENTER
            }
            val swipeBottom = GeneralSwipeAction(
                PreciseSwipe,
                GeneralLocation.CENTER,
                endCoordinates,
                Press.FINGER
            )
            act { swipeBottom }
            //Wait for scroll end
            Thread.sleep(1000L)
        }
    }
}

/**
 * Scroll to find child view
 * @return recycler view item of some registered type
 */
inline fun <reified T : KRecyclerItem<*>> KRecyclerView.scrollToChild(
    direction: ScrollDirection = ScrollDirection.Up,
    childMatcher: Matcher<View>
): T {
    val provideItem = itemTypes.getOrElse(T::class) {
        throw IllegalStateException("${T::class.java.simpleName} did not register to KRecyclerView")
    }.provideItem
    scrollToView(
        direction,
        Matchers.allOf(
            ViewMatchers.withParent(matcher),
            childMatcher
        )
    )
    return provideItem(ItemMatcher(matcher, childMatcher)) as T

}


/**
 * Scroll to find child view
 * @return recycler view item of some registered type
 */
inline fun <reified T : KRecyclerItem<*>> KRecyclerView.scrollToChild(
    direction: ScrollDirection = ScrollDirection.Up,
    childBuilder: ViewBuilder.() -> Unit
): T {
    val childMatcher = ViewBuilder().apply(childBuilder).getViewMatcher()
    return scrollToChild(direction, childMatcher)

}




