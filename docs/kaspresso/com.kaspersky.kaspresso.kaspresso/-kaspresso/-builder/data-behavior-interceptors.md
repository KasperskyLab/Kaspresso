[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [dataBehaviorInterceptors](./data-behavior-interceptors.md)

# dataBehaviorInterceptors

`var dataBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`DataBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`>`

Holds the list of [DataBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [DataBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)s.
These interceptors are called by [com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoDataInterceptor](#)
before actual [androidx.test.espresso.DataInteraction.check](#) call.

