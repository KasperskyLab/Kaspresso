package com.kaspersky.kaspresso.interceptors.tolibrary

import io.github.kakaocup.kakao.Kakao
import com.kaspersky.components.kautomator.KautomatorConfigurator
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kakao.KakaoDataInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kakao.KakaoViewInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kakao.KakaoWebInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kautomator.KautomatorDeviceInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kautomator.KautomatorObjectInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.DeviceWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.ObjectWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.WebAssertionWatcherInterceptor
import com.kaspersky.kaspresso.params.ClickParams

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

internal object KakaoLibraryInjector {

    @Suppress("LongParameterList")
    fun injectKaspressoInKakao(
        viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
        dataBehaviorInterceptors: List<DataBehaviorInterceptor>,
        webBehaviorInterceptors: List<WebBehaviorInterceptor>,
        viewActionWatcherInterceptors: List<ViewActionWatcherInterceptor>,
        viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>,
        atomWatcherInterceptors: List<AtomWatcherInterceptor>,
        webAssertionWatcherInterceptors: List<WebAssertionWatcherInterceptor>,
        clickParams: ClickParams
    ) {
        val viewInterceptor =
            KakaoViewInterceptor(
                viewBehaviorInterceptors,
                viewActionWatcherInterceptors,
                viewAssertionWatcherInterceptors
            )
        val dataInterceptor =
            KakaoDataInterceptor(
                dataBehaviorInterceptors,
                viewAssertionWatcherInterceptors
            )
        val webInterceptor =
            KakaoWebInterceptor(
                webBehaviorInterceptors,
                webAssertionWatcherInterceptors,
                atomWatcherInterceptors
            )

        Kakao.intercept {
            onViewInteraction {
                onCheck(isOverride = true, interceptor = viewInterceptor::interceptCheck)
                onPerform(isOverride = true, interceptor = viewInterceptor::interceptPerform)
            }
            onDataInteraction {
                onCheck(isOverride = true, interceptor = dataInterceptor::interceptCheck)
            }
            onWebInteraction {
                onCheck(isOverride = true, interceptor = webInterceptor::interceptCheck)
                onPerform(isOverride = true, interceptor = webInterceptor::interceptPerform)
            }
        }

        Kakao {
            singleClickAction = clickParams.singleClickAction
            doubleClickAction = clickParams.doubleClickAction
            longClickAction = clickParams.longClickAction
        }
    }

    fun injectKaspressoInKautomator(
        objectBehaviorInterceptors: List<ObjectBehaviorInterceptor>,
        deviceBehaviorInterceptors: List<DeviceBehaviorInterceptor>,
        objectWatcherInterceptors: List<ObjectWatcherInterceptor>,
        deviceWatcherInterceptors: List<DeviceWatcherInterceptor>
    ) {
        val objectInterceptor =
            KautomatorObjectInterceptor(
                objectBehaviorInterceptors,
                objectWatcherInterceptors
            )
        val deviceInterceptor =
            KautomatorDeviceInterceptor(
                deviceBehaviorInterceptors,
                deviceWatcherInterceptors
            )

        KautomatorConfigurator.intercept {
            onUiInteraction {
                onCheck(isOverride = true, interceptor = objectInterceptor::interceptCheck)
                onPerform(isOverride = true, interceptor = objectInterceptor::interceptPerform)
            }
            onUiDeviceInteraction {
                onCheck(isOverride = true, interceptor = deviceInterceptor::interceptCheck)
                onPerform(isOverride = true, interceptor = deviceInterceptor::interceptPerform)
            }
        }
    }
}
