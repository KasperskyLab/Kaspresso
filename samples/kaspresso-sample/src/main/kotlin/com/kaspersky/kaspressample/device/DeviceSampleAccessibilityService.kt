package com.kaspersky.kaspressample.device

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class DeviceSampleAccessibilityService : AccessibilityService() {

    override fun onInterrupt() {
        // nothing
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // nothing
    }
}
