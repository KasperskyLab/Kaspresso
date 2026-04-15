package com.kaspersky.kaspresso.instrumental

import android.app.Instrumentation

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

class InstrumentalDependencyProviderFactory {

    inline fun <reified T : Any> getComponentProvider(instrumentation: Instrumentation): InstrumentalDependencyProvider =
        InstrumentalDependencyProviderImpl(InstrumentalUsage.ComponentLocation(T::class.java.name), instrumentation)

    inline fun <reified T : Any> getInterceptorProvider(instrumentation: Instrumentation): InstrumentalDependencyProvider =
        InstrumentalDependencyProviderImpl(InstrumentalUsage.InterceptorLocation(T::class.java.name), instrumentation)

    fun getTestProvider(instrumentation: Instrumentation): InstrumentalDependencyProvider =
        InstrumentalDependencyProviderImpl(InstrumentalUsage.TestLocation, instrumentation)
}
