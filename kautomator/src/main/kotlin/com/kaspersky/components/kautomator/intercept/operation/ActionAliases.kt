package com.kaspersky.components.kautomator.intercept.operation

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2

/**
 * Appropriate type aliases of UiOperation according to name paradigm in Kakao library (Assertions and Actions)
 */

typealias UiObjectAction = UiOperation<UiObject2>

typealias UiObjectAssertion = UiOperation<UiObject2>

typealias UiDeviceAction = UiOperation<UiDevice>

typealias UiDeviceAssertion = UiOperation<UiDevice>
