## Kaspresso

**Kaspresso** class - is a single point to set Kaspresso parameters. <br>
A developer can customize **Kaspresso** by setting ```Kaspresso.Builder``` at constructors of ```TestCase```, ```BaseTestCase```, ```TestCaseRule```, ```BaseTestCaseRule```.

### Structure

All settings in **Kaspresso** class may be divided into four groups: <br>

1. Loggers <br>
```libLogger``` - inner Kaspresso logger <br>
```testLogger``` - logger available for developers in tests.
It's available by ```testlLogger``` property in test sections (```before, after, init, transform, run```) in test dsl (by ```TestContext``` class). <br>
Also it is availaible while setting ```Kaspresso.Builder``` if you want to add it to your custom interceptors, for example.<br>

2. Kaspresso interceptors based on Kakao Interceptors. <br>
These interceptors were introduced to simplify and uniform using of [Kakao interceptors](https://github.com/agoda-com/Kakao#intercepting).<br> <br>
**Important moment** about a mixing of Kaspresso interceptors and Kakao interceptors. <br>
Kaspresso interceptors will not work if You set your custom Kakao interceptors by calling of ```Kakao.intercept``` method in the test. <br> 
If you set your custom Kakao interceptors for concrete ```Screen``` or ```KView``` and set argument ```isOverride``` in true then Kaspresso interceptors will not work for concrete ```Screen``` or ```KView``` fully. 
<br> <br>
Kaspresso interceptors can be divided into two types: <br>
    1. ```Behavior Interceptors``` - are intercepting calls to ViewInteraction, DataInteraction, WebInteraction and do some stuff. <br>
    2. ```Watcher Interceptors``` - are intercepting calls to ViewAction, ViewAssertion, Atom, WebAssertion and do some stuff. <br>

Let's describe mentioned Kaspresso interceptors shortly: <br>
    1. ```viewActionWatcherInterceptors``` - do some stuff before [android.support.test.espresso.ViewAction.perform] is actually called <br>
    2. ```viewAssertionWatcherInterceptors``` - do some stuff before [android.support.test.espresso.ViewAssertion.check] is actually called <br>
    3. ```atomWatcherInterceptors``` - do some stuff before [android.support.test.espresso.web.model.Atom.transform] is actually called <br>
    4. ```webAssertionWatcherInterceptors``` - do some stuff before [android.support.test.espresso.web.assertion.WebAssertion.checkResult] is actually called <br>
    5. ```viewBehaviorInterceptor``` - intercept calls to ```ViewInteraction#perform``` and ```ViewInteraction#check``` <br>
    6. ```dataBehaviorInterceptor``` - intercept calls to ```DataInteraction#check``` <br>
    7. ```webBehaviorInterceptor``` - intercept calls to ```Web.WebInteraction<R>#perform``` and ```Web.WebInteraction<R>#check``` <br>

4. Special Kaspresso interceptors <br>
These interceptors are not based on some lib. Short description:
    1. ```stepWatcherInterceptors``` - an interceptor of **Step** lifecycle actions
    2. ```testRunWatcherInterceptors``` - an interceptor of entire **Test** lifecycle actions
    
5. Device <br>
```Device``` instance. Detailed info is below.

6. AdbServer <br>
```AdbServer``` instance. Detailed info follows.

### Using

All actions to add Kaspresso in the test where Espresso and Kakao are using is one of:
- extend ```BaseTestCase``` or ```TestCase```
- add ```BaseTestCaseRule``` or ```TestCaseRule``` rule field in your test  

### Configurator and Kaspresso interceptors example

The example how to set Configurator and how to use Kaspresso interceptors are in [com.kaspersky.kaspressample/configurator].

### Default Kaspresso settings
```BaseTestCase```, ```TestCase```, ```BaseTestCaseRule```, ```TestCaseRule``` are using default customized **Kaspresso**. <br>
Most valuable features of default customized **Kaspresso** are below.

##### Logging
Just start [com.kaspersky.kaspressample.simple_tests.SimpleTest]. Next, you will see those logs:
```
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: BEFORE TEST SECTION
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: TEST SECTION
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "1. Open Simple Screen" in SimpleTest
I/KASPRESSO_SPECIAL: I am kLogger
I/ViewInteraction: Checking 'com.kaspersky.kaspresso.proxy.ViewAssertionProxy@95afab5' assertion on view (with id: com.kaspersky.kaspressample:id/activity_main_button_next)
I/KASPRESSO: Check view has effective visibility=VISIBLE on AppCompatButton(id=activity_main_button_next;text=Next;)
I/KASPRESSO: single click on AppCompatButton(id=activity_main_button_next;text=Next;)
I/KASPRESSO: TEST STEP: "1. Open Simple Screen" in SimpleTest SUCCEED. It took 0 minutes, 0 seconds and 618 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "2. Click button_1 and check button_2" in SimpleTest
I/KASPRESSO: single click on AppCompatButton(id=button_1;text=Button 1;)
I/ViewInteraction: Checking 'com.kaspersky.kaspresso.proxy.ViewAssertionProxy@9f38781' assertion on view (with id: com.kaspersky.kaspressample:id/button_2)
I/KASPRESSO: Check view has effective visibility=VISIBLE on AppCompatButton(id=button_2;text=Button 2;)
I/KASPRESSO: TEST STEP: "2. Click button_1 and check button_2" in SimpleTest SUCCEED. It took 0 minutes, 0 seconds and 301 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "3. Click button_2 and check edit" in SimpleTest
I/KASPRESSO: single click on AppCompatButton(id=button_2;text=Button 2;)
I/ViewInteraction: Checking 'com.kaspersky.kaspresso.proxy.ViewAssertionProxy@ad01abd' assertion on view (with id: com.kaspersky.kaspressample:id/edit)
I/KASPRESSO: Check view has effective visibility=VISIBLE on AppCompatEditText(id=edit;text=Some text;)
E/KASPRESSO: Failed to interact with view matching: (with id: com.kaspersky.kaspressample:id/edit) because of AssertionFailedError
I/ViewInteraction: Checking 'com.kaspersky.kaspresso.proxy.ViewAssertionProxy@d0f1c0a' assertion on view (with id: com.kaspersky.kaspressample:id/edit)
I/KASPRESSO: Check view has effective visibility=VISIBLE on AppCompatEditText(id=edit;text=Some text;)
I/ViewInteraction: Checking 'com.kaspersky.kaspresso.proxy.ViewAssertionProxy@3b62c7b' assertion on view (with id: com.kaspersky.kaspressample:id/edit)
I/KASPRESSO: Check with string from resource id: <2131558461> on AppCompatEditText(id=edit;text=Some text;)
I/KASPRESSO: TEST STEP: "3. Click button_2 and check edit" in SimpleTest SUCCEED. It took 0 minutes, 2 seconds and 138 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "4. Check all possibilities of edit" in SimpleTest
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "4.1. Change the text in edit and check it" in SimpleTest
I/KASPRESSO: replace text on AppCompatEditText(id=edit;text=Some text;)
I/KASPRESSO: type text(111) on AppCompatEditText(id=edit;)
I/ViewInteraction: Checking 'com.kaspersky.kaspresso.proxy.ViewAssertionProxy@dbd9c8' assertion on view (with id: com.kaspersky.kaspressample:id/edit)
I/KASPRESSO: Check with text: is "111" on AppCompatEditText(id=edit;text=111;)
I/KASPRESSO: TEST STEP: "4.1. Change the text in edit and check it" in SimpleTest SUCCEED. It took 0 minutes, 0 seconds and 621 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "4.2. Change the text in edit and check it. Second check" in SimpleTest
I/KASPRESSO: replace text on AppCompatEditText(id=edit;text=111;)
I/KASPRESSO: type text(222) on AppCompatEditText(id=edit;)
I/ViewInteraction: Checking 'com.kaspersky.kaspresso.proxy.ViewAssertionProxy@b8ca74' assertion on view (with id: com.kaspersky.kaspressample:id/edit)
I/KASPRESSO: Check with text: is "222" on AppCompatEditText(id=edit;text=222;)
I/KASPRESSO: TEST STEP: "4.2. Change the text in edit and check it. Second check" in SimpleTest SUCCEED. It took 0 minutes, 0 seconds and 403 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "4. Check all possibilities of edit" in SimpleTest SUCCEED. It took 0 minutes, 1 seconds and 488 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: AFTER TEST SECTION
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: TEST PASSED
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: ---------------------------------------------------------------------------
```
Pretty good.

##### Screenshots
A developer receives a screenshot after each Step and after any error. Screenshots are saving at Device in "sdcard/screenshots". 

##### Handling of flaky errors
Any lib for ui-tests is flaky. It's a hard truth of life. Any action/assert in your test may fail for some undefined reason. <br>
That's why Kaspresso wraps **all** actions/assertions of Kakao and handles set of potential flaky exceptions.
If an exception happened then Kaspresso attempts to repeat failed actions/assert for 2 seconds. Such handling rescues developers of any flaky action/assert.<br>
If the fault is due to an interaction with an element which is not visible and to which you need to scroll, Kaspresso will try to fix it by performing an autoscroll action by itself.
Also, Kaspresso attempts to remove all system dialogs if it prevents the test executes.
