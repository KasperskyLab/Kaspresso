package com.kaspersky.kaspresso.kaspresso

/**
 * Removes all interceptors matching [predicate] from every interceptor list in this [Kaspresso.Builder].
 */
fun Kaspresso.Builder.removeInterceptors(predicate: (Any) -> Boolean) {
    listOf(
        viewBehaviorInterceptors,
        dataBehaviorInterceptors,
        webBehaviorInterceptors,
        objectBehaviorInterceptors,
        deviceBehaviorInterceptors,
        viewActionWatcherInterceptors,
        viewAssertionWatcherInterceptors,
        atomWatcherInterceptors,
        webAssertionWatcherInterceptors,
        objectWatcherInterceptors,
        deviceWatcherInterceptors,
        stepWatcherInterceptors,
        testRunWatcherInterceptors,
    ).forEach { list ->
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            if (predicate(iterator.next())) iterator.remove()
        }
    }
}

/**
 * Removes all interceptors of type [T] from every interceptor list in this [Kaspresso.Builder].
 *
 * Example:
 * ```
 * Kaspresso.Builder.simple().apply {
 *     removeInterceptors<SystemDialogSafetyProvider>()
 * }
 * ```
 *
 * Commonly used provider interfaces and the effect of disabling them:
 * - [com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider] — actions and assertions no longer
 *   retry on failure; the test fails immediately on the first error.
 * - [com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider] — system dialogs
 *   (permission requests, crash dialogs, etc.) are no longer dismissed automatically and will
 *   block test execution.
 * - [com.kaspersky.kaspresso.autoscroll.AutoScrollProvider] — views that are off-screen are no
 *   longer scrolled to automatically; the test fails if the target view is not already visible.
 * - [com.kaspersky.kaspresso.elementloader.ElementLoaderProvider] — stale Kautomator elements are
 *   no longer reloaded automatically; the test fails without attempting to re-find the element.
 */
inline fun <reified T> Kaspresso.Builder.removeInterceptors() = removeInterceptors { it is T }
