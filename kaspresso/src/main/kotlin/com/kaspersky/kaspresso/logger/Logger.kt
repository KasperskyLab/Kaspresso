package com.kaspersky.kaspresso.logger

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
 * The interface for base logging with 3 levels: info, debug and error.
 */
interface Logger {

    /**
     * Info level of logging.
     */
    fun i(text: String)

    /**
     * Debug level of logging.
     */
    fun d(text: String)

    /**
     * Warning level of logging.
     */
    fun w(text: String)

    /**
     * Error level of logging.
     */
    fun e(text: String)

    /**
     * Info level of logging with tag.
     */
    fun i(tag: String, text: String)

    /**
     * Debug level of logging with tag.
     */
    fun d(tag: String, text: String)

    /**
     * Warning level of logging.
     */
    fun w(tag: String, text: String)

    /**
     * Error level of logging with tag.
     */
    fun e(tag: String, text: String)
}
