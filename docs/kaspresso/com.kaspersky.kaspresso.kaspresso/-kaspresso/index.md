[kaspresso](../../index.md) / [com.kaspersky.kaspresso.kaspresso](../index.md) / [Kaspresso](./index.md)

# Kaspresso

`data class Kaspresso`

The storage of all Kaspresso preferences and entities, such as [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), [Device](../../com.kaspersky.kaspresso.device/-device/index.md) and different interceptors.

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | The class for [Kaspresso](./index.md) configuration and initialization. This is the way to set Kaspresso preferences.`class Builder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The storage of all Kaspresso preferences and entities, such as [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), [Device](../../com.kaspersky.kaspresso.device/-device/index.md) and different interceptors.`Kaspresso(libLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, testLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`, device: `[`Device`](../../com.kaspersky.kaspresso.device/-device/index.md)`, params: `[`Params`](../../com.kaspersky.kaspresso.params/-params/index.md)`, viewActionWatcherInterceptors: List<`[`ViewActionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)`>, viewAssertionWatcherInterceptors: List<`[`ViewAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)`>, atomWatcherInterceptors: List<`[`AtomWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)`>, webAssertionWatcherInterceptors: List<`[`WebAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)`>, objectWatcherInterceptors: List<`[`ObjectWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md)`>, deviceWatcherInterceptors: List<`[`DeviceWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md)`>, viewBehaviorInterceptors: List<`[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`>, dataBehaviorInterceptors: List<`[`DataBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`>, webBehaviorInterceptors: List<`[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`>, objectBehaviorInterceptors: List<`[`ObjectBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-object-behavior-interceptor.md)`>, deviceBehaviorInterceptors: List<`[`DeviceBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behaviorkautomator/-device-behavior-interceptor.md)`>, stepWatcherInterceptors: List<`[`StepWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)`>, testRunWatcherInterceptors: List<`[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)`>)` |
