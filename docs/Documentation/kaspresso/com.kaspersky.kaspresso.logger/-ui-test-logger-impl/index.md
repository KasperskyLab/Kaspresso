//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.logger](../index.md)/[UiTestLoggerImpl](index.md)



# UiTestLoggerImpl  
 [androidJvm] 

The default implementation of [UiTestLogger](../-ui-test-logger/index.md) using [android.util.Log](https://developer.android.com/reference/kotlin/android/util/Log.html).

class [UiTestLoggerImpl](index.md)(**tag**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [UiTestLogger](../-ui-test-logger/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiTestLoggerImpl](-ui-test-logger-impl.md)|  [androidJvm] fun [UiTestLoggerImpl](-ui-test-logger-impl.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [d](d.md)| [androidJvm]  <br>Brief description  <br><br><br>Debug level of logging.<br><br>  <br>Content  <br>open override fun [d](d.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Debug level of logging with tag.<br><br>  <br>Content  <br>open override fun [d](d.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [e](e.md)| [androidJvm]  <br>Brief description  <br><br><br>Error level of logging.<br><br>  <br>Content  <br>open override fun [e](e.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Error level of logging with tag.<br><br>  <br>Content  <br>open override fun [e](e.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [footer](footer.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws up info [i](i.md) as header block.<br><br>  <br>Content  <br>open override fun [footer](footer.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [header](header.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws up info [i](i.md) as header block.<br><br>  <br>Content  <br>open override fun [header](header.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [i](i.md)| [androidJvm]  <br>Brief description  <br><br><br>Info level of logging.<br><br>  <br>Content  <br>open override fun [i](i.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Info level of logging with tag.<br><br>  <br>Content  <br>open override fun [i](i.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [line](line.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws line info.<br><br>  <br>Content  <br>open override fun [line](line.md)()  <br><br><br>
| [section](section.md)| [androidJvm]  <br>Brief description  <br><br><br>Draws up info [i](i.md) as section block.<br><br>  <br>Content  <br>open override fun [section](section.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [w](w.md)| [androidJvm]  <br>Brief description  <br><br><br>Warning level of logging.<br><br>  <br>Content  <br>open override fun [w](w.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br>open override fun [w](w.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>

