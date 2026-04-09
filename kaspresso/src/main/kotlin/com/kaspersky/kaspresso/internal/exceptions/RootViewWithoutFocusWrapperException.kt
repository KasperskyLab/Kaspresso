package com.kaspersky.kaspresso.internal.exceptions

import java.lang.RuntimeException

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
 * A wrapper for a RootViewWithoutFocusException. It's needed because RootViewWithoutFocusException is a private class,
 * so we have to use reflection to catch it and provide a user a sane way to control it.
 *
 * @see com.kaspersky.kaspresso.params.FlakySafetyParams.Companion.getDefaultAllowedExceptions
 * @see com.kaspersky.kaspresso.internal.extensions.other.ThrowableExtKt.isAllowed
 */
class RootViewWithoutFocusWrapperException : RuntimeException()
