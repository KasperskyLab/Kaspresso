@file:Suppress("unused")

package com.agoda.kakao.recycler

import android.view.View
import com.agoda.kakao.common.KakaoDslMarker
import org.hamcrest.Matcher
import kotlin.reflect.KClass

/**
 * Class that maps types to providing functions
 *
 * To be able to support different item types in KRecyclerView, this class
 * adds support for mapping item type classes to functions that provide them.
 * KEmptyRecyclerItem is added by default.
 *
 * @see itemType
 * @see KEmptyRecyclerItem
 */
@KakaoDslMarker
class KRecyclerItemTypeBuilder {
    val itemTypes = mutableMapOf<KClass<out KRecyclerItem<*>>, KRecyclerItemType<KRecyclerItem<*>>>()

    init {
        itemTypes.put(KRecyclerItem::class, KRecyclerItemType { matcher -> KEmptyRecyclerItem(matcher) })
        itemTypes.put(KEmptyRecyclerItem::class, KRecyclerItemType { matcher -> KEmptyRecyclerItem(matcher) })
    }

    /**
     * Adds entry that helps KRecyclerView to automatically build child views
     *
     * To make it work, you need to pass here function (lambda, constructor), that takes matcher and returns
     * instance of your item type. In this case, matcher actually matches root view of your adapter item.
     *
     * @param provideItem Function that takes matcher of item's root view and returns instance of item view
     */
    inline fun <reified T : KRecyclerItem<*>> itemType(noinline provideItem: (Matcher<View>) -> T) {
        itemTypes.put(T::class, KRecyclerItemType(provideItem))
    }
}