[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](../index.md) / [BehaviorInterceptor](./index.md)

# BehaviorInterceptor

`interface BehaviorInterceptor<Interaction>`

The interface for all interceptors that change the default interaction in Kakao=&gt;Espresso. Often it wraps the interaction calls.

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Called to do some stuff and actually perform an interaction with element.`abstract fun <T> intercept(interaction: Interaction, action: () -> T): T` |

### Inheritors

| Name | Summary |
|---|---|
| [DataBehaviorInterceptor](../-data-behavior-interceptor.md) | The derived from [BehaviorInterceptor](./index.md) interface for intercepting [DataInteraction.check](#) behavior.`interface DataBehaviorInterceptor : `[`BehaviorInterceptor`](./index.md)`<DataInteraction>` |
| [ViewBehaviorInterceptor](../-view-behavior-interceptor.md) | The derived from [BehaviorInterceptor](./index.md) interface for intercepting [ViewInteraction.perform](#) and [ViewInteraction.check](#) behavior.`interface ViewBehaviorInterceptor : `[`BehaviorInterceptor`](./index.md)`<ViewInteraction>` |
| [WebBehaviorInterceptor](../-web-behavior-interceptor.md) | The derived from [BehaviorInterceptor](./index.md) interface for intercepting [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) behavior.`interface WebBehaviorInterceptor : `[`BehaviorInterceptor`](./index.md)`<WebInteraction<*>>` |
