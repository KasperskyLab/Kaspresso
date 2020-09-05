//[kaspresso-framework](../../index.md)/[com.kaspersky.kaspresso.testcases.api.testcase](../index.md)/[DocLocScreenshotTestCase](index.md)/[DocLocScreenshotTestCase](-doc-loc-screenshot-test-case.md)



# DocLocScreenshotTestCase  
[androidJvm]  
Brief description  


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| changeSystemLocale| <br><br>change the system language, i.e. system dialogs (e.g. runtime permissions) will also be localized.     Need permission in manifest file for a target app android.permission.CHANGE_CONFIGURATION<br><br>
| locales| <br><br>comma-separated string with locales to run test with.<br><br>
| screenshotsDirectory| <br><br>directory to save screenshot.<br><br>
  
  
Content  
fun [DocLocScreenshotTestCase](-doc-loc-screenshot-test-case.md)(screenshotsDirectory: [File](https://developer.android.com/reference/kotlin/java/io/File.html), changeSystemLocale: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), locales: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, kaspressoBuilder: [Kaspresso.Builder](../../com.kaspersky.kaspresso.kaspresso/-kaspresso/-builder/index.md))  



