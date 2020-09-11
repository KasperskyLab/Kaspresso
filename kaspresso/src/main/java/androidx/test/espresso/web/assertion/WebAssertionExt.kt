package androidx.test.espresso.web.assertion

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.webdriver.WebDriverAtomScriptsProvider
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * Uses [WebViewAssertions.ResultCheckingWebAssertion] class, that has package-local access in Espresso, so it has to be
 * in the same package.
 *
 * @return a string description of [WebAssertion].
 */
internal fun WebAssertion<*>.describeTo(builder: StringBuilder, result: Any) {
    when (this@describeTo) {
        is WebViewAssertions.ResultCheckingWebAssertion -> {
            builder.append(" \"${getResultMatcher().getResultDescription()}\"")
        }
        else -> {
            builder.append(" ${with(WebDriverAtomScriptsReceiver) { atom.getActionDescription() }}")
            if (result.toString() != "") {
                builder.append(" with result \"$result\"")
            }
        }
    }
}

private fun WebAssertion<*>.getResultMatcher(): Matcher<*> {
    return javaClass
        .getDeclaredField("resultMatcher")
        .apply { isAccessible = true }
        .get(this) as Matcher<*>
}

private fun Matcher<*>.getResultDescription(): String {
    return StringBuilder()
        .apply { this@getResultDescription.describeTo(StringDescription(this)) }
        .toString()
        .replace("is ", "element has text ")
        .replace("a string containing ", "element contains text ")
}

private object WebDriverAtomScriptsReceiver : WebDriverAtomScriptsProvider() {
    fun Atom<*>.getActionDescription(): String {
        return when (script) {
            GET_VISIBLE_TEXT_ANDROID -> "using web action=\"get visible text\""
            CLEAR_ANDROID -> "using web action=\"clear\""
            CLICK_ANDROID -> "using web action=\"click on element\""
            SCROLL_INTO_VIEW_ANDROID -> "using web action=\"scroll into view\""
            SEND_KEYS_ANDROID -> "using web action=\"end keys\""
            ACTIVE_ELEMENT_ANDROID -> "using web action=\"active element\""
            FRAME_BY_ID_OR_NAME_ANDROID -> "using web action=\"frame by id or name\""
            FRAME_BY_INDEX_ANDROID -> "using web action=\"frame by index android\""
            FIND_ELEMENT_ANDROID -> "using web action=\"find element\""
            FIND_ELEMENTS_ANDROID -> "using web action=\"find elements\""
            else -> ""
        }
    }
}
