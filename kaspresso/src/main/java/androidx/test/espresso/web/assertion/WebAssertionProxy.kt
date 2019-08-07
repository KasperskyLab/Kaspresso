package androidx.test.espresso.web.assertion

import android.webkit.WebView
import com.kaspersky.kaspresso.interceptors.view.WebAssertionInterceptor
import org.hamcrest.Matcher

/**
 * A proxy-wrapper of [WebAssertion] for interceptors calls.
 *
 * Uses [WebViewAssertions.ResultCheckingWebAssertion] class, that has package-local access in Espresso, so it has to be
 * in the same package.
 */
class WebAssertionProxy<E>(
    val webAssertion: WebAssertion<E>,
    val matcher: Matcher<*>,
    private val interceptors: List<WebAssertionInterceptor>
) : WebAssertion<E>(webAssertion.atom) {

    /**
     * Calls interceptors before [WebViewAssertions.ResultCheckingWebAssertion.checkResult] on wrapped [webAssertion] is
     * called.
     *
     * @param view a WebView that the Atom was evaluated on.
     * @param result a result of atom evaluation.
     */
    override fun checkResult(view: WebView?, result: E) {
        interceptors.forEach { it.intercept(this, view, result as Any) }
        (webAssertion as WebViewAssertions.ResultCheckingWebAssertion).checkResult(view, result)
    }
}