package com.kaspersky.kaspresso.internal.extensions.other

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

private const val SECOND_IN_MILLIS = 1000
private const val MINUTE_IN_SECONDS = 60

/**
 * Converts milliseconds to triple of time components.
 *
 * @return triple of minutes, seconds and millis.
 */
internal fun Long.toTime(): Triple<Long, Long, Long> {
    val millis = this % SECOND_IN_MILLIS
    val second = this / SECOND_IN_MILLIS % MINUTE_IN_SECONDS
    val minute = this / (SECOND_IN_MILLIS * MINUTE_IN_SECONDS)
    return Triple(minute, second, millis)
}
