package com.kaspersky.components.uiautomator_dsl.dsl.common.builders

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker

/**
 * Class for building UiAutomator selectors
 *
 * This class helps to build selectors for UiAutomator views and get their interactions.
 *
 */
@UiAutomatorDslMarker
class UiViewBuilder {

    private var selector: BySelector? = null

    fun withId(packageName: String, resourceId: String) {
        selector = By.res(packageName, resourceId)
    }

    fun build(): BySelector {
        assertThat(selector).isNotNull()
        return selector as BySelector
    }
}