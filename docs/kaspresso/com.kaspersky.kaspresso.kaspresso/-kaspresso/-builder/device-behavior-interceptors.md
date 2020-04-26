[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [deviceBehaviorInterceptors](./device-behavior-interceptors.md)

# deviceBehaviorInterceptors

`lateinit var deviceBehaviorInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`DeviceBehaviorInterceptor`](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)`>`

Holds the list of [DeviceBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)s.
If it was not specified, Kaspresso will use no [DeviceBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)s.
These interceptors are called by [KautomatorObjectInterceptor](#)
before actual [UiObjectInteraction.check](#) and [UiObjectInteraction.perform](#) call.
Note that the order of [DeviceBehaviorInterceptor](../../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)s in this list is significant: the first item wil be
at the lowest level of intercepting chain, and the last item will be at the highest level.
For example: the first item actually wraps the [UiObjectInteraction.check](#) or [UiObjectInteraction.perform](#)
call, the second item wraps the first item, and so on.

