package com.kaspersky.kaspresso.interceptors.behavior

import android.support.test.espresso.web.sugar.Web

/**
 * The derived from [BehaviorInterceptor] interface for intercepting [Web.WebInteraction.perform] and
 * [Web.WebInteraction.check] behavior.
 */
interface WebBehaviorInterceptor : BehaviorInterceptor<Web.WebInteraction<*>>