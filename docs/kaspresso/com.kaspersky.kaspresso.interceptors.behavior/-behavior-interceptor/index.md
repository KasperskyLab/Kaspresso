[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](../index.md) / [BehaviorInterceptor](./index.md)

# BehaviorInterceptor

`interface BehaviorInterceptor<Interaction>`

The interface for all interceptors that change the default interaction in Kakao=&gt;Espresso. Often it wraps the interaction calls.

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun <T> intercept(interaction: `[`Interaction`](index.md#Interaction)`, action: () -> `[`T`](intercept.md#T)`): `[`T`](intercept.md#T)<br>Called to do some stuff and actually perform an interaction with element. |

### Inheritors

| Name | Summary |
|---|---|
| [DataBehaviorInterceptor](../-data-behavior-interceptor.md) | `interface DataBehaviorInterceptor : `[`BehaviorInterceptor`](./index.md)`<DataInteraction>`<br>The derived from [BehaviorInterceptor](./index.md) interface for intercepting [DataInteraction.check](#) behavior. |
| [ViewBehaviorInterceptor](../-view-behavior-interceptor.md) | `interface ViewBehaviorInterceptor : `[`BehaviorInterceptor`](./index.md)`<ViewInteraction>`<br>The derived from [BehaviorInterceptor](./index.md) interface for intercepting [ViewInteraction.perform](#) and [ViewInteraction.check](#) behavior. |
| [WebBehaviorInterceptor](../-web-behavior-interceptor.md) | `interface WebBehaviorInterceptor : `[`BehaviorInterceptor`](./index.md)`<WebInteraction<*>>`<br>The derived from [BehaviorInterceptor](./index.md) interface for intercepting [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) behavior. |
