package com.kaspersky.kaspresso.flakysafety.scalpel

import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptorsImplementer
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptorsImplementer.implementKaspressoInKakao
import com.kaspersky.kaspresso.kaspresso.Kaspresso

internal object FlakySafeInterceptorScalpel {

    fun scalpFlakySafeInterceptorFromLibs(kaspresso: Kaspresso) {
        scalpKakaoInterceptors(kaspresso)
        scalpKautomatorInterceptors(kaspresso)
    }

    private fun scalpKakaoInterceptors(kaspresso: Kaspresso) {
        val scalpedViewBehaviorInterceptors: List<ViewBehaviorInterceptor> =
            kaspresso.viewBehaviorInterceptors.filter {
                it !is FlakySafeViewBehaviorInterceptor
            }
        val scalpedDataBehaviorInterceptors: List<DataBehaviorInterceptor> =
            kaspresso.dataBehaviorInterceptors.filter {
                it !is FlakySafeDataBehaviorInterceptor
            }
        val scalpedWebBehaviorInterceptors: List<WebBehaviorInterceptor> =
            kaspresso.webBehaviorInterceptors.filter {
                it !is FlakySafeWebBehaviorInterceptor
            }

        implementKaspressoInKakao(
            scalpedViewBehaviorInterceptors,
            scalpedDataBehaviorInterceptors,
            scalpedWebBehaviorInterceptors,
            kaspresso.viewActionWatcherInterceptors,
            kaspresso.viewAssertionWatcherInterceptors,
            kaspresso.atomWatcherInterceptors,
            kaspresso.webAssertionWatcherInterceptors
        )
    }

    private fun scalpKautomatorInterceptors(kaspresso: Kaspresso) {
        val scalpedObjectBehaviorInterceptors: List<ObjectBehaviorInterceptor> =
            kaspresso.objectBehaviorInterceptors.filter {
                it !is FlakySafeObjectBehaviorInterceptor
            }
        val scalpedDeviceBehaviorInterceptors: List<DeviceBehaviorInterceptor> =
            kaspresso.deviceBehaviorInterceptors.filter {
                it !is FlakySafeDeviceBehaviorInterceptor
            }

        LibraryInterceptorsImplementer.implementKaspressoInKautomator(
            scalpedObjectBehaviorInterceptors,
            scalpedDeviceBehaviorInterceptors,
            kaspresso.objectWatcherInterceptors,
            kaspresso.deviceWatcherInterceptors
        )
    }

    fun restoreFlakySafeInterceptorToLibs(kaspresso: Kaspresso) {
        implementKaspressoInKakao(
            kaspresso.viewBehaviorInterceptors,
            kaspresso.dataBehaviorInterceptors,
            kaspresso.webBehaviorInterceptors,
            kaspresso.viewActionWatcherInterceptors,
            kaspresso.viewAssertionWatcherInterceptors,
            kaspresso.atomWatcherInterceptors,
            kaspresso.webAssertionWatcherInterceptors
        )

        LibraryInterceptorsImplementer.implementKaspressoInKautomator(
            kaspresso.objectBehaviorInterceptors,
            kaspresso.deviceBehaviorInterceptors,
            kaspresso.objectWatcherInterceptors,
            kaspresso.deviceWatcherInterceptors
        )
    }
}