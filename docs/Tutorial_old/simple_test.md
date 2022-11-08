# Ваш первый тест на Kaspresso

<br> Прежде чем приступать к написанию теста, давайте поближе познакомимся с функционалом, который мы будем покрывать автотестами. 

<br> Переключаемся на ветку `issue-372/update_tutorial`. В ней находится исходный код самого приложения без тестов. В этом и последующих уроках будет приведена пошаговая инструкция в формате code labs по написанию автотестов. Итоговый результат доступен в ветке `tutorial_results`.

<br> Открываем выбор конфигурации (1) и выбираем tutorial (2):

<img src="../images/simple_test/Select_tutorial.png" alt="Select tutorial"/>

<br> Проверяем, что выбран нужный девайс (1) и запускаем приложение (2):

<img src="../images/simple_test/Launch_tutorial.png" alt="Launch tutorial"/>

<br> После успешного запуска приложения мы видим основной экран приложения Tutorial.

<img src="../images/simple_test/Tutorial_main.png" alt="Tutorial main" width="200"/>

<br> Нажимаем на кнопку с текстом "Simple test" и видим следующий экран:

<img src="../images/simple_test/First_tutorial_screen.png" alt="Page object example" width="200"/>

<br> Экран состоит из:
<br> 1. *Заголовка `TextView`*
<br> 2. *Поля ввода `EditText`*
<br> 3. *Кнопки `Button`*

!!! info
    Полный список виджетов в android с подробной информацией можно найти [здесь](https://developer.android.com/reference/android/widget/package-summary)

<br> При нажатии на кнопку текст в заголовке меняется на введенный в поле ввода.

<br> Чтобы покрыть приложение тестами Kaspresso, необходимо начать с подключения Kaspresso в зависимостях проекта.

## Подключаем Kaspresso к проекту

<br> 1. Переключаем отображение файлов проекта как Project (1) и подключаем в файл `build.gradle` проекта репозиторий `mavenCentral` (2):
<img src="../images/simple_test/Project_build_gradle.png" alt="Project build gradle"/>

```groovy
allprojects {
    repositories {
        mavenCentral()
    }
}
```

<br> 2. Переключаем отображение файлов проекта как Project (1) и добавляем зависимость в файл `build.gradle` модуля `Tutorial`:

<img src="../images/simple_test/Tutorial_build_gradle.png" alt="Tutorial build gradle"/>


```groovy
dependencies {
    androidTestImplementation 'com.kaspersky.android-components:kaspresso:<latest_version>'
}
```

## Написание теста начнем с создания Page object для текущего экрана.
<br> Про паттерн PageObject в Kaspresso можно прочитать в [документации](https://kasperskylab.github.io/Kaspresso/Wiki/Page_object_in_Kaspresso/).<br/>
<br> Попасть на SimpleActivity можно из MainActivity. Поэтому, чтобы протестировать SimpleActivity, нам нужно описать два PageObject-а. В папке `androidTest` создаем папку `screen` и кладем туда объекты `MainScreen` и `SimpleScreen`:

```kotlin
object MainScreen: KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
    
    val simpleActivityButton = KButton { withId(R.id.simple_activity_btn) }
}
```

<br> Основной экран состоит из нескольких UI-элементов, но нас в рамках первого теста интересует только одна кнопка - кнопка перехода на экран SimpleActivty.

```kotlin
object SimpleScreen : KScreen<SimpleScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
    
    val title = KTextView { withId(R.id.simple_title) }
    val button = KButton { withId(R.id.change_title_btn) }
    val input = KEditText { withId(R.id.input_text) }
}
```
<br> В этом объекте мы описываем элементы интерфейса, с которым будет взаимодействовать тест. Здесь стоит обратить внимание на то, что мы один раз кладем matcher-ы в конструктор `View` ({ withId(R.id...)}). В самом тесте мы сможем обращаться к MainScreen и SimpleScreen и их элементам напрямую.
<br> В наследниках KScreen рекомендуется корректно переопределять `layoutId` и `ViewClass`, но это не обязательно. В следующих тестах мы вернемся к обсуждению этих полей, но в этом тесте для упрощения мы оставим инициализацию `null`. 
## Приступаем к коду самого теста

<br> В папке `androidTest` создаем класс `SimpleTest`:

```kotlin
class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {

    }
}
```

<br> Тест `SimpleActivityTest` можно запустить. Информацию по запуску тестов в Android Studio можно найти в  [предыдущем уроке](https://kasperskylab.github.io/Kaspresso/Tutorial/Running_the_first_test/)
<br> Этот тест осуществит запуск указанной activity `MainActivity` перед запуском теста и закроет после прогона теста. За это отвечает:

```kotlin
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()
```

<br> Подробнее про `activityScenarioRule` можно почитать [здесь](https://developer.android.com/reference/androidx/test/ext/junit/rules/ActivityScenarioRule)

<br> SimpleActivityTest унаследован от TestCase. Это не единственный способ создать тестовый класс. В случае, когда невозможно отнаследоваться от TestCase (в Java и Kotlin запрещено множественное наследование), можно использовать TestCaseRule. 

```kotlin
 @get:Rule
 val testCaseRule = TestCaseRule(javaClass.simpleName)
```

<br>В этом случае тело тестового метода должно начинаться с обращения к этому инстансу:

```kotlin
@Test
fun test() =
  testCaseRule.run {
      ...
  }
```

<br> Расширим код теста `test()` в `SimpleActivityTest` проверками, что кнопка перехода на экран SimpleActivity отображается и она кликабельна.

```kotlin
class SimpleActivityTest: TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                click()
            }
        }
    }
}
```

<br> Расширим код теста `test()` в `SimpleActivityTest` проверкой, что заголовок отображается и содержит ожидаемый текст.

```kotlin
class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SimpleActivity>()

    @Test
    fun test() {
        SimpleScreen {
            title {
                isVisible()
                hasText(R.string.simple_activity_default_title)
            }
        }
    }
}
```
<br> Помимо матчеров, которые используются для поиска нужных элементов на экране, можно использовать реализацию классов-наследников BaseActions и BaseAssertions. Первый определяет набор действий, которые могут быть выполнены над элементом, а второй - набор проверок. 
<br> Более подробно про action и assertion можно почитать в [документации](https://kasperskylab.github.io/Kaspresso/Wiki/Matchers_actions_assertions/)
<br> Давайте запустим тест и проверим, что написанный код запускает приложение, открывает главный экран, успешно проверяет видимость кнопки и осуществляет переход на экран SimpleActivity по клику на эту кнопку.
<br> Вернемся к тесту. После перехода на экран SimpleActivity работа с PageObject-ом MainScreen завершена и пора использовать SimpleActivityScreen:

```kotlin
class SimpleActivityTest: TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                click()
            }
        }
        SimpleActivityScreen {
            simpleTitle.isVisible()
            changeTitleButton.isClickable()
            simpleTitle.hasText(R.string.simple_activity_default_title)
            inputText.replaceText("new title")
            changeTitleButton.click()
            simpleTitle.hasText("new title")
        }
    }
}
```
<br>Рассмотрим код этого теста. После перехода на экран SimpleActivityScreen мы проверили видимость заголовка (simpleTitle.isVisible()), кликабельность кнопки смены текста в заголовке (changeTitleButton.isClickable()) и отображения в заголовке ожидаемого текста по умолчанию(simpleTitle.hasText(R.string.simple_activity_default_title)).
<br>Затем мы в поле ввода помещаем сообщение "new title" (inputText.replaceText("new title")), нажимаем на кнопку смены заголовка и убеждаемся, что текст поменялся на "new title" (simpleTitle.hasText("new title"))
