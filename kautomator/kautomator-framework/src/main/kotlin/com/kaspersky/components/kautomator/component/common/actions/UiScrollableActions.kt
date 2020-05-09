@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.common.actions

import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.kaspersky.components.kautomator.component.common.views.UiBaseView
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

interface UiScrollableActions : UiBaseActions {

    fun scrollToStart() {
        view.perform(UiScrollableActionType.SCROLL_TO_START) {
            val scrollable = UiScrollable(UiSelector().resourceId(resourceName))
            scrollable.setAsVerticalList()
            scrollable.flingToBeginning(Int.MAX_VALUE)
        }
    }

    fun scrollToEnd() {
        view.perform(UiScrollableActionType.SCROLL_TO_END) {
            val scrollable = UiScrollable(UiSelector().resourceId(resourceName))
            scrollable.setAsVerticalList()
            scrollable.flingToEnd(Int.MAX_VALUE)
        }
    }

    fun <T> scrollToView(to: UiBaseView<T>) {
        view.perform(UiScrollableActionType.SCROLL_TO_VIEW) {
            val scrollable = UiScrollable(UiSelector().resourceId(resourceName))
            do {
                if (findObject(to.view.interaction.selector.bySelector) != null)
                    return@perform
            } while (scrollable.scrollForward())
            do {
                if (findObject(to.view.interaction.selector.bySelector) != null)
                return@perform
            } while (scrollable.scrollBackward())
            to.isDisplayed()
        }
    }

    enum class UiScrollableActionType : UiOperationType {
        SCROLL_TO_START,
        SCROLL_TO_END,
        SCROLL_TO_VIEW
    }
}
