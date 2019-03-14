@file:Suppress("unused")

package com.agoda.kakao.intent

import android.net.Uri
import android.support.test.espresso.intent.matcher.UriMatchers
import com.agoda.kakao.common.KakaoDslMarker
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf

/**
 * Class for building Uri matchers
 */
@KakaoDslMarker
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