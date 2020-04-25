package com.kaspersky.kaspresso.interceptors.behavior

import androidx.test.espresso.DataInteraction

/**
 * The derived from [BehaviorInterceptor] interface for intercepting [DataInteraction.check] behavior.
 */
interface DataBehaviorInterceptor : BehaviorInterceptor<DataInteraction>
