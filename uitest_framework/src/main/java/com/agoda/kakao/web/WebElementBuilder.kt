@file:Suppress("unused")

package com.agoda.kakao.web

import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.webdriver.DriverAtoms
import android.support.test.espresso.web.webdriver.Locator
import com.agoda.kakao.common.KakaoDslMarker
import com.agoda.kakao.delegates.WebInteractionDelegate

/**
 * Class for building WebView element matchers
 *
 * @param web WebInteractionDelegate where elements should be matched
 */
@KakaoDslMarker
class WebElementBuilder(private val web: WebInteractionDelegate) {
    /**
     * Looks up web view element and performs actions/assertions on it
     *
     * @param locator Locator of web view element
     * @param value Value to be searched for in web view
     * @param interaction Tail lambda where you can perform actions/assertions
     */
    fun withElement(locator: Locator, value: String, interaction: KWebInteraction.() -> Unit) {
        val ref = DriverAtoms.findElement(locator, value)
        KWebInteraction(web, ref).apply(interaction)
    }

    inner class KWebInteraction(
            override val web: WebInteractionDelegate,
            override val ref: Atom<ElementReference>
    ) : WebActions, WebAssertions
}