[kaspresso](../../index.md) / [com.kaspersky.kaspresso.report.impl](../index.md) / [AllureReportWriter](./index.md)

# AllureReportWriter

`class AllureReportWriter : `[`ReportWriter`](../../com.kaspersky.kaspresso.report/-report-writer/index.md)

This [com.kaspersky.kaspresso.report.ReportWriter](../../com.kaspersky.kaspresso.report/-report-writer/index.md) processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)
for generating LogCat logs with Allure's steps info JSON.

I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: TEST PASSED
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: #AllureStepsInfoJson#: [{"attachments":[],"name":"My step 1","parameters":[],"stage":"finished","start":1568790287246,"status":"passed", "steps":[],"stop":1568790288184}]

This logs should be processed by your's tests orchestrator (e.g. Marathon).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AllureReportWriter(uiTestLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>This [com.kaspersky.kaspresso.report.ReportWriter](../../com.kaspersky.kaspresso.report/-report-writer/index.md) processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) for generating LogCat logs with Allure's steps info JSON. |

### Functions

| Name | Summary |
|---|---|
| [processTestResults](process-test-results.md) | `fun processTestResults(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
