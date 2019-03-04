package com.kaspersky.uitest_framework.kakao

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.ComponentName
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.Root
import android.support.test.espresso.intent.matcher.BundleMatchers
import android.support.test.espresso.intent.matcher.ComponentNameMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.matcher.UriMatchers
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import android.support.test.espresso.web.webdriver.DriverAtoms
import android.support.test.espresso.web.webdriver.Locator
import android.view.View
import com.agoda.kakao.*
import com.agoda.kakao.KView
import com.agoda.kakao.WebActions
import com.agoda.kakao.WebAssertions
import com.kaspersky.uitest_framework.kakao.dispatchers.ViewDispatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf

/**
 * Created by egor.kurnikov on 04.03.2019
 */

/**
 * Class for building view matchers and interactions
 *
 * This class helps to build matches for views and get their interactions.
 * Please note that any function invoking will add specific matcher to the list
 * and after that all of them will be combined with help of AllOf.allOf()
 *
 * @see AllOf.allOf()
 */
@BuilderMarker
open class ViewBuilder {
    protected val viewMatchers = arrayListOf<Matcher<View>>()

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
     * Returns view interaction based on all given matchers
     *
     * @return ViewInteraction
     */
    open fun getViewDispatcher(): ViewDispatcher {
        check(viewMatchers.isNotEmpty()) { "No matchers inside InteractionBuilder" }
        return ViewDispatcher(Espresso.onView(AllOf.allOf(viewMatchers)))
    }

    /**
     * Returns combined view matcher with AllOf.allOf()
     *
     * @return Matcher<View>
     */
    fun getViewMatcher(): Matcher<View> = AllOf.allOf(viewMatchers)
}

/**
 * Class for building root matchers
 *
 * This class helps to build matches for root.
 * Please note that any function invoking will add specific matcher to the list
 * and after that all of them will be combined with help of AllOf.allOf()
 *
 * @see AllOf.allOf()
 */
@BuilderMarker
class RootBuilder {
    private val rootMatchers = arrayListOf<Matcher<Root>>()

    /**
     * Matches root that is dialog
     */
    fun isDialog() {
        rootMatchers.add(RootMatchers.isDialog())
    }

    /**
     * Matches root that is not dialog
     */
    fun isNotDialog() {
        rootMatchers.add(Matchers.not(RootMatchers.isDialog()))
    }

    /**
     * Matches root that is focusable
     */
    fun isFocusable() {
        rootMatchers.add(RootMatchers.isFocusable())
    }

    /**
     * Matches root that is not focusable
     */
    fun isNotFocusable() {
        rootMatchers.add(Matchers.not(RootMatchers.isFocusable()))
    }

    /**
     * Matches root that is platform popup
     */
    fun isPlatformPopup() {
        rootMatchers.add(RootMatchers.isPlatformPopup())
    }

    /**
     * Matches root that is not platform popup
     */
    fun isNotPlatformPopup() {
        rootMatchers.add(Matchers.not(RootMatchers.isPlatformPopup()))
    }

    /**
     * Matches root that is touchable
     */
    fun isTouchable() {
        rootMatchers.add(RootMatchers.isTouchable())
    }

    /**
     * Matches root that is not touchable
     */
    fun isNotTouchable() {
        rootMatchers.add(Matchers.not(RootMatchers.isTouchable()))
    }

    /**
     * Matches root that has decor view matching given matcher
     *
     * @param function ViewBuilder which will result in decor view matcher
     */
    fun withDecorView(function: ViewBuilder.() -> Unit) {
        rootMatchers.add(RootMatchers.withDecorView(ViewBuilder().apply(function).getViewMatcher()))
    }

    /**
     * Matches root with given custom matcher
     *
     * @param matcher Custom root matcher to be added
     */
    fun withMatcher(matcher: Matcher<Root>) {
        rootMatchers.add(matcher)
    }

    /**
     * Returns combined root matchers with AllOf.allOf()
     *
     * @return Matcher<Root>
     */
    fun getRootMatcher(): Matcher<Root> = AllOf.allOf(rootMatchers)
}

/**
 * Class for building data matchers
 *
 * This class helps to build matches for data.
 * Please note that any function invoking will add specific matcher to the list
 * and after that all of them will be combined with help of AllOf.allOf()
 *
 * @see AllOf.allOf()
 */
@BuilderMarker
class DataBuilder {
    private val matchers = arrayListOf<Matcher<Any>>()

    /**
     * Matches data whose class matches given class
     *
     * @param clazz Class to be matched
     */
    fun isInstanceOf(clazz: Class<*>) {
        matchers.add(Matchers.`is`(Matchers.instanceOf(clazz)))
    }

    /**
     * Matches data which is equal to given object
     *
     * @param obj Any object that needs to be matched
     */
    fun equals(obj: Any) {
        matchers.add(Matchers.`is`(obj))
    }

    /**
     * Matches data which is not equal to given object
     *
     * @param obj Any object that needs to be matched
     */
    fun notEquals(obj: Any) {
        matchers.add(Matchers.not(Matchers.`is`(obj)))
    }

    /**
     * Matches data with given custom matcher
     *
     * @param matcher Custom matcher to be added
     */
    fun withMatcher(matcher: Matcher<Any>) {
        matchers.add(matcher)
    }

    /**
     * Returns combined data matchers with AllOf.allOf()
     *
     * @return Matcher<Any>
     */
    fun getDataMatcher(): Matcher<Any> = if (matchers.isNotEmpty()) AllOf.allOf(matchers) else Matchers.anything()
}

/**
 * Class for building WebView element matchers
 *
 * @param web WebInteraction where elements should be matched
 */
@BuilderMarker
class WebElementBuilder(private val web: Web.WebInteraction<*>) {
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

    inner class KWebInteraction(override val web: Web.WebInteraction<*>, override val ref: Atom<ElementReference>) :
            WebActions, WebAssertions
}

/**
 * Class for building Intent matchers
 */
@BuilderMarker
class IntentBuilder {
    private val matchers = arrayListOf<Matcher<Intent>>()
    private lateinit var anyMatcher: Matcher<Intent>
    private lateinit var result: ActivityResult

    /**
     * Matches any intent
     */
    fun any() {
        anyMatcher = IntentMatchers.anyIntent()
    }

    /**
     * Matches intent with given action
     *
     * @param action Action to be matched
     */
    fun hasAction(action: String) {
        matchers.add(IntentMatchers.hasAction(action))
    }

    /**
     * Matches intent with given action
     *
     * @param action Matcher for action string
     */
    fun hasAction(action: Matcher<String>) {
        matchers.add(IntentMatchers.hasAction(action))
    }

    /**
     * Matches intent with given categories
     *
     * @param categories Categories to be matched
     */
    fun hasCategories(vararg categories: String) {
        matchers.add(IntentMatchers.hasCategories(categories.toHashSet()))
    }

    /**
     * Matches intent with given categories
     *
     * @param categories Matcher for categories list
     */
    fun hasCategories(categories: Matcher<out Iterable<String>>) {
        matchers.add(IntentMatchers.hasCategories(categories))
    }

    /**
     * Matches intent which component has given class name
     *
     * @param className Class name to be matched in intent's component
     */
    fun hasComponent(className: String) {
        matchers.add(IntentMatchers.hasComponent(className))
    }

    /**
     * Matches intent with given component
     *
     * @param component Component name to be matched
     */
    fun hasComponent(component: ComponentName) {
        matchers.add(IntentMatchers.hasComponent(component))
    }

    /**
     * Matches intent with given component
     *
     * @param component Matcher for component name
     */
    fun hasComponent(component: Matcher<ComponentName>) {
        matchers.add(IntentMatchers.hasComponent(component))
    }

    /**
     * Matches intent with given component
     *
     * @param function Builder for a component to match
     */
    fun hasComponent(function: ComponentNameBuilder.() -> Unit) {
        matchers.add(IntentMatchers.hasComponent(ComponentNameBuilder().apply(function).getMatcher()))
    }

    /**
     * Matches intent with given data
     *
     * @param uri Uri to be matched
     */
    fun hasData(uri: String) {
        matchers.add(IntentMatchers.hasData(uri))
    }

    /**
     * Matches intent with given data
     *
     * @param uri Uri to be matched
     */
    fun hasData(uri: Uri) {
        matchers.add(IntentMatchers.hasData(uri))
    }

    /**
     * Matches intent with given data
     *
     * @param uri Matcher for the uri
     */
    fun hasData(uri: Matcher<Uri>) {
        matchers.add(IntentMatchers.hasData(uri))
    }

    /**
     * Matches intent with given data
     *
     * @param function Builder for a uri to match
     */
    fun hasData(function: UriBuilder.() -> Unit) {
        matchers.add(IntentMatchers.hasData(UriBuilder().apply(function).getMatcher()))
    }

    /**
     * Matches intent with given extra
     *
     * @param key Extra key
     * @param value Extra value
     */
    fun hasExtra(key: String, value: Any) {
        matchers.add(IntentMatchers.hasExtra(key, value))
    }

    /**
     * Matches intent with given extra
     *
     * @param key Matcher for extra key
     * @param value Matcher for extra value
     */
    fun hasExtra(key: Matcher<String>, value: Matcher<Any>) {
        matchers.add(IntentMatchers.hasExtra(key, value))
    }

    /**
     * Matches intent with given extra key
     *
     * @param key Extra key
     */
    fun hasExtraWithKey(key: String) {
        matchers.add(IntentMatchers.hasExtraWithKey(key))
    }

    /**
     * Matches intent with given extra key
     *
     * @param key Matcher for extra key
     */
    fun hasExtraWithKey(key: Matcher<String>) {
        matchers.add(IntentMatchers.hasExtraWithKey(key))
    }

    /**
     * Matches intent with given extras
     *
     * @param extras Matched for extras bundle
     */
    fun hasExtras(extras: Matcher<Bundle>) {
        matchers.add(IntentMatchers.hasExtras(extras))
    }

    /**
     * Matches intent with given extras
     *
     * @param function Builder for a bundle to match
     */
    fun hasExtras(function: BundleBuilder.() -> Unit) {
        matchers.add(IntentMatchers.hasExtras(BundleBuilder().apply(function).getMatcher()))
    }

    /**
     * Matches intent with given flag
     *
     * @param flag Flag to be matched
     */
    fun hasFlag(flag: Int) {
        matchers.add(IntentMatchers.hasFlag(flag))
    }

    /**
     * Matches intent with given flags
     *
     * @param flags Flags to be matched
     */
    fun hasFlags(flags: Int) {
        matchers.add(IntentMatchers.hasFlags(flags))
    }

    /**
     * Matches intent with given flags
     *
     * @param flags Flags to be matched
     */
    fun hasFlags(vararg flags: Int) {
        matchers.add(IntentMatchers.hasFlags(*flags))
    }

    /**
     * Matches intent with given type
     *
     * @param type Type to match
     */
    fun hasType(type: String) {
        matchers.add(IntentMatchers.hasType(type))
    }

    /**
     * Matches intent with given type
     *
     * @param type Matcher for type
     */
    fun hasType(type: Matcher<String>) {
        matchers.add(IntentMatchers.hasType(type))
    }

    /**
     * Matches intent with given package
     *
     * @param packageName Package name to match
     */
    fun hasPackage(packageName: String) {
        matchers.add(IntentMatchers.hasPackage(packageName))
    }

    /**
     * Matches intent with given package
     *
     * @param packageName Matcher for a package name
     */
    fun hasPackage(packageName: Matcher<String>) {
        matchers.add(IntentMatchers.hasPackage(packageName))
    }

    /**
     * Matches intent which addresses to given package
     *
     * @param packageName Package name to match
     */
    fun toPackage(packageName: String) {
        matchers.add(IntentMatchers.toPackage(packageName))
    }

    /**
     * Matches any internal intent
     */
    fun isInternal() {
        matchers.add(IntentMatchers.isInternal())
    }

    /**
     * Invoke this function if you want to set default result for intending intents
     *
     * @param function Builder for activity result
     */
    fun withResult(function: ActivityResultBuilder.() -> Unit) {
        result = ActivityResultBuilder().apply(function).getResult()
    }

    fun getMatcher(): Matcher<Intent> = if (::anyMatcher.isInitialized) anyMatcher else AllOf.allOf(matchers)

    fun getResult(): ActivityResult? = if (::result.isInitialized) result else null
}

/**
 * Class for building Uri matchers
 */
@BuilderMarker
class UriBuilder {
    private val matchers = arrayListOf<Matcher<Uri>>()

    /**
     * Matches uri with given host
     *
     * @param host Host to be matched
     */
    fun hasHost(host: String) {
        matchers.add(UriMatchers.hasHost(host))
    }

    /**
     * Matches uri with given host
     *
     * @param host Matcher for a host
     */
    fun hasHost(host: Matcher<String>) {
        matchers.add(UriMatchers.hasHost(host))
    }

    /**
     * Matches uri with given path
     *
     * @param path Path to be matched
     */
    fun hasPath(path: String) {
        matchers.add(UriMatchers.hasPath(path))
    }

    /**
     * Matches uri with given path
     *
     * @param path Matcher for a path
     */
    fun hasPath(path: Matcher<String>) {
        matchers.add(UriMatchers.hasPath(path))
    }

    /**
     * Matches uri with given scheme
     *
     * @param scheme Scheme to be matched
     */
    fun hasScheme(scheme: String) {
        matchers.add(UriMatchers.hasScheme(scheme))
    }

    /**
     * Matches uri with given scheme
     *
     * @param scheme Matcher for a scheme
     */
    fun hasScheme(scheme: Matcher<String>) {
        matchers.add(UriMatchers.hasScheme(scheme))
    }

    /**
     * Matches uri with given parameter name
     *
     * @param name Parameter name to be matched
     */
    fun hasParamWithName(name: String) {
        matchers.add(UriMatchers.hasParamWithName(name))
    }

    /**
     * Matches uri with given parameter name
     *
     * @param name Matcher for a parameter name
     */
    fun hasParamWithName(name: Matcher<String>) {
        matchers.add(UriMatchers.hasParamWithName(name))
    }

    /**
     * Matches uri with given parameter name and value
     *
     * @param name Parameter name to be matched
     * @param value Parameter value to be matched
     */
    fun hasParamWithValue(name: String, value: String) {
        matchers.add(UriMatchers.hasParamWithValue(name, value))
    }

    /**
     * Matches uri with given parameter name and value
     *
     * @param name Matcher for a parameter name
     * @param value Matcher for a paratemer value
     */
    fun hasParamWithValue(name: Matcher<String>, value: Matcher<String>) {
        matchers.add(UriMatchers.hasParamWithValue(name, value))
    }

    /**
     * Matches uri with given scheme and specific part
     *
     * @param scheme Scheme to be matched
     * @param part Specific part to be matched
     */
    fun hasSchemeSpecificPart(scheme: String, part: String) {
        matchers.add(UriMatchers.hasSchemeSpecificPart(scheme, part))
    }

    /**
     * Matches uri with given scheme and specific part
     *
     * @param scheme Matcher for a scheme
     * @param part Matcher for a specific part
     */
    fun hasSchemeSpecificPart(scheme: Matcher<String>, part: Matcher<String>) {
        matchers.add(UriMatchers.hasSchemeSpecificPart(scheme, part))
    }

    fun getMatcher(): Matcher<Uri> = AllOf.allOf(matchers)
}

/**
 * Class for building ComponentName matchers
 */
@BuilderMarker
class ComponentNameBuilder {
    private val matchers = arrayListOf<Matcher<ComponentName>>()

    /**
     * Matches component name with given class
     *
     * @param className Class name to be matched
     */
    fun hasClassName(className: String) {
        matchers.add(ComponentNameMatchers.hasClassName(className))
    }

    /**
     * Matches component name with given class
     *
     * @param className Matcher for a class name
     */
    fun hasClassName(className: Matcher<String>) {
        matchers.add(ComponentNameMatchers.hasClassName(className))
    }

    /**
     * Matches component name if it's package name the same as your app's
     */
    fun hasMyPackageName() {
        matchers.add(ComponentNameMatchers.hasMyPackageName())
    }

    /**
     * Matches component name with given package
     *
     * @param packageName Package name to be matched
     */
    fun hasPackageName(packageName: String) {
        matchers.add(ComponentNameMatchers.hasPackageName(packageName))
    }

    /**
     * Matches component name with given package
     *
     * @param packageName Matcher for a package name
     */
    fun hasPackageName(packageName: Matcher<String>) {
        matchers.add(ComponentNameMatchers.hasPackageName(packageName))
    }

    /**
     * Matches component name with given short class name
     *
     * @param className Short class name to be matched
     */
    fun hasShortClassName(className: String) {
        matchers.add(ComponentNameMatchers.hasShortClassName(className))
    }

    /**
     * Matches component name with given short class name
     *
     * @param className Matcher for a short class name
     */
    fun hasShortClassName(className: Matcher<String>) {
        matchers.add(ComponentNameMatchers.hasShortClassName(className))
    }

    fun getMatcher(): Matcher<ComponentName> = AllOf.allOf(matchers)
}

/**
 * Class for building Bundle matchers
 */
@BuilderMarker
class BundleBuilder {
    private val matchers = arrayListOf<Matcher<Bundle>>()

    /**
     * Matches bundle with given key
     *
     * @param key Key to be matched
     */
    fun hasKey(key: String) {
        matchers.add(BundleMatchers.hasKey(key))
    }

    /**
     * Matches bundle with given key
     *
     * @param key Matcher for a key
     */
    fun hasKey(key: Matcher<String>) {
        matchers.add(BundleMatchers.hasKey(key))
    }

    /**
     * Matches bundle with given value
     *
     * @param value Value to be matched
     */
    fun hasValue(value: Any) {
        matchers.add(BundleMatchers.hasValue(value))
    }

    /**
     * Matches bundle with given value
     *
     * @param value Matcher for a value
     */
    fun hasValue(value: Matcher<Any>) {
        matchers.add(BundleMatchers.hasValue(value))
    }

    /**
     * Matches bundle with given entry
     *
     * @param key Key to be matched
     * @param value Value to be matched
     */
    fun hasEntry(key: String, value: Any) {
        matchers.add(BundleMatchers.hasEntry(key, value))
    }

    /**
     * Matches bundle with given entry
     *
     * @param key Matcher for a key
     * @param value Matcher for a value
     */
    fun hasEntry(key: Matcher<String>, value: Matcher<Any>) {
        matchers.add(BundleMatchers.hasEntry(key, value))
    }

    fun getMatcher(): Matcher<Bundle> = AllOf.allOf(matchers)
}

/**
 * Class for building ActivityResult
 */
@BuilderMarker
class ActivityResultBuilder {
    private var code = Activity.RESULT_OK
    private var data: Intent? = null

    /**
     * Sets given result code
     *
     * @param code Result code to be saved
     */
    fun withCode(code: Int) {
        this.code = code
    }

    /**
     * Sets given result data
     *
     * @param data Result data to be saved
     */
    fun withData(data: Intent) {
        this.data = data
    }

    fun getResult() = ActivityResult(code, data)
}