package com.kaspersky.kaspressonitrogen.flakysafety.scalpel

import com.kaspersky.kaspresso.flakysafety.scalpel.ScalpelSwitcher
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptorsInjector
import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen

internal class NitrogenFlakySafeInterceptorScalpel (
        private val kaspresso: KaspressoNitrogen
) {

    private val scalpelSwitcher: ScalpelSwitcher =
        ScalpelSwitcher()

    fun scalpFromLibs() {
        scalpelSwitcher.attemptTakeScalp(
                actionToDetermineScalp = { determineScalpExistingInKaspresso() },
                actionToTakeScalp = {
                    scalpKakaoInterceptors()
                }
        )
    }

    private fun determineScalpExistingInKaspresso() =
            kaspresso.viewBehaviorInterceptors.filterIsInstance<FlakySafeViewBehaviorInterceptor>().isNotEmpty() ||
                    kaspresso.dataBehaviorInterceptors.filterIsInstance<FlakySafeDataBehaviorInterceptor>().isNotEmpty() ||
                    kaspresso.webBehaviorInterceptors.filterIsInstance<FlakySafeWebBehaviorInterceptor>().isNotEmpty()

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

        LibraryInterceptorsInjector.injectKaspressoInKakao(
                scalpedViewBehaviorInterceptors,
                scalpedDataBehaviorInterceptors,
                scalpedWebBehaviorInterceptors,
                kaspresso.viewActionWatcherInterceptors,
                kaspresso.viewAssertionWatcherInterceptors,
                kaspresso.atomWatcherInterceptors,
                kaspresso.webAssertionWatcherInterceptors
        )
    }


    fun restoreScalpToLibs() {
        scalpelSwitcher.attemptRestoreScalp {
            LibraryInterceptorsInjector.injectKaspressoInKakao(
                    kaspresso.viewBehaviorInterceptors,
                    kaspresso.dataBehaviorInterceptors,
                    kaspresso.webBehaviorInterceptors,
                    kaspresso.viewActionWatcherInterceptors,
                    kaspresso.viewAssertionWatcherInterceptors,
                    kaspresso.atomWatcherInterceptors,
                    kaspresso.webAssertionWatcherInterceptors
            )
        }
    }
}

