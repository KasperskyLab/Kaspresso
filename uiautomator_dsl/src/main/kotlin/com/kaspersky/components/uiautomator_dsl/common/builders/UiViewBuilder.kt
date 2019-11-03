package com.kaspersky.components.uiautomator_dsl.common.builders

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import com.kaspersky.components.uiautomator_dsl.common.UiAutomatorDslMarker

@UiAutomatorDslMarker
class UiViewBuilder {

    private var selector: BySelector? = null

    fun withId(packageName: String, res: String) {
        selector = By.res(packageName, res)
    }

    fun build(): BySelector {
        checkNotNull(selector) { "ViewBuilder selector is undefined" }
        return selector as BySelector
    }
}