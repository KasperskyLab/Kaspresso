[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.flakysafety](../index.md) / [FlakySafeExecutingInterceptor](./index.md)

# FlakySafeExecutingInterceptor

`class FlakySafeExecutingInterceptor : `[`ExecutingInterceptor`](../../com.kaspersky.kaspresso.interceptors/-executing-interceptor/index.md)

An implementation of [ExecutingInterceptor](../../com.kaspersky.kaspresso.interceptors/-executing-interceptor/index.md) that performs multiple attempts to execute an action or an assertion to
provide flaky safety.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafeExecutingInterceptor()`<br>An implementation of [ExecutingInterceptor](../../com.kaspersky.kaspresso.interceptors/-executing-interceptor/index.md) that performs multiple attempts to execute an action or an assertion to provide flaky safety. |

### Functions

| Name | Summary |
|---|---|
| [interceptAndExecute](intercept-and-execute.md) | `fun interceptAndExecute(function: () -> ViewInteraction): ViewInteraction`<br>Performs multiple attempts to execute an action or an assertion. |
| [interceptAndExecuteWeb](intercept-and-execute-web.md) | `fun interceptAndExecuteWeb(function: () -> WebInteraction<*>): WebInteraction<*>`<br>Performs multiple attempts to execute web action or web assertion. |
