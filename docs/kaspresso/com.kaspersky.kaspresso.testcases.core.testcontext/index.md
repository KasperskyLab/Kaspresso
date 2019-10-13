[kaspresso](../index.md) / [com.kaspersky.kaspresso.testcases.core.testcontext](./index.md)

## Package com.kaspersky.kaspresso.testcases.core.testcontext

### Types

| Name | Summary |
|---|---|
| [BaseTestContext](-base-test-context/index.md) | `open class BaseTestContext : `[`FlakySafetyProvider`](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)`, `[`CheckDuringProvider`](../com.kaspersky.kaspresso.flakysafety/-check-during-provider/index.md)`, `[`ComposeProvider`](../com.kaspersky.kaspresso.compose/-compose-provider/index.md)`, `[`WebComposeProvider`](../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md)<br>Provides the Kaspresso functionality for "run" section: [Device](../com.kaspersky.kaspresso.device/-device/index.md), [AdbServer](../com.kaspersky.kaspresso.device.server/-adb-server/index.md), the [UiTestLogger](../com.kaspersky.kaspresso.logger/-ui-test-logger.md) implementation for external developers. Also provides flaky safety, composing and web composing functionalities via implementing [FlakySafetyProvider](../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md), [ComposeProvider](../com.kaspersky.kaspresso.compose/-compose-provider/index.md) and [WebComposeProvider](../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md) interfaces. |
| [TestContext](-test-context/index.md) | `class TestContext<Data> : `[`BaseTestContext`](-base-test-context/index.md)<br>The special class to operate with in user scenario. Provides [step](-test-context/step.md) and [scenario](-test-context/scenario.md) methods in "run" section to build a test. |
