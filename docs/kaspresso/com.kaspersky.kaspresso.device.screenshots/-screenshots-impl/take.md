//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.screenshots](../index.md)/[ScreenshotsImpl](index.md)/[take](take.md)



# take  
[androidJvm]  
Brief description  




Takes a screenshot if it is possible, otherwise logs the error. By default a screenshot name looks like <device storage>/screenshotRootDir/<test run number>/<test class name>/<test method name>/tag.png See ScreenshotFileProvider, [ScreenshotDirectoryProvider](../../com.kaspersky.kaspresso.device.screenshots.screenshotfiles/-screenshot-directory-provider/index.md) for more details



Required Permissions: WRITE_EXTERNAL_STORAGE.





## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| tag| <br><br>a unique tag to further identify the screenshot. Must match a-zA-Z0-9_-+.<br><br>
  
  
Content  
open override fun [take](take.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  



