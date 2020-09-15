//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.proxy](../index.md)/[DataAssertionProxy](index.md)



# DataAssertionProxy  
 [androidJvm] 

The proxy-wrapper of ViewAssertion for watcher interceptors calls.

class [DataAssertionProxy](index.md)(**viewAssertion**: ViewAssertion, **watcherInterceptors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)>) : ViewAssertion   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [DataAssertionProxy](-data-assertion-proxy.md)|  [androidJvm] fun [DataAssertionProxy](-data-assertion-proxy.md)(viewAssertion: ViewAssertion, watcherInterceptors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)>)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [check](check.md)| [androidJvm]  <br>Brief description  <br><br><br>Calls watcher interceptors before ViewAssertion.check on wrapped viewAssertion is called.<br><br>  <br>Content  <br>open override fun [check](check.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, noViewFoundException: NoMatchingViewException?)  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

