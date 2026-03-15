package androidx.test.espresso.web.webdriver

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.Evaluation
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.ACTIVE_ELEMENT_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.CLEAR_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.CLICK_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.FIND_ELEMENTS_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.FIND_ELEMENT_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.FRAME_BY_ID_OR_NAME_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.FRAME_BY_INDEX_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.GET_VISIBLE_TEXT_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.SCROLL_INTO_VIEW_ANDROID
import androidx.test.espresso.web.webdriver.WebDriverAtomScripts.SEND_KEYS_ANDROID

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
 * Uses [WebDriverAtomScripts] class, that has package-local access in Espresso, so it has to be in the same package.
 *
 * @return a string description of [Atom].
 */
internal fun Atom<*>.describeTo(builder: StringBuilder, evaluation: Evaluation?) {
    builder
        .append(" \"${getActionDescription()}\"")
        .apply {
            evaluation?.let { eval: Evaluation ->
                if (eval.hasMessage()) {
                    append(" with message=\"${eval.message}\"")
                }
                eval.value?.let { result: Any ->
                    append(" with result=\"$result\"")
                }
            }
        }
}

private fun Atom<*>.getActionDescription(): String {
    return when (script) {
        GET_VISIBLE_TEXT_ANDROID -> "get visible text"
        CLICK_ANDROID -> "click on element"
        SCROLL_INTO_VIEW_ANDROID -> "scroll into view"
        CLEAR_ANDROID -> "clear"
        SEND_KEYS_ANDROID -> "send keys"
        ACTIVE_ELEMENT_ANDROID -> "active element"
        FRAME_BY_ID_OR_NAME_ANDROID -> "frame by id or name"
        FRAME_BY_INDEX_ANDROID -> "frame by index android"
        FIND_ELEMENT_ANDROID -> "find element"
        FIND_ELEMENTS_ANDROID -> "find elements"
        else -> ""
    }
}
