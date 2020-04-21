[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](./index.md)

## Package com.kaspersky.kaspresso.interceptors.behavior

### Types

| Name | Summary |
|---|---|
| [BehaviorInterceptor](-behavior-interceptor/index.md) | `interface BehaviorInterceptor<Interaction>`<br>The interface for all interceptors that change the default interaction in Kakao=&gt;Espresso. Often it wraps the interaction calls. |
| [DataBehaviorInterceptor](-data-behavior-interceptor.md) | `interface DataBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<DataInteraction>`<br>The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [DataInteraction.check](#) behavior. |
| [ViewBehaviorInterceptor](-view-behavior-interceptor.md) | `interface ViewBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<ViewInteraction>`<br>The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [ViewInteraction.perform](#) and [ViewInteraction.check](#) behavior. |
| [WebBehaviorInterceptor](-web-behavior-interceptor.md) | `interface WebBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<WebInteraction<*>>`<br>The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) behavior. |
