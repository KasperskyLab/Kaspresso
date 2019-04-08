[kaspresso](../index.md) / [com.kaspersky.kaspresso.logger](./index.md)

## Package com.kaspersky.kaspresso.logger

### Types

| Name | Summary |
|---|---|
| [KLogger](-k-logger/index.md) | `object KLogger : `[`UiTestLogger`](-ui-test-logger/index.md)<br>A singletone to provide access to external logger outside. Implements [UiTestLogger](-ui-test-logger/index.md) interface and delegates all calls to wrapped implementation of [UiTestLogger](-ui-test-logger/index.md). |
| [UiTestLogger](-ui-test-logger/index.md) | `interface UiTestLogger`<br>Base interface for all loggers used in Kaspresso. |
| [UiTestLoggerImpl](-ui-test-logger-impl/index.md) | `class UiTestLoggerImpl : `[`UiTestLogger`](-ui-test-logger/index.md)<br>Default implementation of [UiTestLogger](-ui-test-logger/index.md) using [android.util.Log](https://developer.android.com/reference/android/util/Log.html). |
