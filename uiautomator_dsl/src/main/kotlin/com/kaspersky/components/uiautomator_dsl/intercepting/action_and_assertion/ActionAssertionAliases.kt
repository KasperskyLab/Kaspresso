package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction

typealias UiAction = UiOperation<UiInteraction>

typealias UiAssertion = UiOperation<UiInteraction>

typealias UiDeviceAction = UiOperation<UiDeviceInteraction>

typealias UiDeviceAssertion = UiOperation<UiDeviceInteraction>