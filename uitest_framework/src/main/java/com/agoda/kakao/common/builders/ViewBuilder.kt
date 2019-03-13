@file:Suppress("unused")

package com.agoda.kakao.common.builders

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.delegates.ViewInteractionDelegate
import com.agoda.kakao.common.KakaoDslMarker
import com.agoda.kakao.common.matchers.*
import com.agoda.kakao.configuration.Configurator
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf

/**
 * Class for building view matchers and interactions
 *
 * This class helps to build matches for views and get their interactions.
 * Please note that any function invoking will add specific matcher to the list
 * and after that all of them will be combined with help of AllOf.allOf()
 *
 * @see AllOf.allOf()
 */
@KakaoDslMarker
class ViewBuilder {
    private val viewMatchers = arrayListOf<Matcher<View>>()

    /**
     * Matches only view at given [index], if there are multiple views that matches
     *
     * IMPORTANT: this matcher is single-use only, since it does not reset it's
     * index counter due to specific espresso's matching process. Thus only one action
     * and/or assertion can be performed on such a [KView].
     *
     * If you need to match view with index multiple times, each time you should match
     * with new instance of [withIndex]
     *
     * Take a look at the example:
     * ```
     *  class InputScreen : Screen<InputScreen>() {
     *      fun inputLayout(lambda: KEditText.() -> Unit) = KEditText { withIndex(0, { withId(R.id.input_layout) }) }.invoke(lambda)
     *  }
     *
     *  @Test
     *  fun test() {
     *      screen {
     *          inputLayout {
     *              replaceText("EXAMPLE")
     *          }
     *
     *          inputLayout {
     *              hasAnyText()
     *          }
     *      }
     *  }
     * ```
     *
     * @param index Index of the view to match
     * @param function [ViewBuilder] that will result in matcher
     */
    fun withIndex(index: Int, function: ViewBuilder.() -> Unit) {
        viewMatchers.add(IndexMatcher(ViewBuilder().apply(function).getViewMatcher(), index))
    }

    /**
     * Matches only root views
     *
     * @see ViewMatchers.isRoot
     */
    fun isRoot() {
        viewMatchers.add(ViewMatchers.isRoot())
    }

    /**
     * Matches the view with given resource id
     *
     * @param id Resource id to match
     */
    fun withId(id: Int) {
        viewMatchers.add(ViewMatchers.withId(id))
    }

    /**
     * Matches the view with given tag assigned
     *
     * @param tag Tag object to match
     */
    fun withTag(tag: Any) {
        viewMatchers.add(ViewMatchers.withTagValue(Matchers.equalTo(tag)))
    }

    /**
     * Matches the view if it is in ENABLED state
     */
    fun isEnabled() {
        viewMatchers.add(ViewMatchers.isEnabled())
    }

    /**
     * Matches the view if it is not in ENABLED state
     */
    fun isDisabled() {
        viewMatchers.add(CoreMatchers.not(ViewMatchers.isEnabled()))
    }

    /**
     * Matches the view with given text
     *
     * @param text Text to match
     */
    fun withText(text: String) {
        viewMatchers.add(ViewMatchers.withText(text))
    }

    /**
     * Matches the view with given text
     *
     * @param textId String resource to match
     */
    fun withText(@StringRes textId: Int) {
        viewMatchers.add(ViewMatchers.withText(textId))
    }

    /**
     * Matches the view with given text matcher
     *
     * @param matcher Text matcher to add
     */
    fun withText(matcher: Matcher<String>) {
        viewMatchers.add(ViewMatchers.withText(matcher))
    }

    /**
     * Matches if the view does not have a given text
     *
     * @param text Text to be matched
     */
    fun withoutText(text: String) {
        viewMatchers.add(CoreMatchers.not(ViewMatchers.withText(text)))
    }

    /**
     * Matches if the view does not have a given text
     *
     * @param resId String resource to be matched
     */
    fun withoutText(@StringRes resId: Int) {
        viewMatchers.add(CoreMatchers.not(ViewMatchers.withText(resId)))
    }

    /**
     * Matches the view which contains any text
     */
    fun withAnyText() {
        viewMatchers.add(AnyTextMatcher())
    }

    /**
     * Matches the view which contain given text
     *
     * @param text Text to search
     */
    fun containsText(text: String) {
        viewMatchers.add(ViewMatchers.withText(Matchers.containsString(text)))
    }

    /**
     * Matches the view with given resource name
     *
     * @param name Resource name to match
     */
    fun withResourceName(name: String) {
        viewMatchers.add(ViewMatchers.withResourceName(name))
    }

    /**
     * Matches the view by resource name with given matcher
     *
     * @param matcher Matcher for resource name
     */
    fun withResourceName(matcher: Matcher<String>) {
        viewMatchers.add(ViewMatchers.withResourceName(matcher))
    }

    /**
     * Matches the view with given content description
     *
     * @param description Content description to match
     */
    fun withContentDescription(description: String) {
        viewMatchers.add(ViewMatchers.withContentDescription(description))
    }

    /**
     * Matches the view with given content description
     *
     * @param resourceId Resource id of content description to match
     */
    fun withContentDescription(@StringRes resourceId: Int) {
        viewMatchers.add(ViewMatchers.withContentDescription(resourceId))
    }

    /**
     * Matches the view which has parent with given matcher
     *
     * @param function ViewBuilder which will result in parent matcher
     */
    fun withParent(function: ViewBuilder.() -> Unit) {
        viewMatchers.add(ViewMatchers.withParent(ViewBuilder().apply(function).getViewMatcher()))
    }

    /**
     * Matches the view with given drawable
     *
     * @param resId Drawable resource to match
     * @param toBitmap Lambda with custom Drawable -> Bitmap converter (default is null)
     */
    fun withDrawable(@DrawableRes resId: Int, toBitmap: ((drawable: Drawable) -> Bitmap)? = null) {
        viewMatchers.add(DrawableMatcher(resId = resId, toBitmap = toBitmap))
    }

    /**
     * Matches the view which is RatingBar with given value
     *
     * @param rating value of RatingBar
     */
    fun withRating(rating: Float) {
        viewMatchers.add(RatingBarMatcher(rating))
    }
    
    /**
     * Matches the view with given drawable
     *
     * @param drawable Drawable to match
     * @param toBitmap Lambda with custom Drawable -> Bitmap converter (default is null)
     */
    fun withDrawable(drawable: Drawable, toBitmap: ((drawable: Drawable) -> Bitmap)? = null) {
        viewMatchers.add(DrawableMatcher(drawable = drawable, toBitmap = toBitmap))
    }

    /**
     * Matches the view with given background color
     *
     * @param resId Color to match
     */
    fun withBackgroundColor(@ColorRes resId: Int) {
        viewMatchers.add(BackgroundColorMatcher(resId = resId))
    }

    /**
     * Matches the view with given background color code
     *
     * @param colorCode Color code to match
     */
    fun withBackgroundColor(colorCode: String) {
        viewMatchers.add(BackgroundColorMatcher(colorCode = colorCode))
    }

    /**
     * Matches the first view
     */
    fun isFirst() {
        viewMatchers.add(FirstViewMatcher())
    }

    /**
     * Matches the view with VISIBLE visibility
     */
    fun isVisible() {
        viewMatchers.add(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
    }

    /**
     * Matches the view with INVISIBLE visibility
     */
    fun isInvisible() {
        viewMatchers.add(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE))
    }

    /**
     * Matches the view with GONE visibility
     */
    fun isGone() {
        viewMatchers.add(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))
    }

    /**
     * Matches the view that is displayed
     */
    fun isDisplayed() {
        viewMatchers.add(ViewMatchers.isDisplayed())
    }

    /**
     * Matches the view that is not displayed
     */
    fun isNotDisplayed() {
        viewMatchers.add(Matchers.not(ViewMatchers.isDisplayed()))
    }

    /**
     * Matches the view that is completely displayed
     */
    fun isCompletelyDisplayed() {
        viewMatchers.add(ViewMatchers.isCompletelyDisplayed())
    }

    /**
     * Matches the view that is not completely displayed
     */
    fun isNotCompletelyDisplayed() {
        viewMatchers.add(Matchers.not(ViewMatchers.isCompletelyDisplayed()))
    }

    /**
     * Matches the view that is clickable
     */
    fun isClickable() {
        viewMatchers.add(ViewMatchers.isClickable())
    }

    /**
     * Matches the view that is not clickable
     */
    fun isNotClickable() {
        viewMatchers.add(Matchers.not(ViewMatchers.isClickable()))
    }

    /**
     * Matches the view which is descendant of given matcher
     *
     * @param function ViewBuilder which will result in parent matcher
     */
    fun isDescendantOfA(function: ViewBuilder.() -> Unit) {
        viewMatchers.add(ViewMatchers.isDescendantOfA(ViewBuilder().apply(function).getViewMatcher()))
    }

    /**
     * Matches the view which has descendant of given matcher
     *
     * @param function ViewBuilder which will result in descendant matcher
     */
    fun withDescendant(function: ViewBuilder.() -> Unit) {
        viewMatchers.add(ViewMatchers.hasDescendant(ViewBuilder().apply(function).getViewMatcher()))
    }

    /**
     * Matches the view which has sibling of given matcher
     *
     * @param function ViewBuilder which will result in sibling matcher
     */
    fun withSibling(function: ViewBuilder.() -> Unit) {
        viewMatchers.add(ViewMatchers.hasSibling(ViewBuilder().apply(function).getViewMatcher()))
    }

    /**
     * Matches the view which class name matches given matcher
     *
     * @param matcher Matcher of class name
     */
    fun withClassName(matcher: Matcher<String>) {
        viewMatchers.add(ViewMatchers.withClassName( matcher))
    }

    /**
     * Matches the view by class instance
     *
     * @param clazz Class to match
     */
    fun isInstanceOf(clazz: Class<*>) {
        viewMatchers.add(Matchers.instanceOf(clazz))
    }

    /**
     * Matches the view with given custom matcher
     *
     * @param matcher Matcher that needs to be added
     */
    fun withMatcher(matcher: Matcher<View>) {
        viewMatchers.add(matcher)
    }

    /**
     * Returns view interaction delegate based on all given matchers
     *
     * @return ViewInteractionDelegate
     */
    fun getViewInteractionDelegate(): ViewInteractionDelegate {
        check(viewMatchers.isNotEmpty()) { "No matchers inside InteractionBuilder" }
        return Configurator.viewInteractionDelegateFactory.invoke(
                Espresso.onView(AllOf.allOf(viewMatchers))
        )
    }

    /**
     * Returns combined view matcher with AllOf.allOf()
     *
     * @return Matcher<View>
     */
    fun getViewMatcher(): Matcher<View> = AllOf.allOf(viewMatchers)
}

