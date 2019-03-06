@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.list

import com.kaspersky.uitest_framework.kakao.dispatchers.DataDispatcher

/**
 * Empty implementation of KAdapterItem
 *
 * Use this if you want to perform/assert on the root view of adapter item
 *
 * @param parent Matcher of the root view of adapter item
 * @see KAdapterItem
 */
class KEmptyAdapterItem(parent: DataDispatcher) : KAdapterItem<KEmptyAdapterItem>(parent)