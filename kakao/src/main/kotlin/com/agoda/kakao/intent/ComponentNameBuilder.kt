@file:Suppress("unused")

package com.agoda.kakao.intent

import android.content.ComponentName
import android.support.test.espresso.intent.matcher.ComponentNameMatchers
import com.agoda.kakao.common.KakaoDslMarker
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

/**
 * Class for building ComponentName matchers
 */
@KakaoDslMarker
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