//[kaspresso](../../index.md)/[androidx.test.espresso.web.assertion](../index.md)/[WebAssertionProxy](index.md)



# WebAssertionProxy  
 [androidJvm] 



A proxy-wrapper of WebAssertion for watcherInterceptors calls.



Uses WebViewAssertions.ResultCheckingWebAssertion class, that has package-local access in Espresso, so it has to be in the same package.



class [WebAssertionProxy](index.md)<[E](index.md)>(**webAssertion**: WebAssertion<[E](index.md)>, **matcher**: Matcher<*>, **watcherInterceptors**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[WebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)>) : WebAssertion<[E](index.md)>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [WebAssertionProxy](-web-assertion-proxy.md)|  [androidJvm] fun <[E](index.md)> [WebAssertionProxy](-web-assertion-proxy.md)(webAssertion: WebAssertion<[E](index.md)>, matcher: Matcher<*>, watcherInterceptors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[WebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)>)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| getAtom| [androidJvm]  <br>Content  <br>override fun getAtom(): Atom<[E](index.md)>  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| toViewAssertion| [androidJvm]  <br>Content  <br>override fun toViewAssertion(p0: [E](index.md)): ViewAssertion  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [atom](index.md#androidx.test.espresso.web.assertion/WebAssertionProxy/atom/#/PointingToDeclaration/)|  [androidJvm] override val [atom](index.md#androidx.test.espresso.web.assertion/WebAssertionProxy/atom/#/PointingToDeclaration/): Atom<[E](index.md)>   <br>
| [matcher](index.md#androidx.test.espresso.web.assertion/WebAssertionProxy/matcher/#/PointingToDeclaration/)|  [androidJvm] val [matcher](index.md#androidx.test.espresso.web.assertion/WebAssertionProxy/matcher/#/PointingToDeclaration/): Matcher<*>   <br>
| [webAssertion](index.md#androidx.test.espresso.web.assertion/WebAssertionProxy/webAssertion/#/PointingToDeclaration/)|  [androidJvm] val [webAssertion](index.md#androidx.test.espresso.web.assertion/WebAssertionProxy/webAssertion/#/PointingToDeclaration/): WebAssertion<[E](index.md)>   <br>

