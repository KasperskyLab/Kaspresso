@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.list

import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate

/**
 * For internal use. Don't use manually.
 *
 * Holds type and corresponding provider function
 */
class KAdapterItemType<out T : KAdapterItem<*>>(val provideItem: (DataInteractionDelegate) -> T)