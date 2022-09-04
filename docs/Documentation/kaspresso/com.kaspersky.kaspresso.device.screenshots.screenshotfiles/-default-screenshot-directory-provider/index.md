//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md)/[DefaultScreenshotDirectoryProvider](index.md)



# DefaultScreenshotDirectoryProvider  
 [androidJvm] 

Default implementation of [ScreenshotDirectoryProvider](../-screenshot-directory-provider/index.md) If groupByRunNumbers is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test running several times per the same suite.

class [DefaultScreenshotDirectoryProvider](index.md)(**groupByRunNumbers**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ScreenshotDirectoryProvider](../-screenshot-directory-provider/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [DefaultScreenshotDirectoryProvider](-default-screenshot-directory-provider.md)|  [androidJvm] fun [DefaultScreenshotDirectoryProvider](-default-screenshot-directory-provider.md)(groupByRunNumbers: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getDirectoryForTest](get-directory-for-test.md)| [androidJvm]  <br>Content  <br>open override fun [getDirectoryForTest](get-directory-for-test.md)(testMethod: [TestMethod](../-test-method/index.md), runNumber: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

