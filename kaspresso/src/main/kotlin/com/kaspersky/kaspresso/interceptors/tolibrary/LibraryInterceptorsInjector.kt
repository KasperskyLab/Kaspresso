package com.kaspersky.kaspresso.interceptors.tolibrary

import com.agoda.kakao.Kakao
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

internal object LibraryInterceptorsInjector {

    @Suppress("LongParameterList")
    fun injectKaspressoInKakao(
        viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
        dataBehaviorInterceptors: List<DataBehaviorInterceptor>,
        webBehaviorInterceptors: List<WebBehaviorInterceptor>,
        viewActionWatcherInterceptors: List<ViewActionWatcherInterceptor>,
        viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>,
        atomWatcherInterceptors: List<AtomWatcherInterceptor>,
        webAssertionWatcherInterceptors: List<WebAssertionWatcherInterceptor>
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