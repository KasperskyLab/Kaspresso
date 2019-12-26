package com.kaspersky.components.uiautomator_dsl.intercepting.intercept

data class UiInterception<T>(val isOverride: Boolean, val interceptor: T)