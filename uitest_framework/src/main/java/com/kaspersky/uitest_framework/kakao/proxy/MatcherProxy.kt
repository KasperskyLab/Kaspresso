package com.kaspersky.uitest_framework.kakao.proxy

import com.kaspersky.uitest_framework.kakao.interceptors.MatcherInterceptor
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Created by egor.kurnikov on 06.03.2019
 */

//TODO finish me
class MatcherProxy<T>(
        private val matcher: Matcher<*>,
        private val interceptors: List<MatcherInterceptor>
): BaseMatcher<T>() {

    override fun describeTo(description: Description?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeMismatch(item: Any?, description: Description?) {
        super.describeMismatch(item, description)
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun matches(item: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}