package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2

/**
 * Appropriate type aliases of UiAction according to name paradigm in Kakao library
 */

typealias UiObjectAction = UiAction<UiObject2>

typealias UiObjectAssertion = UiAction<UiObject2>

typealias UiDeviceAction = UiAction<UiDevice>

typealias UiDeviceAssertion = UiAction<UiDevice>