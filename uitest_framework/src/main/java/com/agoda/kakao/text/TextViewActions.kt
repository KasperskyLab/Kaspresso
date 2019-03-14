@file:Suppress("unused")

package com.agoda.kakao.text

import android.net.Uri
import android.support.test.espresso.action.ViewActions
import com.agoda.kakao.common.actions.BaseActions
import org.hamcrest.Matcher

/**
 * Provides actions for TextViews
 */
interface TextViewActions : BaseActions {
    /**
     * @see ViewActions.openLinkWithText
     */
    fun openLinkWithText(text: String) {
        view.perform(ViewActions.openLinkWithText(text))
    }

    /**
     * @see ViewActions.openLinkWithText
     */
    fun openLinkWithText(text: Matcher<String>) {
        view.perform(ViewActions.openLinkWithText(text))
    }

    /**
     * @see ViewActions.openLinkWithUri
     */
    fun openLinkWithUri(uri: String) {
        view.perform(ViewActions.openLinkWithUri(uri))
    }

    /**
     * @see ViewActions.openLinkWithUri
     */
    fun openLinkWithUri(uri: Matcher<Uri>) {
        view.perform(ViewActions.openLinkWithUri(uri))
    }

    /**
     * @see ViewActions.openLink
     */
    fun openLink(text: Matcher<String>, uri: Matcher<Uri>) {
        view.perform(ViewActions.openLink(text, uri))
    }
}