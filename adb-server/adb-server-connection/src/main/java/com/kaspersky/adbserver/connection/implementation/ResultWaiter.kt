package com.kaspersky.adbserver.connection.implementation

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

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
 * The waiter of a result
 */
internal class ResultWaiter<Result> {

    @Volatile
    private var result: Result? = null
    private val waitLatch = CountDownLatch(1)

    fun latchResult(result: Result) {
        this.result = result
        waitLatch.countDown()
    }

    @Throws(InterruptedException::class)
    fun waitResult(timeout: Long, unit: TimeUnit): Result? {
        waitLatch.await(timeout, unit)
        return result
    }
}
