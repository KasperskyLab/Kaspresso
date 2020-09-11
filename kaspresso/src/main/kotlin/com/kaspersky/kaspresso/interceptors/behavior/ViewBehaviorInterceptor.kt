package com.kaspersky.kaspresso.interceptors.behavior

import androidx.test.espresso.ViewInteraction

/**
 * The derived from [BehaviorInterceptor] interface for intercepting [ViewInteraction.perform] and
 * [ViewInteraction.check] behavior.
 */
interface ViewBehaviorInterceptor : BehaviorInterceptor<ViewInteraction>
