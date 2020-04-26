[kaspresso](../index.md) / [com.kaspersky.kaspresso.logger](./index.md)

## Package com.kaspersky.kaspresso.logger

### Types

| Name | Summary |
|---|---|
| [FormattedLogger](-formatted-logger/index.md) | The interface for formatted logging.`interface FormattedLogger` |
| [Logger](-logger/index.md) | The interface for base logging with 3 levels: info, debug and error.`interface Logger` |
| [UiTestLogger](-ui-test-logger.md) | Base interface for all loggers used in Kaspresso.`interface UiTestLogger : `[`FormattedLogger`](-formatted-logger/index.md)`, `[`Logger`](-logger/index.md) |
| [UiTestLoggerImpl](-ui-test-logger-impl/index.md) | The default implementation of [UiTestLogger](-ui-test-logger.md) using [android.util.Log](https://developer.android.com/reference/android/util/Log.html).`class UiTestLoggerImpl : `[`UiTestLogger`](-ui-test-logger.md) |
