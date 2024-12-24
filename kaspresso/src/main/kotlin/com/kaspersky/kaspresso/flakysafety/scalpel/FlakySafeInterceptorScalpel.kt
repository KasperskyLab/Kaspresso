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
import com.kaspersky.kaspresso.interceptors.tolibrary.KakaoLibraryInjector
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import java.util.concurrent.atomic.AtomicInteger

/**
 * The special class that removes all interceptors related to FlakySafety from kakao settings
 * and restore them by demand
 */
internal class FlakySafeInterceptorScalpel(
    private val kaspresso: Kaspresso,
    private val scalpelSwitcher: ScalpelSwitcher = ScalpelSwitcher()
) {
    private val entriesCount = AtomicInteger()

    fun scalpFromLibs() {
        if (entriesCount.getAndIncrement() == 0) { scalpelSwitcher.attemptTakeScalp(
            actionToDetermineScalp = { determineScalpExistingInKaspresso() },
            actionToTakeScalp = {
                scalpKakaoInterceptors()
                scalpKautomatorInterceptors()
                kaspresso.externalFlakySafetyScalperNotifier.scalpFlakySafety()
            }
        ) }
    }

    private fun determineScalpExistingInKaspresso() =
        kaspresso.viewBehaviorInterceptors.filterIsInstance<FlakySafeViewBehaviorInterceptor>().isNotEmpty() ||
                kaspresso.dataBehaviorInterceptors.filterIsInstance<FlakySafeDataBehaviorInterceptor>().isNotEmpty() ||
                kaspresso.webBehaviorInterceptors.filterIsInstance<FlakySafeWebBehaviorInterceptor>().isNotEmpty() ||
                kaspresso.objectBehaviorInterceptors.filterIsInstance<FlakySafeObjectBehaviorInterceptor>().isNotEmpty() ||
                kaspresso.deviceBehaviorInterceptors.filterIsInstance<FlakySafeDeviceBehaviorInterceptor>().isNotEmpty() ||
                kaspresso.externalFlakySafetyScalperNotifier.isAnyExternalFlakySafetyInterceptorPresent()

    private fun scalpKakaoInterceptors() {
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

        KakaoLibraryInjector.injectKaspressoInKakao(
            scalpedViewBehaviorInterceptors,
            scalpedDataBehaviorInterceptors,
            scalpedWebBehaviorInterceptors,
            kaspresso.viewActionWatcherInterceptors,
            kaspresso.viewAssertionWatcherInterceptors,
            kaspresso.atomWatcherInterceptors,
            kaspresso.webAssertionWatcherInterceptors,
            kaspresso.params.clickParams
        )
    }

    private fun scalpKautomatorInterceptors() {
        val scalpedObjectBehaviorInterceptors: List<ObjectBehaviorInterceptor> =
            kaspresso.objectBehaviorInterceptors.filter {
                it !is FlakySafeObjectBehaviorInterceptor
            }
        val scalpedDeviceBehaviorInterceptors: List<DeviceBehaviorInterceptor> =
            kaspresso.deviceBehaviorInterceptors.filter {
                it !is FlakySafeDeviceBehaviorInterceptor
            }

        KakaoLibraryInjector.injectKaspressoInKautomator(
            scalpedObjectBehaviorInterceptors,
            scalpedDeviceBehaviorInterceptors,
            kaspresso.objectWatcherInterceptors,
            kaspresso.deviceWatcherInterceptors
        )
    }

    fun restoreScalpToLibs() {
        val nestingDepth = entriesCount.decrementAndGet()
        if (nestingDepth <= 0) { // prevent restoring the interceptors in case if a "flakySafely" block is nested in an another "flakySafely"
            scalpelSwitcher.attemptRestoreScalp {
                KakaoLibraryInjector.injectKaspressoInKakao(
                    kaspresso.viewBehaviorInterceptors,
                    kaspresso.dataBehaviorInterceptors,
                    kaspresso.webBehaviorInterceptors,
                    kaspresso.viewActionWatcherInterceptors,
                    kaspresso.viewAssertionWatcherInterceptors,
                    kaspresso.atomWatcherInterceptors,
                    kaspresso.webAssertionWatcherInterceptors,
                    kaspresso.params.clickParams
                )

                KakaoLibraryInjector.injectKaspressoInKautomator(
                    kaspresso.objectBehaviorInterceptors,
                    kaspresso.deviceBehaviorInterceptors,
                    kaspresso.objectWatcherInterceptors,
                    kaspresso.deviceWatcherInterceptors
                )

                kaspresso.externalFlakySafetyScalperNotifier.restoreFlakySafety()
            }
        }
    }
}
