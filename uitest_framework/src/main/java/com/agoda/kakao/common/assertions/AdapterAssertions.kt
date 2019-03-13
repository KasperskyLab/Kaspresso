@file:Suppress("unused")

package com.agoda.kakao.common.assertions

import com.agoda.kakao.delegates.ViewInteractionDelegate

/**
 * Provides assertions for view with adapters
 *
 * @see RecyclerAdapterAssertions
 */
interface AdapterAssertions {
    val view: ViewInteractionDelegate
}