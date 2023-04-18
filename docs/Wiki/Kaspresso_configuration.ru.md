# Конфигуратор Kaspresso 

Класс **Kaspresso** — это единственная точка для установки параметров Kaspresso. <br>
Разработчик может настроить **Kaspresso**, установив ```Kaspresso.Builder``` в конструкторах ```TestCase```, ```BaseTestCase```, ```TestCaseRule```, ``` BaseTestCaseRule```.<br>
Пример:
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
    // ваш тест
}
```

### Структура

Конфигурация **Kaspresso** содержит:

#### Логгеры
Kaspresso предоставляет два вида логгеров: `libLogger` и `testLogger`.
```libLogger``` - внутренний логгер Kaspresso <br>
```testLogger``` - логгер, который доступен разработчикам в тестах. <br>
Последний доступен через свойство ```testLogger``` в тестовых разделах (```before, after, init, transform, run```) в тестовом DSL (из класса ```TestContext```). <br>
Кроме того, он доступен при настройке ```Kaspresso.Builder```, если вы хотите добавить его, например, в свои пользовательские перехватчики.

#### Перехватчики Kaspresso на базе перехватчиков Kakao/Kautomator.
Эти перехватчики были введены для упрощения и единообразия использования [перехватчиков Kakao](https://github.com/KakaoCup/Kakao#intercepting) и [перехватчиков Kautomator](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Kautomator-wrapper_over_UI_Automator/#interceptor-).

_**Важный момент**_ о смешении перехватчиков Kaspresso и перехватчиков Kakao/Kautomator. <br>
Перехватчики Kaspresso не будут работать, если вы установите свои собственные перехватчики Kakao, вызвав метод ```Kakao.intercept``` в тесте, или установите свои пользовательские перехватчики Kautomator, вызвав ```Kautomator.intercept``` в тесте. <br>
Если вы установите свои пользовательские перехватчики Kakao для конкретного экрана или KView и установите для аргумента isOverride значение true, то перехватчики Kaspresso не будут работать для конкретного экрана или ```KView```. То же самое верно и для Kautomator, где разработчик взаимодействует с ```UiScreen``` и ```UiBaseView```.

Перехватчики Kaspresso можно разделить на два типа: <br>

1. ```Behavior Interceptors``` - перехватывают вызовы ```ViewInteraction```, ```DataInteraction```, ```WebInteraction```, ```UiObjectInteraction```, ``` UiDeviceInteraction``` и выполняют свою логику. <br>
   **Внимание**, мы собираемся рассмотреть некоторые важные примечания о ```перехватчиках поведения``` в конце этого документа.
2. ```Watcher Interceptors``` - перехватывают вызовы ```ViewAction```, ```ViewAssertion```, ```Atom```, ```WebAssertion```, ``` UiObjectAssertion```, ```UiObjectAction```, ```UiDeviceAssertion```, ```UiDeviceAction``` и еще кое-что.

Расширим упомянутые типы перехватчиков Kaspresso:

1. ```Behavior Interceptors```
    1. ```viewBehaviorInterceptors``` - перехватывают вызовы ```ViewInteraction#perform``` и ```ViewInteraction#check```
    2. ```dataBehaviorInterceptors``` - перехватывают вызовы ```DataInteraction#check```
    3. ```webBehaviorInterceptors``` - перехватывают вызовы ```Web.WebInteraction<R>#perform``` и ```Web.WebInteraction<R>#check```
    4. ```objectBehaviorInterceptors``` - перехватывают вызовы ```UiObjectInteraction#perform``` и ```UiObjectInteraction#check```
    5. ```deviceBehaviorInterceptors``` - перехватывают вызовы ```UiDeviceInteraction#perform``` и ```UiDeviceInteraction#check```
2. ```Watcher Interceptors```
    1. ```viewActionWatcherInterceptors``` – выполняют какие-то действия до того, как будет вызван ```android.support.test.espresso.ViewAction.perform```
    2. ```viewAssertionWatcherInterceptors``` – выполняют какие-то действия до того, как будет вызван ```android.support.test.espresso.ViewAssertion.check```
    3. ```atomWatcherInterceptors``` – выполняют какие-то действия до того, как будет вызван ```android.support.test.espresso.web.model.Atom.transform```
    4. ```webAssertionWatcherInterceptors``` — выполняют какие-то действия до того, как будет вызван ```android.support.test.espresso.web.assertion.WebAssertion.checkResult```
    5. ```objectWatcherInterceptors``` - выполняют какие-то действия до того, как будет вызван ```UiObjectInteraction.perform``` или ```UiObjectInteraction.check```
    6. ```deviceWatcherInterceptors``` - выполняют какие-то действия до того, как будет вызван ```UiDeviceInteraction.perform``` или ```UiDeviceInteraction.check```

**Пожалуйста, помните! Перехватчики поведения и наблюдателя работают под капотом в каждом действии (actions) и утверждении (assertions) каждого графического элемента (View) Kakao и Kautomator по умолчанию в Kaspresso.**

#### Специальные перехватчики Kaspresso
Эти перехватчики не основаны на какой-то lib. Краткое описание:

1. ```stepWatcherInterceptors``` - перехватчик действий жизненного цикла **Step**
2. ```testRunWatcherInterceptors``` - перехватчик всех действий жизненного цикла **Test**.

Как вы заметили, эти перехватчики также являются частью Watcher Interceptors.

#### BuildStepReportWatcherInterceptor

Этот ```watcher interceptor``` по умолчанию включен в ```Kaspresso configurator``` для сбора информации о шагах ваших тестов для дальнейшей обработки в оркестраторе тестов. <br>
Этот перехватчик основан на ```AllureReportWriter``` (подробнее про [Allure](http://allure.qatools.ru/)). <br>
Этот генератор отчетов работает с каждым ```TestInfo``` после завершения теста, преобразует информацию о шагах в [информацию о шагах Allure](https://docs.qameta.io/allure/#_steps) JSON, а затем печатает JSON в LogCat в следующем формате:

```
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: TEST PASSED
I/KASPRESSO: ---------------------------------------------------------------------------
I/KASPRESSO: #AllureStepsInfoJson#: [{"attachments":[],"name":"My step 1","parameters":[],"stage":"finished","start":1568790287246,"status":"passed", "steps":[],"stop":1568790288184}]
```

Эти журналы должны обрабатываться вашим тестовым оркестратором (например, [Marathon](https://github.com/Malinskiy/marathon)).
Если вы используете [Marathon](https://github.com/Malinskiy/marathon), вы должны знать, что он [требует](https://github.com/Malinskiy/marathon/releases/tag/0.5.0)
некоторых дополнительных модификаций для поддержки обработки этих журналов и в настоящий момент не работает должным образом. Но мы усердно работаем над этим.

#### Действия по умолчанию в разделах до/после
Иногда разработчик хочет поместить некоторые действия, повторяющиеся во всех тестах до/после, в одно место, чтобы упростить поддержку тестов. <br>
Существуют аннотации ```@beforeTest/@afterTest``` для решения указанных задач. Но у разработчика нет доступа к ```BaseTestContext``` в этих методах.
Вот почему мы ввели специальные действия по умолчанию, которые вы можете установить в конструкторе с помощью ```Kaspresso.Builder```. <br>
Пример реализации действий по умолчанию в ```Kaspresso.Builder```: <br>
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
Полная сигнатура метода ```beforeEachTest```:
```kotlin
beforeEachTest(override = true, action = {
    testLogger.i("beforeTestFirstAction")
})
```
```afterEachTest``` аналогичен ```beforeEachTest```. <br>
Если вы установите ```override``` в ```false```, то последнее `beforeAction` будет относиться к родительскому TestCase плюс текущий  ```action```. В противном случае последний `beforeAction` будет только текущим  ```action```.
Чтобы понять, как это работает и как переопределить (или просто расширить) действие по умолчанию, пожалуйста,
обратите внимание на [пример](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/configurator_tests/defaultaction_tests).

#### Device
Экземпляр ```Device```. Подробная информация находится на [этой странице](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Working_with_Android_OS/) в разделе Вики.

#### AdbServer
Экземпляр ```AdbServer```. Подробная информация находится на [этой странице](https://kasperskylab.github.io/Kaspresso/en/Wiki/Executing_adb_commands/) в разделе Вики.

### Настройка Kaspresso и пример перехватчиков Kaspresso

Пример того, как настроить Kaspresso и как использовать перехватчики Kaspresso, находится [здесь](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/configurator_tests).

### Настройки Kaspresso по умолчанию
`BaseTestCase`, `TestCase`, `BaseTestCaseRule`, `TestCaseRule` используют настроенный по умолчанию **Kaspresso** (```Kaspresso.Builder.simple конфигуратор``` ). <br>
Ниже приведены наиболее ценные функции настроенного по умолчанию **Kaspresso**.

#### Ведение журнала
Просто запустите [SimpleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/simple_tests/SimpleTest.kt). Далее вы увидите эти логи:
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
Довольно хорошо.

#### Защита от flaky тестов
Если происходит сбой, Kaspresso пытается исправить его, используя большой набор разнообразных способов. <br>
**Эта защита работает для каждого действия и проверки каждого View Kakao и Kautomator!** Вам просто нужно расширить свой тестовый класс из ```TestCase``` (```BaseTestCase```) или установить `TestCaseRule` (```BaseTestCaseRule```) в вашем тесте. <br>

#### Перехватчики
Включенные по умолчанию перехватчики:

1. Watcher interceptors
2. Behavior interceptors
3. Kaspresso interceptors
4. BuildStepReportWatcherInterceptor

Все описанные выше возможности доступны благодаря этим перехватчикам.

### Несколько слов о `Behavior Interceptors`
Любая библиотека для UI-тестов нестабильна. Это суровая правда жизни. Любое действие/проверка в вашем тесте может завершиться ошибкой по какой-то неопределенной причине.

Какие общие виды флакающих ошибок существуют:
1. Распространенные плавающие ошибки (флаки), возникающие из-за того, что Espresso/UI Automator был в плохом настроении =) <br>
   Вот почему Kaspresso оборачивает **все** действия/проверки (actions/assertions) Kakao/Kautomator и обрабатывает набор потенциально плавающих исключений.
   Если произошло исключение, Kaspresso пытается повторить неудачные действия/проверку в течение 10 секунд. Такая обработка избавляет разработчиков от любых ненадежных действий/проверок.<br>
   Подробности доступны по ссылке [flakysafety](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/flakysafety), а примеры — [здесь](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/flaky_tests).
2. Невидимость View. В большинстве случаев вам просто нужно прокрутить экран вниз, чтобы View стало видимым. Итак, Kaspresso пытается выполнить это в автоматическом режиме. <br>
   Подробности доступны на странице [autoscroll](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/autoscroll).
3. Также Kaspresso пытается закрыть все системные диалоги, если это препятствует выполнению теста. <br>
   Подробности доступны на странице [systemsafety](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/systemsafety).

Эти обработки возможны благодаря ```BehaviorInterceptors```. Кроме того, вы можете установить собственную обработку с помощью ```Kaspresso.Builder```. Но помните, порядок ```BehaviorInterceptors``` имеет значение: первый элемент будет на самом низком уровне цепочки перехвата, а последний элемент будет на самом высоком уровне.

Рассмотрим принцип работы ```BehaviorInterceptors```над перехватчиками Kakao. Первый элемент фактически является оболочкой для вызова ```androidx.test.espresso.ViewInteraction.perform```, второй элемент является оболочкой для первого элемента и так далее. <br>
Взгляните на порядок включения ```BehaviorInterceptors``` по умолчанию в Kaspresso поверх Kakao. Это:

1. ```AutoScrollViewBehaviorInterceptor```
2. ```SystemDialogSafetyViewBehaviorInterceptor```
3. ```FlakySafeViewBehaviorInterceptor```

Под капотом все действия и проверки Kakao в первую очередь вызывают `FlakySafeViewBehaviorInterceptor`, который вызывает `SystemDialogSafetyViewBehaviorInterceptor`, а тот вызывает ```AutoScrollViewBehaviorInterceptor```. <br>
Если результатом обработки `AutoScrollViewBehaviorInterceptor` является ошибка, то `SystemDialogSafetyViewBehaviorInterceptor` пытается обработать полученную ошибку. Если результатом обработки ```SystemDialogSafetyViewBehaviorInterceptor``` также является ошибка, тогда ```FlakySafeViewBehaviorInterceptor``` попытается обработать полученную ошибку. <br>
Для упрощения обсуждаемой темы нарисовали картинку:

![](https://habrastorage.org/webt/pw/86/73/pw8673a4w4xnnq5nwpy8idfuoue.png)

### Дополнения основной секции
Разработчик также может расширить функциональность параметризованных тестов, предоставив ```MainSectionEnricher``` в ```BaseTestCase``` или ```BaseTestCaseRule```.
Основная идея - позволить добавить дополнительные шаги тест-кейса до и после главной секции `run`.

Все, что вам нужно сделать, это:

1. Определите свою реализацию для интерфейса ```MainSectionEnricher```;

```kotlin
class LoggingMainSectionEnricher : MainSectionEnricher<TestCaseData> {
    ...

}
```

Здесь ```TestCaseData``` - это тот же тип данных, что и в вашей реализации ```BaseTestCase```.

2. Переопределите методы ``beforeMainSectionRun`` и/или ```afterMainSectionRun```, чтобы добавить свои действия до/после;

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

В методах ``beforeMainSectionRun`` и ```afterMainSectionRun``` у вас есть полный доступ к свойствам и методам ```TestContext<TestCaseData```,  так что вы можете использовать логгер, добавлять тестовые шаги и так далее. Также, эти методы получили параметр ```TestInfo```.

3. Добавьте написанные классы в свою реализацию ```BaseTestCase```.

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

После того, как это будет сделано, описанные вами действия будут выполняться до или после блока ```run``` основной секции.
