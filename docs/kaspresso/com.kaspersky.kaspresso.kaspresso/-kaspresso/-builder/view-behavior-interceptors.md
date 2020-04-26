[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [viewBehaviorInterceptors](./view-behavior-interceptors.md)

# viewBehaviorInterceptors

`lateinit var viewBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`ViewBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`>`

Holds the list of [ViewBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [ViewBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)s.
These interceptors are called by [com.kaspersky.kaspresso.interceptors.tolibrary.impl.KakaoViewInterceptor](#)
before actual [androidx.test.espresso.ViewInteraction.perform](#) and
[androidx.test.espresso.ViewInteraction.check](#) calls.
Note that the order of [ViewBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)s in this list is significant: the first item wil be
at the lowest level of intercepting chain, and the last item will be at the highest level.
For example: the first item actually wraps the [androidx.test.espresso.ViewInteraction.perform](#) call,
the second item wraps the first item, and so on.

