//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.languages](../index.md)/[LanguageImpl](index.md)



# LanguageImpl  
 [androidJvm] 

The implementation of [Language](../-language/index.md)

class [LanguageImpl](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), **context**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [Language](../-language/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [LanguageImpl](-language-impl.md)|  [androidJvm] fun [LanguageImpl](-language-impl.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [switchInApp](switch-in-app.md)| [androidJvm]  <br>Brief description  <br><br><br>Switches language only in the current Application (not in OS!). Please, keep in mind the following fact: If you have switched languages then you need to refresh current screen to get the screen with new language! Also, don't forget to restore the previous language if you don't clean the state of the Application after each test.<br><br>  <br>Content  <br>open override fun [switchInApp](switch-in-app.md)(locale: [Locale](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

