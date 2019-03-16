@file:Suppress("unused")

package com.agoda.kakao.list

import com.agoda.kakao.delegates.DataInteractionDelegate

/**
 * For internal use. Don't use manually.
 *
 * Holds type and corresponding provider function
 */
class KAdapterItemType<out T : KAdapterItem<*>>(val provideItem: (DataInteractionDelegate) -> T)