@file:Suppress("unused")

package com.agoda.kakao.list

import com.agoda.kakao.common.KakaoDslMarker
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf

/**
 * Class for building data matchers
 *
 * This class helps to build matches for data.
 * Please note that any function invoking will add specific matcher to the list
 * and after that all of them will be combined with help of AllOf.allOf()
 *
 * @see AllOf.allOf()
 */
@KakaoDslMarker
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