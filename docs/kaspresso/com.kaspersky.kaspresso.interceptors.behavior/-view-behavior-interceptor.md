[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](index.md) / [ViewBehaviorInterceptor](./-view-behavior-interceptor.md)

# ViewBehaviorInterceptor

`interface ViewBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<ViewInteraction>`

The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [ViewInteraction.perform](#) and
[ViewInteraction.check](#) behavior.

### Inheritors

| Name | Summary |
|---|---|
| [AutoScrollViewBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll/-auto-scroll-view-behavior-interceptor/index.md) | The implementation of [ViewBehaviorInterceptor](./-view-behavior-interceptor.md) and [AutoScrollProvider](../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.`class AutoScrollViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](./-view-behavior-interceptor.md)`, `[`AutoScrollProvider`](../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)`<ViewInteraction>` |
| [FailureLoggingViewBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-view-behavior-interceptor/index.md) | The implementation of [ViewBehaviorInterceptor](./-view-behavior-interceptor.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.`class FailureLoggingViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](./-view-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) |
| [FlakySafeViewBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-view-behavior-interceptor/index.md) | The implementation of [ViewBehaviorInterceptor](./-view-behavior-interceptor.md) and [FlakySafetyProvider](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.`class FlakySafeViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](./-view-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) |
| [SystemDialogSafetyViewBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-view-behavior-interceptor/index.md) | The implementation of [ViewBehaviorInterceptor](./-view-behavior-interceptor.md) and [SystemDialogSafetyProvider](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [ViewInteraction.perform](#) and [ViewInteraction.check](#) calls.`class SystemDialogSafetyViewBehaviorInterceptor : `[`ViewBehaviorInterceptor`](./-view-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) |
