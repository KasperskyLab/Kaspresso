[kaspresso](../../index.md) / [com.kaspersky.kaspresso.report.impl](../index.md) / [AllureReportWriter](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`AllureReportWriter(uiTestLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`

This [com.kaspersky.kaspresso.report.ReportWriter](../../com.kaspersky.kaspresso.report/-report-writer/index.md) processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)
for generating LogCat logs with Allure's steps info JSON.

I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: TEST PASSED
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: #AllureStepsInfoJson#: [{"attachments":[],"name":"My step 1","parameters":[],"stage":"finished","start":1568790287246,"status":"passed", "steps":[],"stop":1568790288184}]

This logs should be processed by your's tests orchestrator (e.g. Marathon).

