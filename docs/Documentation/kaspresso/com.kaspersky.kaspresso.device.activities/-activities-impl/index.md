//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.activities](../index.md)/[ActivitiesImpl](index.md)



# ActivitiesImpl  
 [androidJvm] 

The implementation of the [Activities](../-activities/index.md) interface.

class [ActivitiesImpl](index.md)(**logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [Activities](../-activities/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ActivitiesImpl](-activities-impl.md)|  [androidJvm] fun [ActivitiesImpl](-activities-impl.md)(logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [assertCurrentActivity](assert-current-activity.md)| [androidJvm]  <br>Brief description  <br><br><br>A form of [isCurrent](is-current.md) method for simplified usage.<br><br>  <br>Content  <br>inline fun <[T](assert-current-activity.md) : [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)> [assertCurrentActivity](assert-current-activity.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getResumed](get-resumed.md)| [androidJvm]  <br>Brief description  <br><br><br>Finds and returns resumed activity if it exists, otherwise logs error.<br><br>  <br>Content  <br>open override fun [getResumed](get-resumed.md)(): [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)?  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isCurrent](is-current.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if passed activity is resumed.<br><br>  <br>Content  <br>open override fun [isCurrent](is-current.md)(clazz: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)>)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

