@file:Suppress("unused")

package com.agoda.kakao.intent

import android.app.Instrumentation
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.test.espresso.intent.matcher.IntentMatchers
import com.agoda.kakao.common.KakaoDslMarker
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

/**
 * Class for building Intent matchers
 */
@KakaoDslMarker
class IntentBuilder {
    private val matchers = arrayListOf<Matcher<Intent>>()
    private lateinit var anyMatcher: Matcher<Intent>
    private lateinit var result: Instrumentation.ActivityResult

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

    fun getResult(): Instrumentation.ActivityResult? = if (::result.isInitialized) result else null
}