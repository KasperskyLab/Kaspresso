@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.common.matchers

import android.view.View
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches RecyclerView descendant at given position in adapter
 *
 * @param parent Matcher of the recycler view
 * @param position Position of item in adapter
 */
class PositionMatcher(private val parent: Matcher<View>, private val position: Int)
    : BoundedMatcher<View, View>(View::class.java) {

    override fun describeTo(desc: Description) {
        desc.appendText("view holder at $position position of recycler view: ")
                .appendDescriptionOf(parent)
    }

    override fun matchesSafely(view: View?): Boolean {
        view?.let {
            if (parent.matches(it.parent) && it.parent is RecyclerView) {
                val holder = (it.parent as RecyclerView).findViewHolderForAdapterPosition(position)
                return holder?.itemView === it
            }
        }

        return false
    }
}