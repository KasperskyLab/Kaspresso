//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.logger](../index.md)/[UiTestLogger](index.md)



# UiTestLogger  
 [androidJvm] 

Base interface for all loggers used in Kaspresso.

interface [UiTestLogger](index.md) : [FormattedLogger](../-formatted-logger/index.md), [Logger](../-logger/index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [d](../-logger/d.md)| [androidJvm]  <br>Brief description  <br><br><br>Debug level of logging.<br><br>  <br>Content  <br>abstract override fun [d](../-logger/d.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Debug level of logging with tag.<br><br>  <br>Content  <br>abstract override fun [d](../-logger/d.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [e](../-logger/e.md)| [androidJvm]  <br>Brief description  <br><br><br>Error level of logging.<br><br>  <br>Content  <br>abstract override fun [e](../-logger/e.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Error level of logging with tag.<br><br>  <br>Content  <br>abstract override fun [e](../-logger/e.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [footer](../-formatted-logger/footer.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws up the text as a header block.<br><br>  <br>Content  <br>abstract override fun [footer](../-formatted-logger/footer.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [header](../-formatted-logger/header.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws up the text as a header block.<br><br>  <br>Content  <br>abstract override fun [header](../-formatted-logger/header.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [i](../-logger/i.md)| [androidJvm]  <br>Brief description  <br><br><br>Info level of logging.<br><br>  <br>Content  <br>abstract override fun [i](../-logger/i.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Info level of logging with tag.<br><br>  <br>Content  <br>abstract override fun [i](../-logger/i.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [line](../-formatted-logger/line.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws a line.<br><br>  <br>Content  <br>abstract override fun [line](../-formatted-logger/line.md)()  <br><br><br>
| [section](../-formatted-logger/section.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws up the text as a section block.<br><br>  <br>Content  <br>abstract override fun [section](../-formatted-logger/section.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [w](../-logger/w.md)| [androidJvm]  <br>Brief description  <br><br><br>Warning level of logging.<br><br>  <br>Content  <br>abstract override fun [w](../-logger/w.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br>abstract override fun [w](../-logger/w.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [UiTestLoggerImpl](../-ui-test-logger-impl/index.md)

