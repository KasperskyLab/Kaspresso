# Kautomator: оболочка над UI Automator

**Kautomator** — Хороший и простой DSL для UI Automator в Kotlin, который позволяет ускорить работу самого UI Automator.<br>
Вдохновлено [Kakao](https://github.com/KakaoCup/Kakao) и [выступлением о UI Automator](https://youtu.be/bqNguUHK3SM) (спасибо Светлане Смельчаковой).

### Введение
Тесты, написанные с помощью UI Automator, нечитаемые и сложные в обслуживании, особенно для тестировщиков.
Взгляните на типичный фрагмент кода, написанный с помощью UI Automator:

```kotlin
val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
val uiDevice = UiDevice.getInstance(instrumentation)

val uiObject = uiDevice.wait(
    Until.findObject(
        By.res(
            "com.kaspersky.kaspresso.sample_kautomator",
            "editText"
        )
    ),
    2_000
)

uiObject.text = "Kaspresso"
assertEquals(uiObject.text, "Kaspresso")
```
Это пример только для ввода и проверки текста. Поскольку у нас есть успешный опыт использования Kakao, мы решили таким же образом обернуть UI Automator и назвали его **Kautomator**:
```kotlin
mainScreen {
    simpleEditText {
        replaceText("Kaspresso")
        hasText("Kaspresso")
    }
}
```

Еще одним большим преимуществом **Kautomator** является возможность ускорения UI Automator.<br>
Взгляните на видео ниже:

![](https://habrastorage.org/webt/ti/kv/ki/tikvkij1vjesnacrxqm-lk0coly.gif) <br>
Левое видео — это улучшенный UI Automator, правое видео — это UI Automator по умолчанию.

Почему это возможно? Подробности [доступны ниже](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Kautomator-wrapper_over_UI_Automator/#ui-automator).

#### Преимущества
- Читабельность
- Повторное использование
- Расширяемый DSL
- Удивительная скорость!

### Как это использовать
#### Создать экран
Создайте свой объект `UiScreen`, куда вы добавите UI-элементы, участвующие во взаимодействии тестов:
```kotlin
class FormScreen : UiScreen<FormScreen>()
```
`UiScreen` может представлять весь пользовательский интерфейс или его часть.
Если вы используете [шаблон Page Object](https://martinfowler.com/bliki/PageObject.html), вы можете поместить взаимодействия с Kautomator внутри Page Objects.

#### Создание UiView
`UiScreen` содержит различные `UiView`. Внутри UiScreen описываются все UI-элементы, с которыми будет взаимодействовать тест:

```kotlin
class FormScreen: UiScreen<FormScreen>() {
    val phone = UiView { withId(this@FormScreen.packageName, "phone") }
    val email = UiEditText { withId(this@FormScreen.packageName, "email_edit") }
    val submit = UiButton { withId(this@FormScreen.packageName, "submit_button") }
}
```
Kautomator предоставляет различные типы в зависимости от типа UI-элемента:

* `UiView`
* `UiEditText`
* `UiTextView`
* `UiButton`
* `UiCheckbox`
* `UiChipGroup`
* `UiSwitchView`
* `UiScrollView`
* <b>и многое другое</b>


Каждый `UiView` содержит Matcher-ы для получения экземпляра `ViewInteraction`-а. Некоторые примеры Matcher-ов из Kakao:

* `withId`
* `withText`
* `withPackage`
* `withContentDescription`
* `textStartsWith`
* <b>и многое другое</b>

Как и в Ui Automator, вы можете комбинировать разные Matcher-ы:
```kotlin
val email = UiEditText {
    withId(this@FormScreen.packageName, "email")
    withText(this@FormScreen.packageName, "matsyuk@kaspresso.com")
}
```

#### Реализация взаимодействия

Синтаксис теста с Kautomator очень прост, как только вы определили `UiScreen` и `UiView`, вам нужно только выполнить action или assertion, как в UI Automator:
```kotlin
FormScreen {
    phone {
       hasText("971201771")
    }
    button {
       click()
    }
}
```

#### Отличие от Kakao-Espresso

В Espresso все взаимодействие с `View` обрабатывается через `ViewInteraction`, который имеет два основных метода: `onCheck` и `onPerform`, которые принимают `ViewAction` и `ViewAssertion` в качестве аргументов. Kakao был написан на основе этой архитектуры.

Итак, мы поставили перед собой цель написать Kautomator, максимально похожий на Kakao. Вот почему мы ввели дополнительный слой поверх UiObject2 и UiDevice, который так похож на `ViewInteraction`. Этот уровень представлен `UiObjectInteraction` и `UiDeviceInteraction`, которые имеют два метода: `onCheck` и `onPerform`, принимающие UiObjectAssertion и UiObjectAction или UiDeviceAssertion и UiDeviceAction в качестве аргументов.

`UiObjectInteraction` предназначен для работы с конкретным `View`, таким как `ViewInteraction`. `UiDeviceInteraction` был создан, потому что UI Automator имеет функцию, позволяющую вам выполнять некоторые системные действия, такие как нажатие кнопки «Домой» или кнопки «Назад», открытие быстрых настроек, открытие уведомлений и так далее. Все подобные вещи скрыты классом `UiSystem`.

Так что, наслаждайтесь =)

### Кастомные UI-элементы

Если у вас есть нестандартные (кастомные) UI-элементы в ваших тестах и вы хотите создать свой собственный `UiView`, у нас есть `UiBaseView`. Просто унаследуйте этот класс и реализуйте столько дополнительных интерфейсов Action/Assertion, сколько хотите.
Вам также необходимо переопределить конструкторы, которые вам нужны.

```kotlin
class UiMyView : UiBaseView<KView>, UiMyActions, UiMyAssertions {
    constructor(selector: UiViewSelector) : super(selector)
    constructor(builder: UiViewBuilder.() -> Unit) : super(builder)
}
```

### Работа interceptor-ов

Если вам нужно добавить свою логику во время цепочки вызовов `Kautomator -> UI Automator` (например, ведение журнала) или если вам нужно полностью изменить `UiAssertion` или `UiAction`, которые отправляются в UI Automator во время выполнения, в некоторых случаях можно использовать механизм перехвата.

Перехватчики — это лямбда-выражения, которые вы передаете конфигурационному DSL. Они будут вызываться перед реальными вызовами внутри классов `UiObject2` и `UiDevice` в UI Automator.

У вас есть возможность предоставлять перехватчики на 3 разных уровнях: время выполнения Kautomator, на уровне ваших классов `UiScreen` и на уровне отдельного экземпляра `UiView`.

При каждом вызове функции UI Automator, которую можно перехватить, Kautomator агрегирует все доступные перехватчики для этого конкретного вызова и вызывает их в следующем порядке: `UiView interceptor -> Active Screens interceptors -> Kautomator interceptor`.

Каждый из перехватчиков может разорвать вызов цепочки, установив `isOverride`в true во время настройки.
В этом случае Kautomator не только перестанет вызывать оставшиеся перехватчики в цепочке, но и не будет выполнять вызовы UI Automator. Это означает, что в таком случае ответственность за фактический вызов KAutomator лежит на плечах разработчика.

Вот примеры конфигураций:
```kotlin
class SomeTest {
    @Before
    fun setup() {
        KautomatorConfigurator { // Kautomator runtime
            intercept {
                onUiInteraction { // Перехват вызовов классов UiInteraction во время выполнения
                    onPerform { uiInteraction, uiAction -> // Перехватываем вызов execute()
                        testLogger.i("KautomatorIntercept", "interaction=$uiInteraction, action=$uiAction")
                    }
                }
            }
        }
    }

    @Test
    fun test() {
        MyScreen {
            intercept {
                onUiInteraction { // Перехват вызовов классов UiInteraction в контексте MyScreen
                    onCheck { uiInteraction, uiAssert -> // Перехват вызова check()
                        testLogger.i("KautomatorIntercept", "interaction=$uiInteraction, assert=$uiAssert")
                    }
                }
            }

            myView {
                intercept { // Перехват вызовов ViewInteraction для этого отдельного UI-элемента
                    onPerform(true) { uiInteraction, uiAction -> // Перехватываем вызов execute() и переопределяем цепочку
                        // При выполнении действий над этим элементом не будет вызываться перехватчик уровня Kautomator
                        // и теперь нам нужно вручную вызывать UI Automator.
                        Log.d("KAUTOMATOR_VIEW", "$uiInteraction выполняет $uiAction")
                        uiInteraction.perform(uiAction)
                    }
                }
            }
        }
    }
}
```

### Ускорияем UI Automator
Как вы помните, мы рассказывали о возможном ускорении UI Automator. Как это становится возможным? <br>
UI Automator имеет внутренний механизм для предотвращения потенциальной нестабильности. Под капотом библиотека слушает и отдает команды через AccessibilityManagerService. AccessibilityManagerService — это единая точка для всех событий доступности в системе. В какой-то момент создатели UI Automator столкнулись с проблемой ненадёжности. Одной из самых популярных причин такого неопределенного поведения является большое количество обрабатываемых в системе событий в текущий момент. Но UI Automator имеет связь с AccessibilityManagerService. Такое подключение дает возможность прослушивать все события доступности в системе и ждать спокойного состояния, когда нет никаких действий. Спокойное состояние приводит к детерминированному поведению системы и снижает вероятность нестабильности. <br>
Все это подтолкнуло авторов UI Automator к внедрению следующего алгоритма: UI Automator ожидает 500 мс (`waitForIdleTimeout` и `waitForSelectorTimeout` в окне `androidx.test.uiautomator.Configurator`) в течение 10 секунд для **каждого действия**. КАЖДОЕ ДЕЙСТВИЕ.

Возможно, описанное решение сделало UI Automator более стабильным. Но скорость упала, спору нет.

Kautomator — это DSL поверх UI Automator, который предоставляет [механизм перехватчиков](../wiki/03_Kaspresso_configurator.md#kaspresso-interceptors-based-on-kakaokautomator-interceptors). Kaspresso предлагает большой набор перехватчиков по умолчанию, что устраняет любые потенциальные нестабильные действия. Итак, Kaspresso + Kautomator помогает UI Automator бороться с ненадёжностью.

Через какое-то время мы подумали, зачем нам сохранять искусственные таймауты внутри UI Automator, в то время как Kaspresso + Kautomator делает ту же работу. Взгляните на пример измерения:
```kotlin
@RunWith(AndroidJUnit4::class)
class KautomatorMeasureTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.boost()
    }
) {

    companion object {
        private val RANGE = 0..20
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        before {
            activityTestRule.launchActivity(null)
        }.after { }.run {

    ======> UI Automator:        0 minutes, 1 seconds and 252 millis
    ======> UI Automator boost:  0 minutes, 0 seconds and 310 millis
            step("MainScreen. Click on `measure fragment` button") {
                UiMainScreen {
                    measureButton {
                        isDisplayed()
                        click()
                    }
                }
            }

    ======> UI Automator:        0 minutes, 11 seconds and 725 millis
    ======> UI Automator boost:  0 minutes, 1 seconds and 50 millis
            step("Measure screen. Button_1 clicks comparing") {
                UiMeasureScreen {
                    RANGE.forEach { _ ->
                        button1 {
                            click()
                            hasText(device.targetContext.getString(R.string.measure_fragment_text_button_1).toUpperCase())
                        }
                    }
                }
            }

    ======> UI Automator:        0 minutes, 11 seconds and 789 millis
    ======> UI Automator boost:  0 minutes, 1 seconds and 482 millis
            step("Measure screen. Button_2 clicks and TextView changes comparing") {
                UiMeasureScreen {
                    RANGE.forEach { index ->
                        button2 {
                            click()
                            hasText(device.targetContext.getString(R.string.measure_fragment_text_button_2).toUpperCase())
                        }
                        textView {
                            hasText(
                                "${device.targetContext.getString(R.string.measure_fragment_text_textview)}${index + 1}"
                            )
                        }
                    }
                }
            }

    ======> UI Automator:        0 minutes, 45 seconds and 903 millis
    ======> UI Automator boost:  0 minutes, 2 seconds and 967 millis
            step("Measure fragment. EditText updates comparing") {
                UiMeasureScreen {
                    edit {
                        isDisplayed()
                        hasText(device.targetContext.getString(R.string.measure_fragment_text_edittext))
                        RANGE.forEach { _ ->
                            clearText()
                            typeText("bla-bla-bla")
                            hasText("bla-bla-bla")
                            clearText()
                            typeText("mo-mo-mo")
                            hasText("mo-mo-mo")
                            clearText()
                        }
                    }
                }
            }

    ======> UI Automator:        0 minutes, 10 seconds and 901 millis
    ======> UI Automator boost:  0 minutes, 1 seconds and 23 millis
            step("Measure fragment. Checkbox clicks comparing") {
                UiMeasureScreen {
                    RANGE.forEach { index ->
                        checkBox {
                            if (index % 2 == 0) {
                                setChecked(true)
                                isChecked()
                            } else {
                                setChecked(false)
                                isNotChecked()
                            }
                        }
                    }
                }
            }
        }
}
```
Отлично!

Также бывают случаи, когда UI Automator не может поймать окно 500 мс. Например, когда один элемент обновляется слишком быстро (одно обновление за 100 мс). Просто взгляните на [этот тест](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/idlingwait_tests/WaitForIdleTest.kt). Только `KautomatorWaitForIdleSettings.boost()` позволяет пройти тест.

Как видите, мы добавили в конфигуратор Kaspresso специальное свойство `kautomatorWaitForIdleSettings`. По умолчанию это свойство **не** повышает производительность. Почему? Потому что:
1. У вас могут быть тесты, в которых вы напрямую используете UI Automator. Но указанные таймауты являются глобальными параметрами. Сброс этих тайм-аутов может привести к неопределенному состоянию.
2. Мы хотим потратить время на сбор данных со всего мира, а затем проанализировать потенциальные проблемы наших решений (но мы считаем, что это стабильное и блестящее решение).

Еще одно важное замечание касается конфигурации `kaspressoBuilder = Kaspresso.Builder.simple`. Эта конфигурация быстрее, чем `advanced`, из-за отсутствия перехватчика скриншотов на каждом шаге. При необходимости добавьте их вручную.

В любом случае, это небольшое изменение для разработчика, но большой шаг для всех нас =)
