package android.support.test.espresso.web.assertion

import android.support.test.espresso.web.sugar.Web
import android.webkit.WebView
import com.kaspersky.kaspresso.interceptors.view.WebAssertionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor
import com.kaspersky.kaspresso.proxy.InteractionProxy
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
    override val interaction: Web.WebInteraction<*>,
    private val interceptors: List<WebAssertionInterceptor>,
    override val interactors: List<WebInteractor>
) : WebAssertion<E>(webAssertion.atom), InteractionProxy<Web.WebInteraction<*>> {

    /**
     * Calls interceptors before [WebViewAssertions.ResultCheckingWebAssertion.checkResult] on wrapped [webAssertion] is
     * called.
     *
     * @param view a WebView that the Atom was evaluated on.
     * @param result a result of atom evaluation.
     */
    override fun checkResult(view: WebView?, result: E) {
        interceptors.forEach { it.intercept(this, view, result as Any) }
        interact(view) { (webAssertion as WebViewAssertions.ResultCheckingWebAssertion).checkResult(view, result) }
    }
}