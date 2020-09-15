package androidx.test.espresso.web.assertion

import android.webkit.WebView
import com.kaspersky.kaspresso.interceptors.watcher.view.WebAssertionWatcherInterceptor
import org.hamcrest.Matcher

/**
 * A proxy-wrapper of [WebAssertion] for [watcherInterceptors] calls.
 *
 * Uses [WebViewAssertions.ResultCheckingWebAssertion] class, that has package-local access in Espresso, so it has to be
 * in the same package.
 */
class WebAssertionProxy<E>(
    val webAssertion: WebAssertion<E>,
    val matcher: Matcher<*>,
    private val watcherInterceptors: List<WebAssertionWatcherInterceptor>
) : WebAssertion<E>(webAssertion.atom) {

    /**
     * Calls [watcherInterceptors] before [WebViewAssertions.ResultCheckingWebAssertion.checkResult] on wrapped
     * [webAssertion] is called.
     *
     * @param view a WebView that the Atom was evaluated on.
     * @param result a result of atom evaluation.
     */
    override fun checkResult(view: WebView?, result: E) {
        watcherInterceptors.forEach { it.intercept(this, view, result as Any) }
        (webAssertion as WebViewAssertions.ResultCheckingWebAssertion).checkResult(view, result)
    }
}
