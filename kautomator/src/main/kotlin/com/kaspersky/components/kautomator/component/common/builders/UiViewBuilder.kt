@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.common.builders

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.BySelectorHack
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.common.resources.KId
import com.kaspersky.components.kautomator.common.resources.KString
import com.kaspersky.components.kautomator.component.common.KautomatorMarker
import java.util.regex.Pattern

/**
 * Class for building UiAutomator selectors
 *
 * This class helps to build selectors for UiAutomator views and get their interactions.
 *
 */
@Suppress("TooManyFunctions")
@KautomatorMarker
class UiViewBuilder {

    private var index: Int = 0
    private var selector: BySelector? = null

    /**
     * Matches only view at given [index], if there are multiple views that matches
     * @param index Index of the view to match
     * @param function [UiViewBuilder] that will result in matcher
     */
    fun withIndex(index: Int, function: UiViewBuilder.() -> Unit) {
        this.index = index
        function()
    }

    /**
     * Matches the view with given package name and resource id
     * @param packageName package name to match
     * @param resourceId id to match
     */
    fun withId(packageName: String, resourceId: String) = withResourceName(packageName, resourceId)

    /**
     * Matches the view with given resource id
     * @param resourceId id to match
     */
    fun withId(@IdRes resourceId: Int) {
        val packageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName
        val resName = KId.resolveResName(packageName, resourceId)
        return withResourceName(packageName, resName)
    }

    /**
     * Matches the view with given package name
     * @param packageName package name to match
     */
    fun withPackage(packageName: String) = addSelector { pkg(packageName) }

    /**
     * Matches the view with given package name
     * @param packageName package name to match
     */
    fun withPackage(packageName: Pattern) = addSelector { pkg(packageName) }

    /**
     * Matches the view if it is in ENABLED state
     */
    fun isEnabled() = addSelector { enabled(true) }

    /**
     * Matches the view if it is not in ENABLED state
     */
    fun isDisabled() = addSelector { enabled(false) }

    /**
     * Matches the view if it is in CHECKED state
     */
    fun isChecked() = addSelector { checked(true) }

    /**
     * Matches the view if it is not in CHECKED state
     */
    fun isNotChecked() = addSelector { checked(false) }

    /**
     * Matches the view if it is checkable
     */
    fun isCheckable() = addSelector { checkable(true) }

    /**
     * Matches the view if it is not checkable
     */
    fun isNotCheckable() = addSelector { checkable(false) }

    /**
     * Matches the view if it is clickable
     */
    fun isClickable() = addSelector { clickable(true) }

    /**
     * Matches the view if it is not clickable
     */
    fun isNotClickable() = addSelector { clickable(false) }

    /**
     * Matches the view if it is long clickable
     */
    fun isLongClickable() = addSelector { longClickable(true) }

    /**
     * Matches the view if it is not long clickable
     */
    fun isNotLongClickable() = addSelector { longClickable(false) }

    /**
     * Matches the view if it is focusable
     */
    fun isFocusable() = addSelector { focusable(true) }

    /**
     * Matches the view if it is not focusable
     */
    fun isNotFocusable() = addSelector { focusable(false) }

    /**
     * Matches the view if it is scrollable
     */
    fun isScrollable() = addSelector { scrollable(true) }

    /**
     * Matches the view if it is not scrollable
     */
    fun isNotScrollable() = addSelector { scrollable(false) }

    /**
     * Matches the view if it is selected
     */
    fun isSelected() = addSelector { selected(true) }

    /**
     * Matches the view if it is not selected
     */
    fun isNotSelected() = addSelector { selected(false) }

    /**
     * Matches the view with given content description
     *
     * @param contentDescription Content description to match
     */
    fun withContentDescription(contentDescription: String) =
        addSelector { desc(contentDescription) }

    /**
     * Matches the view with given content description
     *
     * @param contentDescriptionRes Content description to match
     */
    fun withContentDescription(@StringRes contentDescriptionRes: Int) = addSelector { desc(KString.getString(contentDescriptionRes)) }

    /**
     * Matches the view with given content description
     *
     * @param contentDescription Content description to match
     */
    fun withContentDescription(contentDescription: Pattern) =
        addSelector { desc(contentDescription) }

    /**
     * Matches the view with given text
     *
     * @param text Text to match
     */
    fun withText(text: String) = addSelector { text(text) }

    /**
     * Matches the view with given text
     *
     * @param textRes Text to match
     */
    fun withText(@StringRes textRes: Int) = addSelector { text(KString.getString(textRes)) }

    /**
     * Matches the view with given text
     *
     * @param text Text to match
     */
    fun withText(text: Pattern) = addSelector { text(text) }

    /**
     * Matches if the view does not have a given text
     *
     * @param text Text to be matched
     */
    fun withoutText(text: String) =
        addSelector { text(Regex("^((?!$text).)*\$").toPattern()) }

    /**
     * Matches if the view does not have a given text
     *
     * @param textRes Text to be matched
     */
    fun withoutText(@StringRes textRes: Int) = addSelector { text(Regex("^((?!${KString.getString(textRes)}).)*\$").toPattern()) }

    /**
     * Matches the view which contains any text
     */
    fun withAnyText() = addSelector { text(Regex("^.*\$").toPattern()) }

    /**
     * Matches the view which contain given text
     *
     * @param text Text to search
     */
    fun containsText(text: String) = addSelector { textContains(text) }

    /**
     * Matches the view which contain given text
     *
     * @param textRes Text to search
     */
    fun containsText(@StringRes textRes: Int) = addSelector { textContains(KString.getString(textRes)) }

    /**
     * Matches if the view which text starts with given text
     *
     * @param text Text to be matched
     */
    fun textStartsWith(text: String) = addSelector { textStartsWith(text) }

    /**
     * Matches if the view which text starts with given text
     *
     * @param textRes Text to be matched
     */
    fun textStartsWith(@StringRes textRes: Int) = addSelector { textStartsWith(KString.getString(textRes)) }

    /**
     * Matches if the view which text ends with given text
     *
     * @param text Text to be matched
     */
    fun textEndsWith(text: String) = addSelector { textEndsWith(text) }

    /**
     * Matches if the view which text ends with given text
     *
     * @param textRes Text to be matched
     */
    fun textEndsWith(@StringRes textRes: Int) = addSelector { textEndsWith(KString.getString(textRes)) }

    /**
     * Matches the view with given resource name
     *
     * @param name Resource name to match
     */
    fun withResourceName(name: String) = addSelector { res(name) }

    /**
     * Matches the view with given resource name
     *
     * @param pattern Pattern for resource name
     */
    fun withResourceName(pattern: Pattern) = addSelector { res(pattern) }

    /**
     * Matches the view with given resource name
     *
     * @param packageName package name to match
     * @param name Resource name to match
     */
    fun withResourceName(packageName: String, name: String) = addSelector { res(packageName, name) }

    /**
     * Matches the view which has descendant of given matcher
     *
     * @param function ViewBuilder which will result in descendant matcher
     */
    fun withDescendant(function: UiViewBuilder.() -> Unit) =
        addSelector { hasDescendant(UiViewBuilder().apply(function).build().bySelector) }

    /**
     * Matches the view which has descendant of given matcher with the maximum depth under the
     * element to search the descendant
     *
     * @param function ViewBuilder which will result in descendant matcher
     * @param maxDepth The maximum depth under the element to search the descendant
     */
    fun withDescendant(maxDepth: Int, function: UiViewBuilder.() -> Unit) =
        addSelector { hasDescendant(UiViewBuilder().apply(function).build().bySelector, maxDepth) }

    /**
     * Matches the view that is at a certain depth
     * @param exactDepth Exact depth
     */
    fun withDepth(exactDepth: Int) = addSelector { depth(exactDepth) }

    /**
     * Matches the view that is in a range of depths
     * @param min Minimal depth
     * @param max Maximal depth
     */
    fun withDepth(min: Int, max: Int) = addSelector { depth(min, max) }

    /**
     * Matches the view that is at least a certain depth
     * @param min Minimal depth
     */
    fun withMinDepth(min: Int) = addSelector { minDepth(min) }

    /**
     * Matches the view that is no more than a certain depth
     * @param max Maximal depth
     */
    fun withMaxDepth(max: Int) = addSelector { maxDepth(max) }

    /**
     * Matches the view which has child of given matcher
     *
     * @param function ViewBuilder which will result in child matcher
     */
    fun withChild(function: UiViewBuilder.() -> Unit) =
        addSelector { hasDescendant(UiViewBuilder().apply(function).build().bySelector) }

    /**
     * Matches the view which class matches given name
     *
     * @param clazz Class name
     */
    fun withClassName(clazz: String) = addSelector { clazz(clazz) }

    /**
     * Matches the view which class matches given name
     *
     * @param clazz Class name
     */
    fun withClassName(clazz: Pattern) = addSelector { clazz(clazz) }

    /**
     * Matches the view which class matches given name
     *
     * @param clazz The class to match
     */
    fun withClassName(clazz: Class<*>) = addSelector { clazz(clazz) }

    /**
     * Matches the view by class instance
     *
     * @param clazz Class to match
     */
    fun isInstanceOf(clazz: Class<*>) = addSelector { clazz(clazz.canonicalName) }

    /**
     * Matches the view with given custom [BySelector]
     *
     * @param selector [BySelector] public function
     */
    fun withSelector(selector: BySelector.() -> BySelector) = addSelector(selector)

    /**
     * Returns combined [BySelector] with all passed conditions
     */
    fun build(): UiViewSelector {
        assertThat(selector).isNotNull()
        return UiViewSelector(index, selector as BySelector)
    }

    private fun addSelector(condition: BySelector.() -> BySelector) {
        if (selector == null)
            selector = BySelectorHack.newInstance(condition)
        else
            condition(selector as BySelector)
    }
}
