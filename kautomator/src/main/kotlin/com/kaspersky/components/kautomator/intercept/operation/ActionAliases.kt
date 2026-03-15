package com.kaspersky.components.kautomator.intercept.operation

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2

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
 * Appropriate type aliases of UiOperation according to name paradigm in Kakao library (Assertions and Actions)
 */

typealias UiObjectAction = UiOperation<UiObject2>

typealias UiObjectAssertion = UiOperation<UiObject2>

typealias UiDeviceAction = UiOperation<UiDevice>

typealias UiDeviceAssertion = UiOperation<UiDevice>
