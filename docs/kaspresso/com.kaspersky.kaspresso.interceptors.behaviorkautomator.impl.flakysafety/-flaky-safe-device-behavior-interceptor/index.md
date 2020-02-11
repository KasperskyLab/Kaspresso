[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety](../index.md) / [FlakySafeDeviceBehaviorInterceptor](./index.md)

# FlakySafeDeviceBehaviorInterceptor

`class FlakySafeDeviceBehaviorInterceptor : `[`DeviceBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)`, `[`FlakySafetyProvider`](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)

The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces.
Provides system flaky safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FlakySafeDeviceBehaviorInterceptor(params: `[`FlakySafetyParams`](../../com.kaspersky.kaspresso.params/-flaky-safety-params/index.md)`, logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [DeviceBehaviorInterceptor](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md) and [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md) interfaces. Provides system flaky safety functionality for [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) calls. |

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `fun <T> interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)<br>Wraps the given [activity](intercept-check.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor$interceptCheck(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor.interceptCheck.T)))/activity) invocation with the flaky safety. |
| [interceptPerform](intercept-perform.md) | `fun <T> interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)<br>Wraps the given [activity](intercept-perform.md#com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor$interceptPerform(com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction, com.kaspersky.components.kautomator.intercept.operation.UiOperation((androidx.test.uiautomator.UiDevice)), kotlin.Function0((com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor.interceptPerform.T)))/activity) invocation with the flaky safety. |
