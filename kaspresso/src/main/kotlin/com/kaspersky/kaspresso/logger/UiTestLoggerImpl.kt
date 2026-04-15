package com.kaspersky.kaspresso.logger

import android.util.Log

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
 * The default implementation of [UiTestLogger] using [android.util.Log].
 */
class UiTestLoggerImpl(
    private val tag: String
) : UiTestLogger {

    override fun i(text: String) {
        Log.i(tag, text)
    }

    override fun d(text: String) {
        Log.d(tag, text)
    }

    override fun w(text: String) {
        Log.w(tag, text)
    }

    override fun e(text: String) {
        Log.e(tag, text)
    }

    override fun i(tag: String, text: String) {
        Log.i(tag, text)
    }

    override fun d(tag: String, text: String) {
        Log.d(tag, text)
    }

    override fun w(tag: String, text: String) {
        Log.w(tag, text)
    }

    override fun e(tag: String, text: String) {
        Log.e(tag, text)
    }

    /**
     * Draws up info [i] as section block.
     */
    override fun section(text: String) {
        i("---------------------------------------------------------------------------")
        i(text)
        i("---------------------------------------------------------------------------")
    }

    /**
     * Draws up info [i] as header block.
     */
    override fun header(text: String) {
        line()
        i(text)
    }

    /**
     * Draws up info [i] as header block.
     */
    override fun footer(text: String) {
        i(text)
        line()
    }

    /**
     * Draws line info.
     */
    override fun line() {
        i("___________________________________________________________________________")
    }
}
