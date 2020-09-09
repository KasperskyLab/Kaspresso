[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](index.md) / [WebBehaviorInterceptor](./-web-behavior-interceptor.md)

# WebBehaviorInterceptor

`interface WebBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<WebInteraction<*>>`

The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [Web.WebInteraction.perform](#) and
[Web.WebInteraction.check](#) behavior.

### Inheritors

| Name | Summary |
|---|---|
| [AutoScrollWebBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll/-auto-scroll-web-behavior-interceptor/index.md) | The implementation of [WebBehaviorInterceptor](./-web-behavior-interceptor.md) and [AutoScrollProvider](../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md) interfaces. Provides autoscroll on failure functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.`class AutoScrollWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](./-web-behavior-interceptor.md)`, `[`AutoScrollProvider`](../com.kaspersky.kaspresso.autoscroll/-auto-scroll-provider/index.md)`<WebInteraction<*>>` |
| [FailureLoggingWebBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.failure/-failure-logging-web-behavior-interceptor/index.md) | The implementation of [WebBehaviorInterceptor](./-web-behavior-interceptor.md) and [FailureLoggingProvider](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) interfaces. Provides failure logging functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.`class FailureLoggingWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](./-web-behavior-interceptor.md)`, `[`FailureLoggingProvider`](../com.kaspersky.kaspresso.failure/-failure-logging-provider/index.md) |
| [FlakySafeWebBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety/-flaky-safe-web-behavior-interceptor/index.md) | The implementation of [WebBehaviorInterceptor](./-web-behavior-interceptor.md) and [FlakySafetyProvider](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.`class FlakySafeWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](./-web-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) |
| [SystemDialogSafetyWebBehaviorInterceptor](../com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety/-system-dialog-safety-web-behavior-interceptor/index.md) | The implementation of [WebBehaviorInterceptor](./-web-behavior-interceptor.md) and [SystemDialogSafetyProvider](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) interfaces. Provides system dialog safety functionality for [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) calls.`class SystemDialogSafetyWebBehaviorInterceptor : `[`WebBehaviorInterceptor`](./-web-behavior-interceptor.md)`, `[`SystemDialogSafetyProvider`](../com.kaspersky.kaspresso.systemsafety/-system-dialog-safety-provider/index.md) |
