//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.screenshots.screenshotfiles](index.md)



# Package com.kaspersky.kaspresso.device.screenshots.screenshotfiles  


## Types  
  
|  Name|  Summary| 
|---|---|
| [DefaultScreenshotDirectoryProvider](-default-screenshot-directory-provider/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Default implementation of [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md) If groupByRunNumbers is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test running several times per the same suite.<br><br>  <br>Content  <br>class [DefaultScreenshotDirectoryProvider](-default-screenshot-directory-provider/index.md)(**groupByRunNumbers**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md)  <br><br><br>
| [DefaultScreenshotNameProvider](-default-screenshot-name-provider/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Default implementation of ScreenshotFileProvider If addTimestamps is true it adds timestamps to names like that "1570158949869_ScreenshotSampleTest_step_1.png"<br><br>  <br>Content  <br>class [DefaultScreenshotNameProvider](-default-screenshot-name-provider/index.md)(**addTimestamps**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ScreenshotNameProvider](-screenshot-name-provider/index.md)  <br><br><br>
| [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Provides a directory for screenshots of a separate test<br><br>  <br>Content  <br>interface [ScreenshotDirectoryProvider](-screenshot-directory-provider/index.md)  <br><br><br>
| [ScreenshotNameProvider](-screenshot-name-provider/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Provides names for screenshots<br><br>  <br>Content  <br>interface [ScreenshotNameProvider](-screenshot-name-provider/index.md)  <br><br><br>
| [ScreenshotsDirectoryStorage](-screenshots-directory-storage/index.md)| [androidJvm]  <br>Content  <br>class [ScreenshotsDirectoryStorage](-screenshots-directory-storage/index.md)  <br><br><br>
| [TestMethod](-test-method/index.md)| [androidJvm]  <br>Content  <br>data class [TestMethod](-test-method/index.md)(**className**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **methodName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>

