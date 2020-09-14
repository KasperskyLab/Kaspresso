//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.activities](../index.md)/[Activities](index.md)



# Activities  
 [androidJvm] 

The interface to work with activities.

interface [Activities](index.md)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getResumed](get-resumed.md)| [androidJvm]  <br>Brief description  <br><br><br>Finds and returns resumed activity if it exists.<br><br>  <br>Content  <br>abstract fun [getResumed](get-resumed.md)(): [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)?  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isCurrent](is-current.md)| [androidJvm]  <br>Brief description  <br><br><br>Checks if passed activity is resumed.<br><br>  <br>Content  <br>abstract fun [isCurrent](is-current.md)(clazz: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<Out [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)>)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [ActivitiesImpl](../-activities-impl/index.md)

