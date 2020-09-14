//[kautomator](../../index.md)/[com.kaspersky.components.kautomator.screen](../index.md)/[UiScreen](index.md)/[intercept](intercept.md)



# intercept  
[androidJvm]  
Brief description  




Sets the interceptors for the screen. Interceptors will be invoked on all interactions while the screen is active.



The screen is considered active when it is invoked in one of the following ways:

val screen = SomeScreen()  
  
screen { // Active  
    view { click() }  
    ...  
} // Inactive

If you use nesting screens, all interceptors of the screens that became active will be invoked in LIFO order (using Deque).





## See also  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| [UiInterceptor](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/index.md)| <br><br><br><br>
  


## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| configurator| <br><br>Configuration of the interceptors<br><br>
  
  
Content  
fun [intercept](intercept.md)(configurator: [UiInterceptor.Configurator](../../com.kaspersky.components.kautomator.intercept.base/-ui-interceptor/-configurator/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  



