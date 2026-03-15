package androidx.test.espresso.web.assertion

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.webdriver.WebDriverAtomScriptsProvider
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * Uses [WebViewAssertions.ResultCheckingWebAssertion] class, that has package-local access in Espresso, so it has to be
 * in the same package.
 *
 * @return a string description of [WebAssertion].
 */
internal fun WebAssertion<*>.describeTo(builder: StringBuilder, result: Any) {
    when (this@describeTo) {
        is WebViewAssertions.ResultCheckingWebAssertion -> {
            builder.append(" \"${getResultMatcher().getResultDescription()}\"")
        }
        else -> {
            builder.append(" ${with(WebDriverAtomScriptsReceiver) { atom.getActionDescription() }}")
            if (result.toString() != "") {
                builder.append(" with result \"$result\"")
            }
        }
    }
}

private fun WebAssertion<*>.getResultMatcher(): Matcher<*> {
    return javaClass
        .getDeclaredField("resultMatcher")
        .apply { isAccessible = true }
        .get(this) as Matcher<*>
}

private fun Matcher<*>.getResultDescription(): String {
    return StringBuilder()
        .apply { this@getResultDescription.describeTo(StringDescription(this)) }
        .toString()
        .replace("is ", "element has text ")
        .replace("a string containing ", "element contains text ")
}

private object WebDriverAtomScriptsReceiver : WebDriverAtomScriptsProvider() {
    fun Atom<*>.getActionDescription(): String {
        return when (script) {
            GET_VISIBLE_TEXT_ANDROID -> "using web action=\"get visible text\""
            CLEAR_ANDROID -> "using web action=\"clear\""
            CLICK_ANDROID -> "using web action=\"click on element\""
            SCROLL_INTO_VIEW_ANDROID -> "using web action=\"scroll into view\""
            SEND_KEYS_ANDROID -> "using web action=\"end keys\""
            ACTIVE_ELEMENT_ANDROID -> "using web action=\"active element\""
            FRAME_BY_ID_OR_NAME_ANDROID -> "using web action=\"frame by id or name\""
            FRAME_BY_INDEX_ANDROID -> "using web action=\"frame by index android\""
            FIND_ELEMENT_ANDROID -> "using web action=\"find element\""
            FIND_ELEMENTS_ANDROID -> "using web action=\"find elements\""
            else -> ""
        }
    }
}
