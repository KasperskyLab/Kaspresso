package com.kaspersky.uitest_framework

import android.app.UiAutomation
import android.content.Context
import android.net.wifi.WifiManager
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.DataInteraction
import android.view.View
import com.agoda.kakao.*
import org.hamcrest.Matcher
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.widget.TextView

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
                throw e
            }
            Thread.sleep(500)
        }
    }
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
                throw e
            }
            Thread.sleep(500)
        }
    }

}



fun clickOnPermissionDialog(clickCount: Int = 1) {
    val GRANT_BUTTON_INDEX = 1
    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    val allowPermissions = device.findObject(UiSelector()
        .clickable(true)
        .checkable(false)
        .index(GRANT_BUTTON_INDEX))
    if (allowPermissions.exists()) {
        repeat(clickCount) {
            allowPermissions.click()
        }
    }
}

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

fun setWiFiState(enable: Boolean) {
    val wifiManager = InstrumentationRegistry.getTargetContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
    wifiManager.isWifiEnabled = enable
}

fun setWiFiAndMobileState(b: Boolean) {
    setWiFiState(b)
    ServerClass.sTestSingleton.makeAdbRequest("shell svc data ${ if (b) {
        Thread.sleep(5000)
        "enable"
    } else "disable" }")
}

fun silentlyRunAdbCommand(array: List<String>) {
    setWiFiState(enable = true)
    var count = 50
    while (count != 0) {
        Thread.sleep(200)
        count -= 1
    }
    for (str in array) {
        ServerClass.sTestSingleton.makeAdbRequest(str)
    }
    setWiFiState(enable = false)
}

fun TextViewActions.getText() : String {
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
