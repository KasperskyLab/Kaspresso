## **Configurator**

**Configurator** - is a single point to set Kaspresso. <br>

### **Structure**

All settings in **Configurator** are may be divided into four groups: <br>

1. Loggers <br>
```logger``` - inner Kaspresso logger <br>
```externalLogger``` - logger enabled for developers in tests <br>

2. Kaspresso interceptors based on Kakao Interceptors. <br>
These interceptors were introduced to simplify and uniform using of [Kakao interceptors](https://github.com/agoda-com/Kakao#intercepting).<br> <br>
**Important moment** about a mixing of Kaspresso interceptors and Kakao interceptors. <br>
Kaspresso interceptors will not work if You set your custom Kakao interceptors by calling of ```Kakao.intercept``` method in the test. <br> 
If you set your custom Kakao interceptors for concrete ```Screen``` or ```KView``` and set argument ```isOverride``` in true then Kaspresso interceptors will not work for concrete ```Screen``` or ```KView``` fully. 
<br> <br> 
Let's describe mentioned Kaspresso interceptors shortly: <br>
    1. ```viewActionInterceptors``` - do some stuff before [android.support.test.espresso.ViewAction.perform] is actually called <br>
    2. ```viewAssertionInterceptors``` - do some stuff before [android.support.test.espresso.ViewAssertion.check] is actually called <br>
    3. ```atomInterceptors``` - do some stuff before [android.support.test.espresso.web.model.Atom.transform] is actually called <br>
    4. ```webAssertionInterceptors``` - do some stuff before [android.support.test.espresso.web.assertion.WebAssertion.checkResult] is actually called <br>
    5. ```executingInterceptor``` - do some stuff and **actually execute** an action or an assertion <br>
    6. ```failureInterceptor``` - an interceptor that is called on failures. It's [FailureInterceptor.interceptAndThrow] method is being provide as the default [android.support.test.espresso.FailureHandler].

3. Clean Kaspresso interceptors <br>
These interceptors are not based on some lib. Short description:
    1. ```stepInterceptors``` - an interceptor of **Step** lifecycle actions
    2. ```testRunInterceptors``` - an interceptor of entire **Test** lifecycle actions
    
4. Other fields are to ```Device``` class. Detailed info is below. 

### **Using**

As we wrote above all actions to add Kaspresso in a test where Espresso and Kakao are using is one of:
- extend ```BaseTestCase``` or ```TestCase```
- add ```BaseTestCaseRule``` or ```TestCaseRule``` rule field in your test  

### **Configurator and Kaspresso interceptors example**

The example how to set Configurator and how to use Kaspresso interceptors are in [com.kaspersky.kaspressample/configurator].