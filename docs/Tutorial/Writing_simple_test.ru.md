# Ваш первый тест на Kaspresso

## Переключаемся на нужную ветку в GIT

В Android Studio вы можете переключаться между ветками и, таким образом, видеть разные версии проекта. Изначально, после загрузки Kaspresso вы будете находиться в главной ветке - `master`.

<img src="../images/simple_test/master_branch.png" alt="Master branch"/>

В этой ветке находится исходный код приложения, которое мы будем покрывать тестами. В текущем и последующих уроках будет приведена пошаговая инструкция в формате codelabs по написанию автотестов. Итоговый результат со всеми написанными тестами доступен в ветке `TECH-tutorial-results`, вы в любой момент сможете переключиться на нее и посмотреть решение.

Для этого кликните на название ветки, в которой находитесь, и в поиске введите название интересующей вас ветки.

<img src="../images/simple_test/switch_to_results.png" alt="Switch to results"/>

## Ручное тестирование

Прежде чем приступать к написанию теста, давайте поближе познакомимся с функционалом, который мы будем покрывать автотестами. Для этого переключаемся на master-ветку.

Открываем выбор конфигурации (1) и выбираем tutorial (2):

<img src="../images/simple_test/Select_tutorial.png" alt="Select tutorial"/>

Проверяем, что выбран нужный девайс (1) и запускаем приложение (2):

<img src="../images/simple_test/Launch_tutorial.png" alt="Launch tutorial"/>

После успешного запуска приложения мы видим основной экран приложения Tutorial.

<img src="../images/simple_test/Tutorial_main.png" alt="Tutorial main" width="200"/>

Нажимаем на кнопку с текстом "Simple test" и видим следующий экран:

<img src="../images/simple_test/First_tutorial_screen.png" alt="Page object example" width="200"/>

Экран состоит из:
1. *Заголовка `TextView`*
2. *Поля ввода `EditText`*
3. *Кнопки `Button`*

!!! info
    Полный список виджетов в Android с подробной информацией можно найти [здесь](https://developer.android.com/reference/android/widget/package-summary)

При нажатии на кнопку текст в заголовке меняется на введенный в поле ввода.

## Автоматическое тестирование

Мы вручную проверили, что результат работы приложения соответствует ожиданиям:

<ol>
    <li>На главном экране есть кнопка перехода на экран SimpleTest (остальные элементы этого экрана нас сейчас не интересуют)</li>
    <li>Эта кнопка видима</li>
    <li>На нее можно кликнуть</li>
    <li>При клике на нее мы переходим на экран SimpleTest</li>
    <li>На экране SimpleTest есть элементы - заголовок, поле ввода и кнопка</li>
    <li>Все эти элементы видимы</li>
    <li>Заголовок содержит текст по умолчанию</li>
    <li>Если ввести какой-то текст в поле ввода и кликнуть на кнопку, то текст в заголовке меняется на введенный</li>
</ol>

Теперь нам нужно все те же проверки написать в коде, чтобы они осуществлялись в автоматическом режиме. 

Чтобы покрыть приложение тестами Kaspresso, необходимо начать с подключения библиотеки Kaspresso в зависимостях проекта.

## Подключаем Kaspresso к проекту

Переключаем отображение файлов проекта как Project (1) и добавляем зависимость в существующую секцию `dependencies` в файле `build.gradle` модуля `Tutorial`:

<img src="../images/simple_test/Tutorial_build_gradle.png" alt="Tutorial build gradle"/>

```groovy
dependencies {
    androidTestImplementation("com.kaspersky.android-components:kaspresso:1.5.1")
    androidTestUtil("androidx.test:orchestrator:1.4.2")
}
```

## Написание теста начнем с создания Page object для текущего экрана.

Можем писать код нашего теста. Чтобы это сделать, необходимо для каждого экрана, который участвует в тесте, создать модель (класс), внутри которого объявить все элементы интерфейса (кнопки, текстовые поля и т.д.), из которых состоит экран, с которыми будет взаимодействовать тест. Такой подход называется `Page Object` и подробнее о нем вы можете почитать в [документации](https://kasperskylab.github.io/Kaspresso/Wiki/Page_object_in_Kaspresso/).

В первых четырех пунктах теста мы взаимодействуем с главным экраном, поэтому первым делом необходимо создать Page Object главного экрана. 
Работать мы будем в папке `androidTest` в модуле `tutorial`. Если у вас этой папки нет, то ее необходимо создать, для этого кликаем правой кнопкой мыши на папку `src` и выбираем пункт `New` -> `Directory`.

<img src="../images/simple_test/create_directory.png" alt="Create directory"/>

Выбираем пункт `androidTest/kotlin`:

<img src="../images/simple_test/name_android_test.png" alt="Name directory androidTest"/>

Внутри папки kotlin давайте создадим отдельный пакет (package), в котором будем хранить все Page Object-ы:

<img src="../images/simple_test/create_package.png" alt="Create package"/>

Создание отдельного пакета на функциональность не влияет, мы это делаем просто для удобства, чтобы все модели экранов лежали в одном месте. Вы можете дать пакету любое имя (за некоторым исключением), но обычно в тестах используют такие же названия, как в самом приложении. Мы можем перейти в файл MainActivity, где сверху будет указано имя пакета. 

<img src="../images/simple_test/package_name_main_activity.png" alt="MainActivity Package name"/>

Копируем это имя и вставляем в название пакета. Конкретно в этом пакете мы будем хранить только модели экранов (Page Object-ы), поэтому в конце давайте добавим `.screen`.

<img src="../images/simple_test/package_name_screen.png" alt="Screen Package name"/>

Когда мы будем добавлять другие классы в папку с тестами, то будем класть их уже в другие пакеты, но при этом первая часть их названия будет такой же `com.kaspersky.kaspresso.tutorial`.

Теперь в созданном пакете мы добавляем модель экрана (класс):

<img src="../images/simple_test/create_class.png" alt="Create class"/>

Выбираем тип Object и именуем MainScreen.

<img src="../images/simple_test/create_main_screen.png" alt="Create MainScreen"/>

MainScreen представляет собой модель главного экрана. Для того чтобы эту модель можно было использовать в автотестах, необходимо унаследоваться от класса KScreen и в угловых скобках указать название этого класса.

!!! info
    Указание типа в угловых скобках в Java и Kotlin называется Generics. Подробнее об этом вы можете почитать в [документации по Java](https://docs.oracle.com/javase/tutorial/java/generics/types.html) и [Kotlin](https://kotlinlang.org/docs/generics.html)

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {
}
```
У нас возникает ошибка - класс KScreen содержит два элемента, которые нужно переопределить при наследовании. Для того чтобы сделать это быстро, в Android Studio можно нажать комбинацию клавиш `ctrl + i` и выбрать элементы, которые мы хотим переопределить. 

<img src="../images/simple_test/override.png" alt="Override methods"/>

Удерживая `ctrl`, выбираем все пункты и нажимаем `OK`. 

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int?
        get() = TODO("Not yet implemented")
    override val viewClass: Class<*>?
        get() = TODO("Not yet implemented")
}
```

В файле появились новые строчки кода. Вместо `TODO` нужно написать корректную реализацию - id макета (`layoutId`), который установлен на экране, и название класса (`viewClass`). Это необходимо для связывания теста с конкретным файлом верстки и классом activity. Такое связывание сделает дальнейшую поддержку и доработку теста более удобной, но пока перед нами стоит задача написать первый тест, поэтому оставим значение `null`.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
}
```

Теперь внутри класса KScreen мы будем объявлять все элементы пользовательского интерфейса, с которыми будет взаимодействовать тест. В нашем случае на главном экране нас интересует только кнопка `SimpleTest`. 

<img src="../images/simple_test/simple_test_button.png" alt="Override methods" width="300"/>

Чтобы тест мог с ней взаимодействовать, нужно знать id, по которому эту кнопку можно найти на экране. Эти идентификаторы присваивает разработчик при написании приложения. 

Чтобы узнать, какой id был присвоен какому-то элементу интерфейса, можно воспользоваться инструментом, встроенным в Android Studio - `LayoutInspector`.

<ol>
    <li>Запускаем приложение</li>
    <li>В правом нижнем углу Android Studio выбираем пункт Layout Inspector <img src="../images/simple_test/bottom_layout_inspector.png" alt="Find bottom layout inspector"/></li>
    <li>Ждем пока загрузится экран <img src="../images/simple_test/loaded_inspector.png" alt="Layout inspector loaded"/></li>
    <li>Если экран не загрузился, то проверьте, что у вас выбран нужный процесс <img src="../images/simple_test/choose_process.png" alt="Choose process"/></li>
</ol>
Ищем пункт id - это тот идентификатор, который нас интересует. 

<img src="../images/simple_test/button_id_search.png" alt="Search for button id"/>

Также важно понимать, с каким элементом UI мы работаем. Для этого можно перейти в макет, где элемент был объявлен, и посмотреть всю информацию о нем.

<img src="../images/simple_test/find_layout.png" alt="Find layout"/>

В данном случае это элемент Button c вот таким id: `simple_activity_btn`

<img src="../images/simple_test/button_in_layout.png" alt="Find button in layout"/>

Можем добавлять эту кнопку в `MainScreen`, обычно название переменной дают такое же, как id, но без нижних подчеркиваний, каждое следующее слово с заглавной буквы (это называется camelCase)

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = 
}
```
Переменной simpleActivityButton нужно присвоить значение, она представляет собой кнопку, которую можно протестировать - за это отвечает class KButton. Вот так будет выглядеть установка значения в эту переменную, сейчас мы подробно разберем, что делает этот код.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = KButton { withId(R.id.simple_activity_btn) }
}
```

Во-первых, давайте перейдем в определение KButton и посмотрим, что это. Для этого, удерживая `ctrl`, кликаем на название класса KButton левой кнопкой мыши.

<img src="../images/simple_test/show_kbutton_source.png" alt="Find source of KButton"/>

Видим, что это класс, который наследуется от KBaseView и реализует интерфейс TextViewAssertions. Можем перейти в определение KBaseView и посмотреть всех наследников этого класса, их тут достаточно много. 

<img src="../images/simple_test/kbaseview_children.png" alt="Find kbaseview children"/>

Зачем они все нужны?

Дело в том, что каждый элемент пользовательского интерфейса можно протестировать по-разному. К примеру, для TextView мы можем проверить, какой текст сейчас в него установлен, можем установить новый текст, а ProgressBar не содержит никакой текст, и осуществлять проверку на то, какой текст в него установлен, нет смысла. 

Поэтому в зависимости от того, какой элемент интерфейса мы тестируем, нужно выбирать правильную реализацию KBaseView. Сейчас мы тестируем кнопку, поэтому выбрали KButton. На следующем экране мы будем тестировать заголовок (TextView) и поле ввода (EditText) и выберем соответствующие реализации KBaseView.

<img src="../images/simple_test/needed_children.png" alt="Show children which we need"/>

Идем дальше, эту кнопку тест должен найти на экране по какому-то критерию. В данном случае мы осуществим поиск элемента по id, поэтому используем матчер `withId`, куда в качестве параметра передаем идентификатор кнопки, который мы нашли благодаря `Layout Inpector`.

Для того чтобы указать этот id, мы использовали синтаксис R.id..., где `R` - это класс со всеми ресурсами приложения. Благодаря нему можно находить id элементов интерфейса, строк, которые есть в проекте, картинок и т.д. При вводе названия этого класса Android Studio должна импортировать его автоматически, но иногда этого не происходит, тогда нужно ввести этот импорт вручную.

```kotlin
import com.kaspersky.kaspresso.tutorial.R
```

Все, теперь у нас есть модель главного экрана и эта модель содержит кнопку, которую можно тестировать. Можем приступать к написанию самого теста.

## Добавляем SimpleActivityTest

В папке `androidTest` -> `kotlin`, в созданном нами пакете добавляем класс `SimpleActivityTest`.

<img src="../images/simple_test/create_test_1.png" alt="Creating Test First part"/>

<img src="../images/simple_test/create_test_2.png" alt="Creating Test Second part"/>

Новый класс был размещен в пакете `screen`, но мы хотели бы, чтобы в нем лежали только модели экранов, поэтому созданный тест мы переместим в корень пакета `com.kaspersky.kaspresso.tutorial`. Для того, чтобы это сделать, кликаем на название класса правой кнопкой мыши и выбираем `Refactor` -> `Move`

<img src="../images/simple_test/move_to_package.png" alt="Move to another package"/>

И убираем из названия пакета последнюю часть `.screen`.

<img src="../images/simple_test/change_package.png" alt="Change package name"/>

Класс тестов должен быть унаследован от класса `TestCase`. Обратите внимание на импорты, класс TestCase должен быть импортирован из пакета `import com.kaspersky.kaspresso.testcases.api.testcase.TestCase`.

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

class SimpleActivityTest: TestCase() {
}
```

И добавляем метод `test()`, в котором будем проверять работу приложения. У него может быть любое имя, необязательно "test", но важно, чтобы он был помечен аннотацией `@Test` (`import org.junit.Test`).

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class SimpleActivityTest : TestCase() {

    @Test
    fun test() {

    }
}
```

Тест `SimpleActivityTest` можно запустить. Информацию по запуску тестов в Android Studio можно найти в  [предыдущем уроке](https://kasperskylab.github.io/Kaspresso/Tutorial/Running_the_first_test/).

<img src="../images/simple_test/success_1.png" alt="Success passed test"/>

Сейчас этот тест ничего не делает, поэтому и завершается успешно. Давайте добавим ему логики и протестируем MainScreen. 

```kotlin
package com.kaspersky.kaspresso.tutorial

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import com.kaspersky.kaspresso.tutorial.screen.MainScreen

class SimpleActivityTest : TestCase() {

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                isClickable()
            }
        }
    }
}
```

Внутри метода test мы получаем объект MainScreen, открываем фигурные скобки и обращаемся к кнопке, которую будем тестировать, дальше открываем еще раз фигурные скобки и тут пишем все проверки. Сейчас, благодаря методам `isVisible()` и `isClickable()` мы проверяем, что кнопка видима и по ней можно кликнуть. Запускаем и наш тест падает.

<img src="../images/simple_test/test_failed_1.png" alt="Feailed test"/>

Дело в том, что Page Object `MainScreen` относится к `MainActivity` (именно эту активити видит пользователь, когда запускает приложение) и, для того чтобы элементы отобразились на экране, эту активити нужно запустить перед выполнением теста. Для того, чтобы перед тестом была запущена какая-то активити, ножно добавить следующие строки:

```kotlin
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()
```

Этот тест осуществит запуск указанной activity `MainActivity` перед запуском теста и закроет после прогона теста.

Подробнее про `activityScenarioRule` можно почитать [здесь](https://developer.android.com/reference/androidx/test/ext/junit/rules/ActivityScenarioRule).

Тогда весь код теста будет выглядеть следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import org.junit.Rule
import org.junit.Test
import com.kaspersky.kaspresso.tutorial.screen.MainScreen

class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                isClickable()
            }
        }
    }
}
```

Запускаем. Все отлично, у нас тест проходит успешно, и вы можете увидеть на девайсе, что во время теста открывается нужная нам активити и закрывается после прогона.

<img src="../images/simple_test/sucess_2.png" alt="Success test"/>

Хорошей практикой во время написания тестов является проверка, что тест не только успешно выполняется, но и падает, если условие не выполняется. Так вы исключите ситуацию, когда тесты "зеленые", но на самом деле из-за какой-то ошибки в коде проверки вообще не выполнялись. Давайте это сделаем, проверим, что кнопка содержит некорректный текст. 

```kotlin
class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                isClickable()
                containsText("Incorrect text")
            }
        }
    }
}
```

Тест падает, меняем текст на корректный.

```kotlin
class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                isClickable()
                containsText("Simple test")
            }
        }
    }
}
```

Тест проходит успешно. 

Теперь нам нужно протестировать `SimpleActivity`. Делаем по аналогии с `MainScreen` - создаем Page Object.

```kotlin
object SimpleActivityScreen : KScreen<SimpleActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
}
```

Ищем id элементов через `Layout Inspector`:

<img src="../images/simple_test/title_inspect.png" alt="Title id in inspector"/>

<img src="../images/simple_test/input_inspect.png" alt="Input id in inspector"/>

<img src="../images/simple_test/button_inspect.png" alt="Button id in inspector"/>

Не забываем указывать корректные View элементы, для заголовка - KTextView, для поля ввода - KEditText, для кнопки - KButton

```kotlin
object SimpleActivityScreen : KScreen<SimpleActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleTitle = KTextView { withId(R.id.simple_title) }
    val inputText = KEditText { withId(R.id.input_text) }
    val changeTitleButton = KButton { withId(R.id.change_title_btn) }
}
```

И теперь можем тестировать этот экран. Для того, чтобы на него перейти, на главном экране нужно кликнуть на кнопку, вызываем `click()`.

Добавляем проверки для этого экрана:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import org.junit.Rule
import org.junit.Test
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.SimpleActivityScreen

class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                isClickable()
                click()
            }
        }
        SimpleActivityScreen {
            simpleTitle.isVisible()
            changeTitleButton.isClickable()
            simpleTitle.hasText("Default title")
            inputText.replaceText("new title")
            changeTitleButton.click()
            simpleTitle.hasText("new title")

        }
    }
}
```

Наш первый тест практически готов. Единственное изменение, которое стоит сделать - тут мы используем захардкоженный текст "Default title". При этом тест успешно проходит, но если вдруг приложение будет локализовано на разные языки, то при запуске теста с английской локалью тест может проходить успешно, а если запустим на устройстве с российской локалью, то тест упадет. 

Поэтому вместо того, чтобы хардкодить строку, мы возьмем ее из ресурсов приложения. В макете активити мы можем посмотреть, какая строка использовалась в этом TextView.

<img src="../images/simple_test/find_string_in_layout.png" alt="Find string in layout"/>

Переходим в строковые ресурсы (файл `values/strings.xml`) и копируем id строки.

<img src="../images/simple_test/string_in_values.png" alt="Find string in values folder"/>

Теперь в методе hasText вместо использования строки "Default title" используем ее id `R.string.simple_activity_default_title`.
Не забываем импортировать класс ресурсов R `import com.kaspersky.kaspresso.tutorial.R`.

Финальный код теста выглядит вот так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R
import org.junit.Rule
import org.junit.Test
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.SimpleActivityScreen

class SimpleActivityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() {
        MainScreen {
            simpleActivityButton {
                isVisible()
                isClickable()
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

# Итог
В этом уроке мы написали наш первый тест на Kaspresso. На практике познакомились с подходом PageObject. Научились получать идентификаторы элементов интерфейса при помощи `Layout inspector`.

<br>


