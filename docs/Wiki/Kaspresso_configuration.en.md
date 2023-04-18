# Kaspresso configurator

**Kaspresso** class - is a single point to set Kaspresso parameters. <br>
A developer can customize **Kaspresso** by setting ```Kaspresso.Builder``` at constructors of ```TestCase```, ```BaseTestCase```, ```TestCaseRule```, ```BaseTestCaseRule```.<br>
The example:
```kotlin
class SomeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        beforeEachTest {
            testLogger.i("The beginning")
        }
        afterEachTest {
            testLogger.i("The end")
        }
    }
) {
    // your test
}
```

### Structure

**Kaspresso** configuration contains:

#### Loggers
Kaspresso provides two loggers: `libLogger` and `testLogger`.
```libLogger``` - inner Kaspresso logger <br>
```testLogger``` - logger that is available for developers in tests. <br>
The last one is accessible by ```testLogger``` property in test sections (```before, after, init, transform, run```) in the test DSL (by ```TestContext``` class). <br>
Also, it is available while setting ```Kaspresso.Builder``` if you want to add it to your custom interceptors, for example.

#### Kaspresso interceptors based on Kakao/Kautomator Interceptors.
These interceptors were introduced to simplify and uniform using of [Kakao interceptors](https://github.com/KakaoCup/Kakao#intercepting) and [Kautomator interceptors](https://kasperskylab.github.io/Kaspresso/en/Wiki/Kautomator-wrapper_over_UI_Automator/#intercepting).

_**Important moment**_ about a mixing of Kaspresso interceptors and Kakao/Kautomator interceptors. <br>
Kaspresso interceptors will not work if you set your custom Kakao interceptors by calling of ```Kakao.intercept``` method in the test or set your custom Kautomator interceptors by calling of ```Kautomator.intercept``` in the test. <br>
If you set your custom Kakao interceptors for concrete ```Screen``` or ```KView``` and set argument ```isOverride``` in true then Kaspresso interceptors will not work for concrete ```Screen``` or ```KView``` fully. The same statement is right for Kautomator where a developer interacts with ```UiScreen``` and ```UiBaseView```.

Kaspresso interceptors can be divided into two types:

1. ```Behavior Interceptors``` - are intercepting calls to ```ViewInteraction```, ```DataInteraction```, ```WebInteraction```, ```UiObjectInteraction```, ```UiDeviceInteraction```  and do some stuff. <br>
   **Attention**, we are going to consider some important notes about ```Behavior Interceptors``` at the end of this document.
2. ```Watcher Interceptors``` - are intercepting calls to ```ViewAction```, ```ViewAssertion```, ```Atom```, ```WebAssertion```, ```UiObjectAssertion```, ```UiObjectAction```, ```UiDeviceAssertion```, ```UiDeviceAction```  and do some stuff.

Let's expand mentioned Kaspresso interceptors types:

1. ```Behavior Interceptors```
    1. ```viewBehaviorInterceptors``` - intercept calls to ```ViewInteraction#perform``` and ```ViewInteraction#check```
    2. ```dataBehaviorInterceptors``` - intercept calls to ```DataInteraction#check```
    3. ```webBehaviorInterceptors``` - intercept calls to ```Web.WebInteraction<R>#perform``` and ```Web.WebInteraction<R>#check```
    4. ```objectBehaviorInterceptors``` - intercept calls to ```UiObjectInteraction#perform``` and ```UiObjectInteraction#check```
    5. ```deviceBehaviorInterceptors``` - intercept calls to ```UiDeviceInteraction#perform``` and ```UiDeviceInteraction#check```
2. ```Watcher Interceptors```
    1. ```viewActionWatcherInterceptors``` - do some stuff before ```android.support.test.espresso.ViewAction.perform``` is actually called
    2. ```viewAssertionWatcherInterceptors``` - do some stuff before ```android.support.test.espresso.ViewAssertion.check``` is actually called
    3. ```atomWatcherInterceptors``` - do some stuff before ```android.support.test.espresso.web.model.Atom.transform``` is actually called
    4. ```webAssertionWatcherInterceptors``` - do some stuff before ```android.support.test.espresso.web.assertion.WebAssertion.checkResult``` is actually called
    5. ```objectWatcherInterceptors``` - do some stuff before ```UiObjectInteraction.perform``` or ```UiObjectInteraction.check``` is actually called
    6. ```deviceWatcherInterceptors``` - do some stuff before ```UiDeviceInteraction.perform``` or ```UiDeviceInteraction.check``` is actually called

**Please, remember! Behavior and watcher interceptors work under the hood in every action and assertion of every View of Kakao and Kautomator by default in Kaspresso.**

#### Special Kaspresso interceptors
These interceptors are not based on some lib. Short description:

1. ```stepWatcherInterceptors``` - an interceptor of **Step** lifecycle actions
2. ```testRunWatcherInterceptors``` - an interceptor of entire **Test** lifecycle actions

As you noticed these interceptors are a part of ```Watcher Interceptors```, also.

#### BuildStepReportWatcherInterceptor

This ```watcher interceptor``` by default is included into ```Kaspresso configurator``` to collect your tests steps information for further processing in tests orchestrator. <br>
By default this interceptor is based on ```AllureReportWriter``` (if you don't know what [Allure](http://allure.qatools.ru/) is you should really check on it). <br>
This report writer works with each ```TestInfo``` after test finishing, converts its steps information into [Allure's steps info](https://docs.qameta.io/allure/#_steps) JSON, and then prints JSON into LogCat in the following format:

```
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: TEST PASSED
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: #AllureStepsInfoJson#: [{"attachments":[],"name":"My step 1","parameters":[],"stage":"finished","start":1568790287246,"status":"passed", "steps":[],"stop":1568790288184}]
```

This logs should be processed by your test orchestrator (e.g. [Marathon](https://github.com/Malinskiy/marathon)).
If you use [Marathon](https://github.com/Malinskiy/marathon) you should know that the it [requires](https://github.com/Malinskiy/marathon/releases/tag/0.5.0) 
some additional modifications to support processing this logs and doesn't work as expected at the current moment. But we are working hard on it.

#### Default actions in before/after sections
Sometimes, a developer wishes to put some actions repeating in all tests before/after into a single place to simplify the maintenance of tests. <br>
You can make a remark that there are ```@beforeTest/@afterTest``` annotations to resolve mentioned tasks. But the developer doesn't have an access to ```BaseTestContext``` in those methods.
That's why we have introduced special default actions that you can set in constructor by ```Kaspresso.Builder```. <br>
The example how to implement default actions in ```Kaspresso.Builder``` is: <br>
```kotlin
open class YourTestCase : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        beforeEachTest {
            testLogger.i("beforeTestFirstAction")
        }
        afterEachTest {
            testLogger.i("afterTestFirstAction")
        }
    }
)
```
The full signature of ```beforeEachTest``` is:
```kotlin
beforeEachTest(override = true, action = {
    testLogger.i("beforeTestFirstAction")
})
```
```afterEachTest``` is similar to ```beforeEachTest```. <br>
If you set ```override``` in ```false``` then the final beforeAction will be beforeAction of the parent TestCase plus current ```action```. Otherwise, final beforeAction will be only current ```action```.
How it's work and how to override (or just extend) default action, please,
observe the [example](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/configurator_tests/defaultaction_tests).

#### Device
```Device``` instance. Detailed info is at [Device wiki](https://kasperskylab.github.io/Kaspresso/en/Wiki/Working_with_Android_OS/).

#### AdbServer
```AdbServer``` instance. Detailed info is at [AdbServer wiki](https://kasperskylab.github.io/Kaspresso/en/Wiki/Executing_adb_commands/).

### Kaspresso configuring and Kaspresso interceptors example

The example of how to configure Kaspresso and how to use Kaspresso interceptors is in [here](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/configurator_tests).

### Default Kaspresso settings
```BaseTestCase```, ```TestCase```, ```BaseTestCaseRule```, ```TestCaseRule``` are using default customized **Kaspresso** (```Kaspresso.Builder.simple``` builder). <br>
Most valuable features of default customized **Kaspresso** are below.

#### Logging
Just start [SimpleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/simple_tests/SimpleTest.kt). Next, you will see those logs:
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
I/KASPRESSO: #AllureStepsInfoJson#: [{"attachments":[],"name":"My step 1","parameters":[],"stage":"finished","start":1568790287246,"status":"passed", "steps":[],"stop":1568790288184}]
I/KASPRESSO: ---------------------------------------------------------------------------
```
Pretty good.

#### Defense from flaky tests
If a failure occurs then Kaspresso tries to fix it using a big set of diverse ways. <br>
**This defense works for every action and assertion of each View of Kakao and Kautomator!** You just need to extend your test class from ```TestCase``` (```BaseTestCase```) or to set ```TestCaseRule```(```BaseTestCaseRule```) in your test. <br>
More detailed info about some ways of defense is [below](./03_Kaspresso_configurator.md#some-words-about-behavior-interceptors)

#### Interceptors
Interceptors turned by default:

1. Watcher interceptors
2. Behavior interceptors
3. Kaspresso interceptors
4. BuildStepReportWatcherInterceptor

So, all features described above are available thanks to these interceptors.

### Some words about Behavior Interceptors
Any lib for ui-tests is flaky. It's a hard truth of life. Any action/assert in your test may fail for some undefined reason.

What general kinds of flaky errors exist:

1. Common flaky errors that happened because Espresso/UI Automator was in a bad mood =) <br>
   That's why Kaspresso wraps **all** actions/assertions of Kakao/Kautomator and handles set of potential flaky exceptions.
   If an exception happened then Kaspresso attempts to repeat failed actions/assert for 10 seconds. Such handling rescues developers of any flaky action/assert.<br>
   The details are available at [flakysafety](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/flakysafety) and examples are [here](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/flaky_tests).
2. The reason of a failure is non visibility of a View. In most cases you just need to scroll a parent layout to make the View visible. So, Kaspresso tries to perform it in auto mode. <br>
   The details are available at [autoscroll](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/autoscroll).
3. Also, Kaspresso attempts to remove all system dialogs if it prevents the test execution. <br>
   The details are available at [systemsafety](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/systemsafety).

These handlings are possible thanks to ```BehaviorInterceptors```. Also, you can set your custom processing by ```Kaspresso.Builder```. But remember, the order of ```BehaviorInterceptors``` is significant: the first item will be at the lowest level of intercepting chain, and the last item will be at the highest level.

Let's consider the work principle of ```BehaviorInterceptors``` over Kakao interceptors. The first item actually wraps the ```androidx.test.espresso.ViewInteraction.perform``` call, the second item wraps the first item, and so on. <br>
Have a glance at the order of ```BehaviorInterceptors``` enabled by default in Kaspresso over Kakao. It's:

1. ```AutoScrollViewBehaviorInterceptor```
2. ```SystemDialogSafetyViewBehaviorInterceptor```
3. ```FlakySafeViewBehaviorInterceptor```

Under the hood, all Kakao actions and assertions first of all call ```FlakySafeViewBehaviorInterceptor``` that calls ```SystemDialogSafetyViewBehaviorInterceptor``` and that calls ```AutoScrollViewBehaviorInterceptor```. <br>
If a result of ```AutoScrollViewBehaviorInterceptor``` handling is an error then ```SystemDialogSafetyViewBehaviorInterceptor``` attempts to handle received error. If a result of ```SystemDialogSafetyViewBehaviorInterceptor``` handling is an error too then ```FlakySafeViewBehaviorInterceptor``` attempts to handle received the error. <br>
To simplify the discussed topic we have drawn a picture:

![](https://habrastorage.org/webt/pw/86/73/pw8673a4w4xnnq5nwpy8idfuoue.png)

### Main section enrichers
Developer also can extends parametrized tests functionality by providing ```MainSectionEnricher``` in ```BaseTestCase``` or ```BaseTestCaseRule```.
The main idea of enrichers - allow adding additional test case's steps before and after the main section's ```run``` block.

All you need to do is:

1. Define your enricher implementation for ```MainSectionEnricher``` interface;

```kotlin
class LoggingMainSectionEnricher : MainSectionEnricher<TestCaseData> {
    ...

}
```

Here, ```TestCaseData``` is the same data type as in your ```BaseTestCase``` implementation.

2. Override ```beforeMainSectionRun``` or/and ```afterMainSectionRun``` methods to add your before/after actions;

```kotlin
class LoggingMainSectionEnricher : MainSectionEnricher<TestCaseData> {

    override fun TestContext<TestCaseData>.beforeMainSectionRun(testInfo: TestInfo) {
        testLogger.d("Before main section run... | ${testInfo.testName}")
        step("Check users count...") {
            testLogger.d("Check users count: ${data.users.size}")
        }
    }

    override fun TestContext<TestCaseData>.afterMainSectionRun(testInfo: TestInfo) {
        testLogger.d("After main section run... | ${testInfo.testName}")
        step("Check posts count...") {
            testLogger.d("Check posts count: ${data.posts.size}")
        }
    }

}
```

In ```beforeMainSectionRun``` and ```afterMainSectionRun``` methods you have full access to ```TestContext<TestCaseData``` properties and methods,
so you can use logger, add test case's steps and so on. Also, this methods received ```TestInfo``` parameter.

3. Add your enrichers into your ```BaseTestCase``` implementation.

```kotlin
class EnricherBaseTestCase : BaseTestCase<TestCaseDsl, TestCaseData>(
    kaspresso = Kaspresso.Builder.default(),
    dataProducer = { action -> TestCaseDataCreator.initData(action) },
    mainSectionEnrichers = listOf(
        LoggingMainSectionEnricher(),
        AnalyticsMainSectionEnricher()
    )
)
```

After this manipulations your described actions will be executed before or after main section's ```run``` block.
