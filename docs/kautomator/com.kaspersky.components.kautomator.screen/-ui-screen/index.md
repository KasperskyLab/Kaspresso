//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.screen](../index.md)/[UiScreen](index.md)



# UiScreen  
 [androidJvm] 



Container class for UiAutomator elements.



This class groups UI elements and grants access to basic actions, such as pressBack



abstract class [UiScreen](index.md)<[T](index.md) : [UiScreen](index.md)<[T](index.md)>> : [UiScreenActions](../-ui-screen-actions/index.md)   


## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [UiScreenActions](../-ui-screen-actions/index.md)| <br><br><br><br>
  


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| T| <br><br>type of your screen, done to enable invoke() for its children<br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [UiScreen](-ui-screen.md)|  [androidJvm] <br><br>type of your screen, done to enable invoke() for its children<br><br>fun [UiScreen](-ui-screen.md)()   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [intercept](intercept.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Sets the interceptors for the screen. Interceptors will be invoked on all interactions while the screen is active.<br><br><br><br>The screen is considered active when it is invoked in one of the following ways:<br><br>val screen = SomeScreen()  <br>  <br>screen { // Active  <br>    view { click() }  <br>    ...  <br>} // Inactive<br><br>If you use nesting screens, all interceptors of the screens that became active will be invoked in LIFO order (using Deque).<br><br><br><br>  <br>Content  <br>fun [intercept](intercept.md)(configurator: [UiInterceptor.Configurator](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-configurator/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [invoke](invoke.md)| [androidJvm]  <br>Brief description  <br><br><br>Operator that allows usage of DSL style<br><br>  <br>Content  <br>operator fun [invoke](invoke.md)(function: [T](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [pressBack](../-ui-screen-actions/press-back.md)| [androidJvm]  <br>Brief description  <br><br><br>Simulates a short press on the BACK button.<br><br>  <br>Content  <br>open override fun [pressBack](../-ui-screen-actions/press-back.md)()  <br><br><br>
| [reset](reset.md)| [androidJvm]  <br>Brief description  <br><br><br>Removes the interceptors from the screen.<br><br>  <br>Content  <br>fun [reset](reset.md)()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [waitForWindowUpdate](../-ui-screen-actions/wait-for-window-update.md)| [androidJvm]  <br>Brief description  <br><br><br>Waits for window update<br><br>  <br>Content  <br>open override fun [waitForWindowUpdate](../-ui-screen-actions/wait-for-window-update.md)(timeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))  <br><br><br>[androidJvm]  <br>Brief description  <br><br><br>Waits for window update for specified package name<br><br>  <br>Content  <br>open override fun [waitForWindowUpdate](../-ui-screen-actions/wait-for-window-update.md)(packageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [packageName](index.md#com.kaspersky.components.kautomator.screen/UiScreen/packageName/#/PointingToDeclaration/)|  [androidJvm] abstract val [packageName](index.md#com.kaspersky.components.kautomator.screen/UiScreen/packageName/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>
| [view](index.md#com.kaspersky.components.kautomator.screen/UiScreen/view/#/PointingToDeclaration/)|  [androidJvm] <br><br>UiDeviceDelegate on which all actions are performed<br><br>open override val [view](index.md#com.kaspersky.components.kautomator.screen/UiScreen/view/#/PointingToDeclaration/): [UiDeviceInteractionDelegate](../../com.kaspersky.components.kautomator.intercept.delegate/-ui-device-interaction-delegate/index.md)   <br>

