package com.kaspersky.components.uiautomatordsl.intercepting.intercept

data class UiInterception<T>(val isOverride: Boolean, val interceptor: T)