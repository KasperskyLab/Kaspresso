[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.testassistants](../index.md) / [TestAssistantsProvider](./index.md)

# TestAssistantsProvider

`interface TestAssistantsProvider`

Provider of test assistants allowed in [com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md),
[com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md) and their inheritors

### Properties

| Name | Summary |
|---|---|
| [adbServer](adb-server.md) | `abstract val adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md) |
| [device](device.md) | `abstract val device: `[`Device`](../../com.kaspersky.kaspresso.device/-device/index.md) |
| [params](params.md) | `abstract val params: `[`Params`](../../com.kaspersky.kaspresso.params/-params/index.md) |
| [testLogger](test-logger.md) | `abstract val testLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) |

### Inheritors

| Name | Summary |
|---|---|
| [BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md) | `abstract class BaseTestCase<InitData, Data> : `[`TestAssistantsProvider`](./index.md)<br>The base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of [TestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-test-case/index.md) as a parent for all actual project-wide test cases. Nesting test cases are not recommended, use [com.kaspersky.kaspresso.testcases.api.scenario.Scenario](../../com.kaspersky.kaspresso.testcases.api.scenario/-scenario/index.md) instead. |
| [BaseTestContext](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md) | `open class BaseTestContext : `[`FlakySafetyProvider`](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md)`, `[`ContinuouslyProvider`](../../com.kaspersky.kaspresso.flakysafety/-continuously-provider/index.md)`, `[`ComposeProvider`](../../com.kaspersky.kaspresso.compose/-compose-provider/index.md)`, `[`WebComposeProvider`](../../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md)`, `[`TestAssistantsProvider`](./index.md)<br>Provides the Kaspresso functionality for "run" section: [Device](../../com.kaspersky.kaspresso.device/-device/index.md), [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), the [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md) implementation for external developers. Also provides flaky safety, composing and web composing functionalities via implementing [FlakySafetyProvider](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider/index.md), [ComposeProvider](../../com.kaspersky.kaspresso.compose/-compose-provider/index.md) and [WebComposeProvider](../../com.kaspersky.kaspresso.compose/-web-compose-provider/index.md) interfaces. |
