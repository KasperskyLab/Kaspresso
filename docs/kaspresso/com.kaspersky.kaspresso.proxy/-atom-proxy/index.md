//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.proxy](../index.md)/[AtomProxy](index.md)



# AtomProxy  
 [androidJvm] 

The proxy-wrapper of Atom for watcher interceptors calls.

class [AtomProxy](index.md)<[T](index.md)>(**atom**: Atom<[T](index.md)>, **matcher**: Matcher<*>, **watcherInterceptors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[AtomWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)>) : Atom<[T](index.md)>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [AtomProxy](-atom-proxy.md)|  [androidJvm] fun <[T](index.md)> [AtomProxy](-atom-proxy.md)(atom: Atom<[T](index.md)>, matcher: Matcher<*>, watcherInterceptors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[AtomWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)>)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getArguments](get-arguments.md)| [androidJvm]  <br>Brief description  <br><br><br>Simply calls Atom.getArguments on wrapped [atom](index.md#com.kaspersky.kaspresso.proxy/AtomProxy/atom/#/PointingToDeclaration/).<br><br>  <br>Content  <br>open override fun [getArguments](get-arguments.md)(elementContext: ElementReference?): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>  <br><br><br>
| [getScript](get-script.md)| [androidJvm]  <br>Brief description  <br><br><br>Simply calls Atom.getScript on wrapped [atom](index.md#com.kaspersky.kaspresso.proxy/AtomProxy/atom/#/PointingToDeclaration/).<br><br>  <br>Content  <br>open override fun [getScript](get-script.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [transform](transform.md)| [androidJvm]  <br>Brief description  <br><br><br>Calls watcher interceptors before Atom.transform on wrapped [atom](index.md#com.kaspersky.kaspresso.proxy/AtomProxy/atom/#/PointingToDeclaration/) is called.<br><br>  <br>Content  <br>open override fun [transform](transform.md)(evaluation: Evaluation?): [T](index.md)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [atom](index.md#com.kaspersky.kaspresso.proxy/AtomProxy/atom/#/PointingToDeclaration/)|  [androidJvm] val [atom](index.md#com.kaspersky.kaspresso.proxy/AtomProxy/atom/#/PointingToDeclaration/): Atom<[T](index.md)>   <br>
| [matcher](index.md#com.kaspersky.kaspresso.proxy/AtomProxy/matcher/#/PointingToDeclaration/)|  [androidJvm] val [matcher](index.md#com.kaspersky.kaspresso.proxy/AtomProxy/matcher/#/PointingToDeclaration/): Matcher<*>   <br>

