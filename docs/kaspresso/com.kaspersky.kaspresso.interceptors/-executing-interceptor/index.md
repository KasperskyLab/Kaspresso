[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [ExecutingInterceptor](./index.md)

# ExecutingInterceptor

`interface ExecutingInterceptor`

An interface for all executing interceptors, actually manages the execution of actions or assertions.

### Functions

| Name | Summary |
|---|---|
| [interceptAndExecute](intercept-and-execute.md) | `abstract fun interceptAndExecute(function: () -> ViewInteraction): ViewInteraction`<br>Called to do some stuff and actually execute an action or an assertion. |
| [interceptAndExecuteWeb](intercept-and-execute-web.md) | `abstract fun interceptAndExecuteWeb(function: () -> WebInteraction<*>): WebInteraction<*>`<br>Called to do some stuff and actually execute web action or web assertion. |

### Inheritors

| Name | Summary |
|---|---|
| [FlakySafeExecutingInterceptor](../../com.kaspersky.kaspresso.interceptors.impl.flakysafety/-flaky-safe-executing-interceptor/index.md) | `class FlakySafeExecutingInterceptor : `[`ExecutingInterceptor`](./index.md)<br>An implementation of [ExecutingInterceptor](./index.md) that performs multiple attempts to execute an action or an assertion to provide flaky safety. |
