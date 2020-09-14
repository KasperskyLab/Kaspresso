//[kaspresso](../index.md)/[com.kaspersky.kaspresso.device.screenshots.screenshotmaker](index.md)



# Package com.kaspersky.kaspresso.device.screenshots.screenshotmaker  


## Types  
  
|  Name|  Summary| 
|---|---|
| [CombinedScreenshotMaker](-combined-screenshot-maker/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Calls preferredScreenshotMaker and fallbacks to fallbackScreenshotMaker on fail<br><br>  <br>Content  <br>class [CombinedScreenshotMaker](-combined-screenshot-maker/index.md)(**preferredScreenshotMaker**: [ScreenshotMaker](-screenshot-maker/index.md), **fallbackScreenshotMaker**: [ScreenshotMaker](-screenshot-maker/index.md)) : [ScreenshotMaker](-screenshot-maker/index.md)  <br><br><br>
| [ExternalScreenshotMaker](-external-screenshot-maker/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Captures spoon-compatible screenshots by uiautomator.<br><br>  <br>Content  <br>class [ExternalScreenshotMaker](-external-screenshot-maker/index.md) : [ScreenshotMaker](-screenshot-maker/index.md)  <br><br><br>
| [InternalScreenshotMaker](-internal-screenshot-maker/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Captures the view of a current activity<br><br>  <br>Content  <br>class [InternalScreenshotMaker](-internal-screenshot-maker/index.md)(**activities**: [Activities](../com.kaspersky.kaspresso.device.activities/-activities/index.md)) : [ScreenshotMaker](-screenshot-maker/index.md)  <br><br><br>
| [ScreenshotMaker](-screenshot-maker/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Creates and saves a screenshot<br><br>  <br>Content  <br>interface [ScreenshotMaker](-screenshot-maker/index.md)  <br><br><br>

