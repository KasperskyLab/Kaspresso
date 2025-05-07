package com.kaspersky.components.kautomator.component.common.builders

import androidx.test.uiautomator.BySelector

data class UiViewSelector(
    val index: Int,
    val selectors: List<BySelector>,
) {
    constructor(
        index: Int,
        selector: BySelector
    ) : this(index, listOf(selector))
}
