package com.kaspersky.components.kautomator.intercept.base

data class UiInterception<T>(val isOverride: Boolean, val interceptor: T)