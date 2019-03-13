@file:Suppress("unused")

package com.agoda.kakao.intent

import android.os.Bundle
import android.support.test.espresso.intent.matcher.BundleMatchers
import com.agoda.kakao.common.KakaoDslMarker
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

/**
 * Class for building Bundle matchers
 */
@KakaoDslMarker
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