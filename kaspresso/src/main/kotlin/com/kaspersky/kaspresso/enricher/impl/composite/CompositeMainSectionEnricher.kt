package com.kaspersky.kaspresso.enricher.impl.composite

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

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
 * The implementation of the [MainSectionEnricher] interface.
 * Composes all of [MainSectionEnricher]s list into one composite [MainSectionEnricher] that is actually
 * called by [com.kaspersky.kaspresso.testcases.core.TestRunner] on each test event.
 */
class CompositeMainSectionEnricher<Data>(
    private val mainSectionEnrichers: List<MainSectionEnricher<Data>>,
    private val exceptions: MutableList<Throwable>
) : MainSectionEnricher<Data> {

    override fun TestContext<Data>.beforeMainSectionRun(testInfo: TestInfo) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.run { beforeMainSectionRun(testInfo) } }
    }

    override fun TestContext<Data>.afterMainSectionRun(testInfo: TestInfo) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.run { afterMainSectionRun(testInfo) } }
    }
}
