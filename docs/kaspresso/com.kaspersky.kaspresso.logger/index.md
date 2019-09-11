[kaspresso](../index.md) / [com.kaspersky.kaspresso.logger](./index.md)

## Package com.kaspersky.kaspresso.logger

### Types

| Name | Summary |
|---|---|
| [FormattedLogger](-formatted-logger/index.md) | `interface FormattedLogger`<br>The interface for formatted logging. |
| [Logger](-logger/index.md) | `interface Logger`<br>The interface for base logging with 3 levels: info, debug and error. |
| [UiTestLogger](-ui-test-logger.md) | `interface UiTestLogger : `[`FormattedLogger`](-formatted-logger/index.md)`, `[`Logger`](-logger/index.md)<br>Base interface for all loggers used in Kaspresso. |
| [UiTestLoggerImpl](-ui-test-logger-impl/index.md) | `class UiTestLoggerImpl : `[`UiTestLogger`](-ui-test-logger.md)<br>The default implementation of [UiTestLogger](-ui-test-logger.md) using [android.util.Log](https://developer.android.com/reference/android/util/Log.html). |
