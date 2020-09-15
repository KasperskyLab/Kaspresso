//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.screens](../index.md)/[KScreen](index.md)



# KScreen  
 [androidJvm] abstract class [KScreen](index.md)<[T](index.md) : [KScreen](index.md)<[T](index.md)>> : Screen<[T](index.md)>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| addInterceptors| [androidJvm]  <br>Content  <br>override fun addInterceptors()  <br><br><br>
| closeSoftKeyboard| [androidJvm]  <br>Content  <br>open override fun closeSoftKeyboard()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| intercept| [androidJvm]  <br>Content  <br>override fun intercept(configurator: Interceptor.Configurator.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| invoke| [androidJvm]  <br>Content  <br>operator override fun invoke(function: [T](index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| pressBack| [androidJvm]  <br>Content  <br>open override fun pressBack()  <br><br><br>
| pressKey| [androidJvm]  <br>Content  <br>open override fun pressKey(key: EspressoKey)  <br>open override fun pressKey(keyCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| pressMenuKey| [androidJvm]  <br>Content  <br>open override fun pressMenuKey()  <br><br><br>
| removeInterceptors| [androidJvm]  <br>Content  <br>override fun removeInterceptors()  <br><br><br>
| reset| [androidJvm]  <br>Content  <br>override fun reset()  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [dataInterceptor](index.md#com.kaspersky.kaspresso.screens/KScreen/dataInterceptor/#/PointingToDeclaration/)|  [androidJvm] override var [dataInterceptor](index.md#com.kaspersky.kaspresso.screens/KScreen/dataInterceptor/#/PointingToDeclaration/): Interceptor<DataInteraction, ViewAssertion, ViewAction>?   <br>
| [isActive](index.md#com.kaspersky.kaspresso.screens/KScreen/isActive/#/PointingToDeclaration/)|  [androidJvm] override var [isActive](index.md#com.kaspersky.kaspresso.screens/KScreen/isActive/#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [layoutId](index.md#com.kaspersky.kaspresso.screens/KScreen/layoutId/#/PointingToDeclaration/)|  [androidJvm] abstract val [layoutId](index.md#com.kaspersky.kaspresso.screens/KScreen/layoutId/#/PointingToDeclaration/): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?   <br>
| [rootView](index.md#com.kaspersky.kaspresso.screens/KScreen/rootView/#/PointingToDeclaration/)|  [androidJvm] open override var [rootView](index.md#com.kaspersky.kaspresso.screens/KScreen/rootView/#/PointingToDeclaration/): KBaseView<*>?   <br>
| [view](index.md#com.kaspersky.kaspresso.screens/KScreen/view/#/PointingToDeclaration/)|  [androidJvm] open override val [view](index.md#com.kaspersky.kaspresso.screens/KScreen/view/#/PointingToDeclaration/): ViewInteractionDelegate   <br>
| [viewClass](index.md#com.kaspersky.kaspresso.screens/KScreen/viewClass/#/PointingToDeclaration/)|  [androidJvm] abstract val [viewClass](index.md#com.kaspersky.kaspresso.screens/KScreen/viewClass/#/PointingToDeclaration/): [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)<*>?   <br>
| [viewInterceptor](index.md#com.kaspersky.kaspresso.screens/KScreen/viewInterceptor/#/PointingToDeclaration/)|  [androidJvm] override var [viewInterceptor](index.md#com.kaspersky.kaspresso.screens/KScreen/viewInterceptor/#/PointingToDeclaration/): Interceptor<ViewInteraction, ViewAssertion, ViewAction>?   <br>
| [webInterceptor](index.md#com.kaspersky.kaspresso.screens/KScreen/webInterceptor/#/PointingToDeclaration/)|  [androidJvm] override var [webInterceptor](index.md#com.kaspersky.kaspresso.screens/KScreen/webInterceptor/#/PointingToDeclaration/): Interceptor<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>?   <br>

