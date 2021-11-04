package io.github.kakaocup.compose.intercept.operation

import androidx.compose.ui.test.SemanticsNodeInteraction

// Duplication of original typealiases from Kakao-Compose
// Original typealiases are unavailable. The reason is unclear.

typealias ComposeAction = (SemanticsNodeInteraction) -> Unit

typealias ComposeAssertion = (SemanticsNodeInteraction) -> Unit
