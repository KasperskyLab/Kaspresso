[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety](../index.md) / [FlakySafeViewBehaviorInterceptor](./index.md)

# FlakySafeViewBehaviorInterceptor

`class FlakySafeViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)

The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces.
Provides system flaky safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [ViewBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.`FlakySafeViewBehaviorInterceptor(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Wraps the given [action](intercept.md#com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor$intercept(androidx.test.espresso.ViewInteraction, kotlin.Function0((com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor.intercept.T)))/action) invocation with the flaky safety.`fun <T> intercept(interaction: ViewInteraction, action: () -> T): T` |
