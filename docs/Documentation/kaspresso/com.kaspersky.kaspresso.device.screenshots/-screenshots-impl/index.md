//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.screenshots](../index.md)/[ScreenshotsImpl](index.md)



# ScreenshotsImpl  
 [androidJvm] 

The implementation of the [Screenshots](../-screenshots/index.md) interface.

class [ScreenshotsImpl](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **screenshotMaker**: [ScreenshotMaker](../../com.kaspersky.kaspresso.device.screenshots.screenshotmaker/-screenshot-maker/index.md), **screenshotDirectoryProvider**: [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md), **screenshotNameProvider**: [ScreenshotNameProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-name-provider/index.md), **screenshotRootDir**: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)) : [Screenshots](../-screenshots/index.md), [ScreenshotTestStartListener](../-screenshot-test-start-listener/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ScreenshotsImpl](-screenshots-impl.md)|  [androidJvm] fun [ScreenshotsImpl](-screenshots-impl.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), screenshotMaker: [ScreenshotMaker](../../com.kaspersky.kaspresso.device.screenshots.screenshotmaker/-screenshot-maker/index.md), screenshotDirectoryProvider: [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md), screenshotNameProvider: [ScreenshotNameProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-name-provider/index.md), screenshotRootDir: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onTestStarted](on-test-started.md)| [androidJvm]  <br>Content  <br>open override fun [onTestStarted](on-test-started.md)()  <br><br><br>
| [take](take.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Takes a screenshot if it is possible, otherwise logs the error. By default a screenshot name looks like <device storage>/screenshotRootDir/<test run number>/<test class name>/<test method name>/tag.png See ScreenshotFileProvider, [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md) for more details<br><br><br><br>Required Permissions: WRITE_EXTERNAL_STORAGE.<br><br><br><br>  <br>Content  <br>open override fun [take](take.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

