//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.proxy](../index.md)/[ViewActionProxy](index.md)



# ViewActionProxy  
 [androidJvm] 

The proxy-wrapper of ViewAction for watcher interceptors calls.

class [ViewActionProxy](index.md)(**viewAction**: ViewAction, **watcherInterceptors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ViewActionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)>) : ViewAction   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ViewActionProxy](-view-action-proxy.md)|  [androidJvm] fun [ViewActionProxy](-view-action-proxy.md)(viewAction: ViewAction, watcherInterceptors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ViewActionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)>)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getConstraints](get-constraints.md)| [androidJvm]  <br>Brief description  <br><br><br>Simply calls ViewAction.getConstraints on wrapped viewAction.<br><br>  <br>Content  <br>open override fun [getConstraints](get-constraints.md)(): Matcher<[View](https://developer.android.com/reference/kotlin/android/view/View.html)>  <br><br><br>
| [getDescription](get-description.md)| [androidJvm]  <br>Brief description  <br><br><br>Simply calls ViewAction.getDescription on wrapped viewAction.<br><br>  <br>Content  <br>open override fun [getDescription](get-description.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [perform](perform.md)| [androidJvm]  <br>Brief description  <br><br><br>Calls watcher interceptors before ViewAction.perform on wrapped viewAction is called.<br><br>  <br>Content  <br>open override fun [perform](perform.md)(uiController: UiController, view: [View](https://developer.android.com/reference/kotlin/android/view/View.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

