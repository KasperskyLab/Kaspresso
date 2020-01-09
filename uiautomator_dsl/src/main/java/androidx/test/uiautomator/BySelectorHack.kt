package androidx.test.uiautomator

/**
 * Workaround for creating [BySelector] instances with package-private constructor.
 * Replaces default [By] factory and completely safe to use due to member function invocation after
 * instance creation.
 */
object BySelectorHack {
    fun newInstance(function: BySelector.() -> BySelector): BySelector {
        val selector = BySelector()
        return function(selector)
    }
}