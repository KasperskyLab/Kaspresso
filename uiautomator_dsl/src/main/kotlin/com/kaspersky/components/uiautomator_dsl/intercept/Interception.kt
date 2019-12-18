package com.kaspersky.components.uiautomator_dsl.intercept

data class Interception<T>(val isOverride: Boolean, val interceptor: T)