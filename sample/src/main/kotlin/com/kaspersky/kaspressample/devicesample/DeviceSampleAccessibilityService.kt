package com.kaspersky.kaspressample.devicesample

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class DeviceSampleAccessibilityService : AccessibilityService() {

    override fun onInterrupt() { }

    override fun onAccessibilityEvent(p0: AccessibilityEvent?) { }
}
