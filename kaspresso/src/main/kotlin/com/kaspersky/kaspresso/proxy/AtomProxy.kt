package com.kaspersky.kaspresso.proxy

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.ElementReference
import androidx.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import org.hamcrest.Matcher

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
 * The proxy-wrapper of [Atom] for watcher interceptors calls.
 */
class AtomProxy<T>(
    val atom: Atom<T>,
    val matcher: Matcher<*>,
    private val watcherInterceptors: List<AtomWatcherInterceptor>
) : Atom<T> {

    /**
     * Simply calls [Atom.getArguments] on wrapped [atom].
     *
     * @param elementContext null unless an ElementReference has been supplied to interact this atom with.
     * @return a [List] of objects to pass to the script as arguments.
     */
    override fun getArguments(elementContext: ElementReference?): MutableList<Any> {
        return atom.getArguments(elementContext)
    }

    /**
     * Calls watcher interceptors before [Atom.transform] on wrapped [atom] is called.
     *
     * @param evaluation represents the results of a Javascript execution.
     * @return [R] a result type of the atom.
     */
    override fun transform(evaluation: Evaluation?): T {
        watcherInterceptors.forEach { it.intercept(this, evaluation) }
        return atom.transform(evaluation)
    }

    /**
     * Simply calls [Atom.getScript] on wrapped [atom].
     *
     * @return the script to be evaluated.
     */
    override fun getScript(): String = atom.script
}
