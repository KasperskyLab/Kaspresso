package com.kaspersky.kaspressample.jetpack_compose_screen

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.kaspersky.kaspressample.jetpackcompose.resources.C
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class ComposeSimpleFlakyScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeSimpleFlakyScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag(C.Tag.simple_flaky_screen_container) }
    ) {

    val firstButton: KNode = child {
        hasTestTag(C.Tag.simple_flaky_screen_simple_first_button)
    }

    val secondButton: KNode = child {
        hasTestTag(C.Tag.simple_flaky_screen_simple_second_button)
    }

    val editText: KNode = child {
        hasTestTag(C.Tag.simple_flaky_screen_simple_edittext)
    }
}
