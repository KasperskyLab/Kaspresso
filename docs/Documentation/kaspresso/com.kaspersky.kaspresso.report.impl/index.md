//[kaspresso](../index.md)/[com.kaspersky.kaspresso.report.impl](index.md)



# Package com.kaspersky.kaspresso.report.impl  


## Types  
  
|  Name|  Summary| 
|---|---|
| [AllureReportWriter](-allure-report-writer/index.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>This [com.kaspersky.kaspresso.report.ReportWriter](../com.kaspersky.kaspresso.report/-report-writer/index.md) processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo](../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) for generating LogCat logs with <a href="https://docs.qameta.io/allure/#_steps">Allure's steps</a> info JSON.<br><br><br><br>I/KASPRESSO: --------------------------------------------------------------------------- I/KASPRESSO: TEST PASSED I/KASPRESSO: --------------------------------------------------------------------------- I/KASPRESSO: #AllureStepsInfoJson#: [{"attachments":[],"name":"My step 1","parameters":[],"stage":"finished","start":1568790287246,"status":"passed", "steps":[],"stop":1568790288184}]<br><br><br><br>This logs should be processed by your's tests orchestrator (e.g. <a href="https://github.com/Malinskiy/marathon">Marathon</a>).<br><br><br><br>  <br>Content  <br>class [AllureReportWriter](-allure-report-writer/index.md)(**uiTestLogger**: [UiTestLogger](../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [ReportWriter](../com.kaspersky.kaspresso.report/-report-writer/index.md)  <br><br><br>
| [StepInfoConverter](-step-info-converter/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Converts [com.kaspersky.kaspresso.testcases.models.info.StepInfo](../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md) into io.qameta.allure.model.StepResult JSON model.<br><br>  <br>Content  <br>class [StepInfoConverter](-step-info-converter/index.md)  <br><br><br>

