[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [webBehaviorInterceptors](./web-behavior-interceptors.md)

# webBehaviorInterceptors

`lateinit var webBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`WebBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`>`

Holds the list of [WebBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [WebBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)s.
These interceptors are called by [com.kaspersky.kaspresso.interceptors.tolibrary.impl.KakaoWebInterceptor](#)
before actual [androidx.test.espresso.web.sugar.Web.WebInteraction.perform](#) and
[androidx.test.espresso.web.sugar.Web.WebInteraction.check](#) calls.
Note that the order of [WebBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)s in this list is significant: the first item wil be
at the lowest level of intercepting chain, and the last item will be at the highest level.
For example: the first item actually wraps the [androidx.test.espresso.web.sugar.Web.WebInteraction.perform](#)
call, the second item wraps the first item, and so on.

