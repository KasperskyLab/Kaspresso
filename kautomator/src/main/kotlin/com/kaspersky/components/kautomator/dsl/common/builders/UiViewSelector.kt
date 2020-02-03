package com.kaspersky.components.kautomator.dsl.common.builders

import androidx.test.uiautomator.BySelector

data class UiViewSelector(
    val index: Int,
    val bySelector: BySelector
)