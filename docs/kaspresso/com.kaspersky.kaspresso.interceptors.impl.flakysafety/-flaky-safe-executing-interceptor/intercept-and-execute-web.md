[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.flakysafety](../index.md) / [FlakySafeExecutingInterceptor](index.md) / [interceptAndExecuteWeb](./intercept-and-execute-web.md)

# interceptAndExecuteWeb

`fun interceptAndExecuteWeb(function: () -> WebInteraction<*>): WebInteraction<*>`

Overrides [ExecutingInterceptor.interceptAndExecuteWeb](../../com.kaspersky.kaspresso.interceptors/-executing-interceptor/intercept-and-execute-web.md)

Performs multiple attempts to execute web action or web assertion.

### Parameters

`function` - a function-wrapper of web action or web assertion to be invoked.

**Return**
[Web.WebInteraction](#) as it is a result of [function](intercept-and-execute-web.md#com.kaspersky.kaspresso.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor$interceptAndExecuteWeb(kotlin.Function0((android.support.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)))))/function) invocation.

