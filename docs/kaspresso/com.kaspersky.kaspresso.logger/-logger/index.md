//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.logger](../index.md)/[Logger](index.md)



# Logger  
 [androidJvm] 

The interface for base logging with 3 levels: info, debug and error.

interface [Logger](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [d](d.md)| [androidJvm]  <br>Brief description  <br><br><br>Debug level of logging.<br><br>  <br>Content  <br>abstract fun [d](d.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Debug level of logging with tag.<br><br>  <br>Content  <br>abstract fun [d](d.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [e](e.md)| [androidJvm]  <br>Brief description  <br><br><br>Error level of logging.<br><br>  <br>Content  <br>abstract fun [e](e.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Error level of logging with tag.<br><br>  <br>Content  <br>abstract fun [e](e.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [i](i.md)| [androidJvm]  <br>Brief description  <br><br><br>Info level of logging.<br><br>  <br>Content  <br>abstract fun [i](i.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Info level of logging with tag.<br><br>  <br>Content  <br>abstract fun [i](i.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [w](w.md)| [androidJvm]  <br>Brief description  <br><br><br>Warning level of logging.<br><br>  <br>Content  <br>abstract fun [w](w.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br>abstract fun [w](w.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [UiTestLogger](../-ui-test-logger/index.md)

