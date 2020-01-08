package com.kaspersky.kaspresso.interceptors.behavior_uia

import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiObjectAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiObjectAssertion
import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiObjectInteraction

interface ObjectBehaviorInterceptor :
    UiAutomatorDslBehaviorInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>