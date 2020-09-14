//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcase](../index.md)/[DocLocScreenshotTestCase](index.md)/[DocLocScreenshotTestCase](-doc-loc-screenshot-test-case.md)



# DocLocScreenshotTestCase  
[androidJvm]  
Brief description  


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| changeSystemLocale| <br><br>change the system language, i.e. system dialogs (e.g. runtime permissions) will also be localized.     Need permission in manifest file for a target app android.permission.CHANGE_CONFIGURATION<br><br>
| locales| <br><br>comma-separated string with locales to run test with.<br><br>
| screenshotDirectoryProvider| <br><br>screenshot directory provider inside the root directory<br><br>
| screenshotNameProvider| <br><br>screenshot file name provider<br><br>
| screenshotsDirectory| <br><br>root directory to save screenshot.<br><br>
  
  
Content  
fun [DocLocScreenshotTestCase](-doc-loc-screenshot-test-case.md)(screenshotsDirectory: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), screenshotDirectoryProvider: [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md), screenshotNameProvider: [ScreenshotNameProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-name-provider/index.md), changeSystemLocale: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), locales: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md))  



