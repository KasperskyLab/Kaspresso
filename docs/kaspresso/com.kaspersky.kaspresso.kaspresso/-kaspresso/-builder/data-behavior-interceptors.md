[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [dataBehaviorInterceptors](./data-behavior-interceptors.md)

# dataBehaviorInterceptors

`lateinit var dataBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`DataBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`>`

Holds the list of [DataBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [DataBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)s.
These interceptors are called by [com.kaspersky.kaspresso.interceptors.tolibrary.impl.KakaoDataInterceptor](#)
before actual [androidx.test.espresso.DataInteraction.check](#) call.
Note that the order of [DataBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)s in this list is significant: the first item wil be
at the lowest level of intercepting chain, and the last item will be at the highest level.
For example: the first item actually wraps the [androidx.test.espresso.DataInteraction.check](#) call,
the second item wraps the first item, and so on.

