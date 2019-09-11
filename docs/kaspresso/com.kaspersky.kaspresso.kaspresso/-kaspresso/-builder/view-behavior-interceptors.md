[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [viewBehaviorInterceptors](./view-behavior-interceptors.md)

# viewBehaviorInterceptors

`var viewBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`ViewBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`>`

Holds the list of [ViewBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [ViewBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)s.
These interceptors are called by [com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoViewInterceptor](#)
before actual [androidx.test.espresso.ViewInteraction.perform](#) and
[androidx.test.espresso.ViewInteraction.check](#) calls.

