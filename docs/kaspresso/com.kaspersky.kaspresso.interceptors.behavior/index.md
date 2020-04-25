[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behavior](./index.md)

## Package com.kaspersky.kaspresso.interceptors.behavior

### Types

| Name | Summary |
|---|---|
| [BehaviorInterceptor](-behavior-interceptor/index.md) | The interface for all interceptors that change the default interaction in Kakao=&gt;Espresso. Often it wraps the interaction calls.`interface BehaviorInterceptor<Interaction>` |
| [DataBehaviorInterceptor](-data-behavior-interceptor.md) | The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [DataInteraction.check](#) behavior.`interface DataBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<DataInteraction>` |
| [ViewBehaviorInterceptor](-view-behavior-interceptor.md) | The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [ViewInteraction.perform](#) and [ViewInteraction.check](#) behavior.`interface ViewBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<ViewInteraction>` |
| [WebBehaviorInterceptor](-web-behavior-interceptor.md) | The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting [Web.WebInteraction.perform](#) and [Web.WebInteraction.check](#) behavior.`interface WebBehaviorInterceptor : `[`BehaviorInterceptor`](-behavior-interceptor/index.md)`<WebInteraction<*>>` |
