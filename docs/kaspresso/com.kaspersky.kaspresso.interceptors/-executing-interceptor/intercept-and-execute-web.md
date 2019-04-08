[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [ExecutingInterceptor](index.md) / [interceptAndExecuteWeb](./intercept-and-execute-web.md)

# interceptAndExecuteWeb

`abstract fun interceptAndExecuteWeb(function: () -> WebInteraction<*>): WebInteraction<*>`

Called to do some stuff and actually execute web action or web assertion.

### Parameters

`function` - a function-wrapper of web action or web assertion to be invoked.

**Return**
[Web.WebInteraction](#) as it is a result of [function](intercept-and-execute-web.md#com.kaspersky.kaspresso.interceptors.ExecutingInterceptor$interceptAndExecuteWeb(kotlin.Function0((android.support.test.espresso.web.sugar.Web.WebInteraction((kotlin.Any)))))/function) invocation.

