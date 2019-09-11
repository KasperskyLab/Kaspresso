[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [webBehaviorInterceptors](./web-behavior-interceptors.md)

# webBehaviorInterceptors

`var webBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`WebBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`>`

Holds the list of [WebBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [WebBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)s.
These interceptors are called by [com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoWebInterceptor](#)
before actual [androidx.test.espresso.web.sugar.Web.WebInteraction.perform](#) and
[androidx.test.espresso.web.sugar.Web.WebInteraction.check](#) calls.

