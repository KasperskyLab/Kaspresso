package com.kaspersky.components.composesupport.core.actions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.doubleClick
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performTouchInput
import com.kaspersky.components.composesupport.core.actions.options.DoubleClickConfig
import com.kaspersky.components.composesupport.core.actions.options.LongClickConfig
import io.github.kakaocup.compose.intercept.delegate.ComposeDelegate
import io.github.kakaocup.compose.intercept.operation.ComposeOperationType

interface KNodeActions {
    val delegate: ComposeDelegate

    /**
     * Performs a double click action on the element represented by the given semantics node.
     */
    fun doubleClick(doubleClickConfig: DoubleClickConfig? = null) {
        delegate.perform(ComposeKNodeActionType.PERFORM_DOUBLE_CLICK) {
            performTouchInput {
                doubleClick(
                    position = Offset(doubleClickConfig?.xOffset ?: centerX, doubleClickConfig?.yOffset ?: centerY),
                    delayMillis = doubleClickConfig?.delayMs ?: ((viewConfiguration.doubleTapMinTimeMillis + viewConfiguration.doubleTapTimeoutMillis) / 2)
                )
            }
        }
    }

    /**
     * Performs a long click action on the element represented by the given semantics node.
     */
    fun longClick(longClickConfig: LongClickConfig? = null) {
        delegate.perform(ComposeKNodeActionType.PERFORM_LONG_CLICK) {
            performTouchInput {
                longClick(
                    position = Offset(longClickConfig?.xOffset ?: centerX, longClickConfig?.yOffset ?: centerY),
                    durationMillis = longClickConfig?.durationMs ?: ((viewConfiguration.doubleTapMinTimeMillis + viewConfiguration.doubleTapTimeoutMillis) / 2)
                )
            }
        }
    }

    enum class ComposeKNodeActionType : ComposeOperationType {
        PERFORM_DOUBLE_CLICK,
        PERFORM_LONG_CLICK
    }
}
