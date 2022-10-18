# Ваш первый тест на Kaspresso

В прошлом уроке мы запустили первый автотест, который уже был написан за нас. Давайте посмотрим, что там внутри:

Открываем выбор конфигурации (1) и выбираем tutorial (2):
<img src="../images/simple_test/Select_tutorial.png" alt="Select tutorial" width="200"/>

Проверяем, что выбран нужный девайс (1) и запускаем приложение (2):
<img src="../images/simple_test/Launch_tutorial.png" alt="Launch tutorial" width="200"/>

После успешного запуска приложения мы видим основной экран приложения Tutorial. 
<img src="../images/simple_test/Tutorial_main.png" alt="Tutorial main" width="200"/>

Нажимаем на кнопку с текстом "Simple test":
<img src="../images/simple_test/First_tutorial_screen.png" alt="Page object example" width="200"/>

<br> Экран состоит из:
<br> 1. *Заголовка `TextView`*
<br> 2. *Поля ввода `EditText`*
<br> 3. *Кнопки `Button`*

<br> При нажатии на кнопку текст в заголовке меняется на введенный в поле ввода.

Чтобы покрыть приложение тестами Kaspresso, необходимо начать с подключения Kaspresso в зависимостях проекта.

## Подключаем Kaspresso к проекту

1. Подключаем в файл `build.gradle` проекта репозиторий `mavenCentral`

```groovy
allprojects {
    repositories {
        mavenCentral()
    }
}
```

2. Переключаем отображение файлов проекта как Project (1) и добавляем зависимость в файл `build.gradle` главного модуля:

<img src="../images/simple_test/Tutorial_build_gradle.png" alt="Tutorial build gradle" width="200"/>


```groovy
dependencies {
    androidTestImplementation 'com.kaspersky.android-components:kaspresso:<latest_version>'
}
```

<br> Если Вы используете Android Support, мы рекомендуем мигрировать на AndroidX. Последняя поддерживаемая версия для Android Supprot:

```groovy
dependencies {
    androidTestImplementation 'com.kaspersky.android-components:kaspresso:1.0.1-support'
}
```

## Написание теста начнем с создания Page object для текущего экрана.
<br/> Про паттерн PageObject в Kaspresso можно прочитать в [документации](https://kasperskylab.github.io/Kaspresso/Wiki/Page%20object%20in%20Kaspresso/).<br/>
<br/> В папке `androidTest` создаем создаем папку screen и кладем туда объект `SimpleScreen`

```kotlin
object SimpleScreen : KScreen<SimpleScreen>() {

    override val layoutId: Int = R.layout.activity_simple
    override val viewClass: Class<*> = SimpleActivity::class.java

    val title = KButton { withId(R.id.simple_title) }
    val button = KButton { withId(R.id.change_title_btn) }
    val input = KEditText { withId(R.id.input_text) }
}
```
<br> В этом объекте мы описываем элементы интерфейса, с которым будет взаимодействовать тест. Здесь стоит обратить внимание на то, что мы один раз кладем matcher-ы в конструктор `View` ({ withId(R.id...)}). В самом тесте мы сможем обращаться к SimpleScreen и его элементам напрямую.
<br> Для поиска нужного `View` можно использовать сразу несколько matcher-ов. Например, если у какого-то элемента нет `id`, мы можем найти его с помощью нескольких matcher-ов. 
<br> В объекте SimpleScreen переопределены `layoutId` и `ViewClass`. Если их корректно не проинициализировать (например, присвоить `null`), то на работоспособность теста это влиять не будет. Но мы рекомендуем не игнорировать их и корректно инициализировать. Это поможет при разработке и дальнейшей поддержки понимать, с каким `ViewClass` и `layoutId` связан конкретный Screen.

## Приступаем с коду самого теста
```kotlin
class SimpleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SimpleActivity>()

    @Test
    fun test() {
        SimpleScreen {
            title {
                isVisible()
                hasText(R.string.simple_activity_default_title)
                hasTextColor(R.color.black)
            }

            button {
                isVisible()
                withText(R.string.simple_activity_change_title_button)
                isClickable()
            }
            input {
                isVisible()
                hasHint(R.string.simple_activity_input_hint)
                hasEmptyText()

                typeText("Kaspresso")
                hasText("Kaspresso")
            }
            closeSoftKeyboard()
            button {
                click()
            }
            title {
                hasText("Kaspresso")
            }
        }
    }
}
```

<br> SimpleTest унаследован от TestCase. Это не единственный способ создать тестовый класс. В случае, когда невозможно отнаследоваться от TestCase, можно использовать TestCaseRule. 
```kotlin
 @get:Rule
 val testCaseRule = TestCaseRule(javaClass.simpleName)
```
В этом случае тело тестового метода должно начинаться с обращения к этому инстансу:
```kotlin
@Test
fun test() =
  testCaseRule.run {
      ...  
  }
```

<br> Рассмотрим сам тест. Благодаря реализации паттерна Page object и Kotlin DSL код теста становится простым и понятным: сперва мы проверили корректность отображения нужных элементов, затем ввели текст в поле ввода, нажали кнопку и проверили, что заголовок изменился. Однако, код любого теста - это реализация определенных тест-кейсов. Сам же тест-кейс - это некий сценарий (последовательность шагов), написанный на человеческом языке тестировщиком. Этот набор шагов может со временем меняться, поэтому спустя какое-то время возникнет потребность в редактировании теста. Помимо этого, тест может не всегда проходить успешно. Чтобы тест было легко поддерживать и он оставался понятным спустя долгое время, он должен быть разделен на шаги, идентичные указанным в тест-кейсах. Комментарии будут не самым лучшим решением, так как в логах не будет понятно, на каком шаге упал тест. Для этого можно воспользоваться специальными методами Kaspresso.

```kotlin
class SimpleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SimpleActivity>()

    @Test
    fun test() =
        before {

        }.after {

        }.run {
            step("Open Simple Screen") {
                SimpleScreen {
                    title {
                        isVisible()
                        hasText(R.string.simple_activity_default_title)
                        hasTextColor(R.color.black)
                    }

                    button {
                        isVisible()
                        withText(R.string.simple_activity_change_title_button)
                        isClickable()
                    }
                    input {
                        isVisible()
                        hasHint(R.string.simple_activity_input_hint)
                        hasEmptyText()
                    }
                }
            }

            step("Type \" Kaspresso \"") {
                SimpleScreen {
                    input.typeText("Kaspresso")
                    closeSoftKeyboard()
                    button.click()
                }
            }

            step("Check title content") {
                SimpleScreen {
                    title.hasText("Kaspresso")
                }
            }
        }
}
```
<br> У каждого теста могут быть свои предусловия (определенные состояния девайса), а после его выполнения состояние девайса должно быть возвращено в исходное. Секции before и after нужны для настройки состояния до и после прогона теста. Например, это может быть включение Bluetooth. До выполнения теста необходимо включить его, а после - выключить. Более подробно эти секции описаны в следующих примерах. 
<br> Step представляет собой абстракцию, которая реагирует на все события шага (например: шаг стартует, шаг завершается успехом или неудачей). Внутри одной секции step может быть объявлены другие секции step. По умолчанию, абстракция step добавляет логирование и скриншотинг (возможность кастомизаций набора действий описаны в следующих примерах). Таким образом, после прогона теста можно будет посмотреть подробные записи логирования, которые будут полезны для дальнейшей поддержки тестов в рабочем состоянии и устранении проблем. Скришоты будут делаться по окончании шага (по одному на каждую step-секцию) и перед завершением теста в случае возникновения ошибки. Данное поведение основывается на философии Kaspresso о возможном изменении состояния после каждого шага. Если необходимо больше скриншотов, то их можно сделать с помощью вызова метода `device.screenshots.take("Additional_screenshot")`. Доступный функционал `device` описан в следующих примерах.

