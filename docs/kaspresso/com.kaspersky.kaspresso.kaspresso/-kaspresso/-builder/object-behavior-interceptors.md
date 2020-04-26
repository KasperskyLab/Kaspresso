[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [objectBehaviorInterceptors](./object-behavior-interceptors.md)

# objectBehaviorInterceptors

`lateinit var objectBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`ObjectBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`>`

Holds the list of [ObjectBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [ObjectBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)s.
These interceptors are called by [KautomatorDeviceInterceptor](#)
before actual [UiDeviceInteraction.check](#) and [UiDeviceInteraction.perform](#) call.
Note that the order of [ObjectBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)s in this list is significant: the first item wil be
at the lowest level of intercepting chain, and the last item will be at the highest level.
For example: the first item actually wraps the [UiDeviceInteraction.check](#) or [UiDeviceInteraction.perform](#)
call, the second item wraps the first item, and so on.

