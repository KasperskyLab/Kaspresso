@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.common.assertions

import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate

/**
 * Provides assertions for view with adapters
 *
 * @see RecyclerAdapterAssertions
 */
interface AdapterAssertions {
    val view: ViewInteractionDelegate
}