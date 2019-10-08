[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll](../index.md) / [AutoScrollViewBehaviorInterceptor](./index.md)

# AutoScrollViewBehaviorInterceptor

`class AutoScrollViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`, `[`AutoScrollProvider`](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)`<ViewInteraction>`

The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces.
Provides autoscroll on failure functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AutoScrollViewBehaviorInterceptor(params: `[`AutoScrollParams`](../../com.kaspersky.kaspresso.params/-auto-scroll-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [AutoScrollProvider](../../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun <T> intercept(interaction: ViewInteraction, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)<br>Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor$intercept(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor.intercept.T)))/action) invocation with the autoscrolling on failure. |
