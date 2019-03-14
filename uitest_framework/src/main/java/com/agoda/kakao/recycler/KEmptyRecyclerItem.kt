@file:Suppress("unused")

package com.agoda.kakao.recycler

import android.view.View
import org.hamcrest.Matcher

/**
 * Empty implementation of KRecyclerItem
 *
 * Use this if you want to perform/assert on the root view of view holder
 *
 * @param parent Matcher of the root view of view holder
 * @see KRecyclerItem
 */
class KEmptyRecyclerItem(parent: Matcher<View>) : KRecyclerItem<KEmptyRecyclerItem>(parent)