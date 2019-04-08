[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.configurator](../../index.md) / [Configurator](../index.md) / [Builder](./index.md)

# Builder

`class Builder`

A class for [Configurator](../index.md) initialization. The right way to change [Configurator](../index.md) settings is to use [Builder](./index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Builder()`<br>A class for [Configurator](../index.md) initialization. The right way to change [Configurator](../index.md) settings is to use [Builder](./index.md). |

### Properties

| Name | Summary |
|---|---|
| [accessibility](accessibility.md) | `var accessibility: `[`Accessibility`](../../../com.kaspersky.kaspresso.device.accessibility/-accessibility/index.md) |
| [activities](activities.md) | `var activities: `[`Activities`](../../../com.kaspersky.kaspresso.device.activities/-activities/index.md) |
| [allowedExceptionsForAttempt](allowed-exceptions-for-attempt.md) | `var allowedExceptionsForAttempt: `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>>` |
| [apps](apps.md) | `var apps: `[`Apps`](../../../com.kaspersky.kaspresso.device.apps/-apps/index.md) |
| [atomInterceptors](atom-interceptors.md) | `var atomInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AtomInterceptor`](../../../com.kaspersky.kaspresso.interceptors/-atom-interceptor/index.md)`>` |
| [attemptsIntervalMs](attempts-interval-ms.md) | `var attemptsIntervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [attemptsTimeoutMs](attempts-timeout-ms.md) | `var attemptsTimeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [executingInterceptor](executing-interceptor.md) | `var executingInterceptor: `[`ExecutingInterceptor`](../../../com.kaspersky.kaspresso.interceptors/-executing-interceptor/index.md)`?` |
| [exploit](exploit.md) | `var exploit: `[`Exploit`](../../../com.kaspersky.kaspresso.device.exploit/-exploit/index.md) |
| [externalLogger](external-logger.md) | `var externalLogger: `[`UiTestLogger`](../../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md) |
| [failureInterceptor](failure-interceptor.md) | `var failureInterceptor: `[`FailureInterceptor`](../../../com.kaspersky.kaspresso.interceptors/-failure-interceptor/index.md)`?`<br>An interceptor that is called on failures. It's [FailureInterceptor.interceptAndThrow](../../../com.kaspersky.kaspresso.interceptors/-failure-interceptor/intercept-and-throw.md) method is being provide as the default [android.support.test.espresso.FailureHandler](#). |
| [files](files.md) | `var files: `[`Files`](../../../com.kaspersky.kaspresso.device.files/-files/index.md) |
| [internet](internet.md) | `var internet: `[`Internet`](../../../com.kaspersky.kaspresso.device.internet/-internet/index.md) |
| [logger](logger.md) | `var logger: `[`UiTestLogger`](../../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md) |
| [permissions](permissions.md) | `var permissions: `[`Permissions`](../../../com.kaspersky.kaspresso.device.permissions/-permissions/index.md) |
| [screenshots](screenshots.md) | `var screenshots: `[`Screenshots`](../../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md) |
| [viewActionInterceptors](view-action-interceptors.md) | `var viewActionInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewActionInterceptor`](../../../com.kaspersky.kaspresso.interceptors/-view-action-interceptor/index.md)`>` |
| [viewAssertionInterceptors](view-assertion-interceptors.md) | `var viewAssertionInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewAssertionInterceptor`](../../../com.kaspersky.kaspresso.interceptors/-view-assertion-interceptor/index.md)`>` |
| [webAssertionInterceptors](web-assertion-interceptors.md) | `var webAssertionInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WebAssertionInterceptor`](../../../com.kaspersky.kaspresso.interceptors/-web-assertion-interceptor/index.md)`>` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [default](default.md) | `fun default(): `[`Configurator.Builder`](./index.md)<br>Puts the default settings pack to [Builder](./index.md). |
