//[kaspresso](../index.md)/[com.kaspersky.kaspresso.logger](index.md)



# Package com.kaspersky.kaspresso.logger  


## Types  
  
|  Name|  Summary| 
|---|---|
| [FormattedLogger](-formatted-logger/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The interface for formatted logging.<br><br>  <br>Content  <br>interface [FormattedLogger](-formatted-logger/index.md)  <br><br><br>
| [Logger](-logger/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The interface for base logging with 3 levels: info, debug and error.<br><br>  <br>Content  <br>interface [Logger](-logger/index.md)  <br><br><br>
| [UiTestLogger](-ui-test-logger/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Base interface for all loggers used in Kaspresso.<br><br>  <br>Content  <br>interface [UiTestLogger](-ui-test-logger/index.md) : [FormattedLogger](-formatted-logger/index.md), [Logger](-logger/index.md)  <br><br><br>
| [UiTestLoggerImpl](-ui-test-logger-impl/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The default implementation of [UiTestLogger](-ui-test-logger/index.md) using [android.util.Log](https://developer.android.com/reference/kotlin/android/util/Log.html).<br><br>  <br>Content  <br>class [UiTestLoggerImpl](-ui-test-logger-impl/index.md)(**tag**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [UiTestLogger](-ui-test-logger/index.md)  <br><br><br>

