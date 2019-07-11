package android.support.test.espresso.web.assertion

import android.webkit.WebView
import com.kaspersky.kaspresso.interceptors.WebAssertionInterceptor
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * A proxy-wrapper of [WebAssertion] for interceptors calls.
 */
class WebAssertionProxy<E>(
    private val webAssertion: WebAssertion<E>,
    private val matcher: Matcher<*>,
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

    /**
     * @return a string description of [WebAssertion].
     */
    fun describe(): String {
        val builder = StringBuilder("web assertion: ")
        matcher.describeTo(StringDescription(builder))
        return builder.toString()
    }
}