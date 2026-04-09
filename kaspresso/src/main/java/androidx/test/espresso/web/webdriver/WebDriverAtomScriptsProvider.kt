package androidx.test.espresso.web.webdriver

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
 * Provides [WebDriverAtomScripts] types to descendants as they are with package-local access.
 */
internal open class WebDriverAtomScriptsProvider {

    protected val CLEAR_ANDROID: String = WebDriverAtomScripts.CLEAR_ANDROID

    protected val CLICK_ANDROID: String = WebDriverAtomScripts.CLICK_ANDROID

    protected val FIND_ELEMENT_ANDROID: String = WebDriverAtomScripts.FIND_ELEMENT_ANDROID

    protected val FIND_ELEMENTS_ANDROID: String = WebDriverAtomScripts.FIND_ELEMENTS_ANDROID

    protected val SCROLL_INTO_VIEW_ANDROID: String = WebDriverAtomScripts.SCROLL_INTO_VIEW_ANDROID

    protected val SEND_KEYS_ANDROID: String = WebDriverAtomScripts.SEND_KEYS_ANDROID

    protected val ACTIVE_ELEMENT_ANDROID: String = WebDriverAtomScripts.ACTIVE_ELEMENT_ANDROID

    protected val FRAME_BY_ID_OR_NAME_ANDROID: String = WebDriverAtomScripts.FRAME_BY_ID_OR_NAME_ANDROID

    protected val FRAME_BY_INDEX_ANDROID: String = WebDriverAtomScripts.FRAME_BY_INDEX_ANDROID

    protected val GET_VISIBLE_TEXT_ANDROID: String = WebDriverAtomScripts.GET_VISIBLE_TEXT_ANDROID
}
