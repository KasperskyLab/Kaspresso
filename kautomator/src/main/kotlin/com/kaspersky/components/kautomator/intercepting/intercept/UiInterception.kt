package com.kaspersky.components.kautomator.intercepting.intercept

data class UiInterception<T>(val isOverride: Boolean, val interceptor: T)