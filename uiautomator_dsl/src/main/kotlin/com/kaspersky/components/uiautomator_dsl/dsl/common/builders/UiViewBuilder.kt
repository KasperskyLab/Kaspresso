package com.kaspersky.components.uiautomator_dsl.dsl.common.builders

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker

@UiAutomatorDslMarker
class UiViewBuilder {

    private var selector: BySelector? = null

    fun withId(packageName: String, res: String) {
        selector = By.res(packageName, res)
    }

    fun build(): BySelector {
        assertThat(selector).isNotNull()
        return selector as BySelector
    }
}