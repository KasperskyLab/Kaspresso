# Ваш первый тест на Kaspresso

<br> Прежде чем приступать к написанию теста, давайте поближе познакомимся с функционалом, который мы будем покрывать автотестами. 

<br> Переключаемся на ветку `issue-372/update_tutorial`. В ней находится исходный код самого приложения без тестов. В этом и последующих уроках будет приведена пошаговая инструкция в формате codelabs по написанию автотестов. Итоговый результат доступен в ветке `tutorial_results`.

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
<br> Переключаем отображение файлов проекта как Project (1) и добавляем зависимость в существующую секцию `dependencies` в файле `build.gradle` модуля `Tutorial`:
<img src="../images/simple_test/Tutorial_build_gradle.png" alt="Tutorial build gradle"/>

```groovy
dependencies {
    androidTestImplementation("com.kaspersky.android-components:kaspresso:1.4.3")
}
```

## Написание теста начнем с создания Page object для текущего экрана.
<br> Мы вручную проверили, что результат работы приложения соответствует ожиданиям:
1. На главном экране есть кнопка перехода на экран SimpleTest (остальные элементы этого экрана нас сейчас не интересуют)
2. Эта кнопка видима
3. На нее можно кликнуть
4. При клике на нее мы переходим на экран SimpleTest
5. На экране SimpleTest есть элементы - заголовок, поле ввода и кнопка
6. Все эти элементы видимы
7. Заголовок содержит тест по умолчанию
8. Если ввести какой-то текст в поле ввода и кликнуть на кнопку, то текст в заголовке меняется на введенный

<br>Теперь нам нужно все те же проверки написать в коде, чтобы они осуществлялись в автоматическом режиме. 
<br>Чтобы это сделать, необходимо для каждого экрана, который участвует в тесте, создать модель (класс), внутри которого объявить все важные элементы интерфейса (кнопки, текстовые поля и т.д.), из которых состоит экран и с которыми будет взаимодействовать тест. Такой подход называется `Page Object` и подробнее о нем вы можете почитать в [документации](https://kasperskylab.github.io/Kaspresso/Wiki/Page_object_in_Kaspresso/)

<br>В первых четырех пунктах теста мы взаимодействуем с главным экраном, поэтому первым делом необходимо создать Page Object главного экрана. 
<br>Работать мы будем в папке androidTest в модуле tutorial. Если у вас этой папки нет, то ее необходимо создать, для этого кликаем правой кнопкой мыши на папку src и выбираем пункт New -> Directory 

<img src="../images/simple_test/create_directory.png" alt="Create directory" width="200"/>

<br>Выбираем пункт `androidTest/kotlin`

<img src="../images/simple_test/name_android_test.png" alt="Name directory androidTest" width="200"/>

<br>Внутри папки kotlin давайте создадим отдельный пакет (package), в котором будем хранить все Page Object-ы. 

<img src="../images/simple_test/create_package.png" alt="Create package" width="200"/>

<br>Создание отдельного пакета на функциональность не влияет, мы это делаем просто для удобства, чтобы все модели экранов лежали в одном месте. Даем имя пакету screen и внутри создаем новый класс, 

<img src="../images/simple_test/create_class.png" alt="Create class" width="200"/>

<br>Выбираем тип Object и именуем MainScreen.

<img src="../images/simple_test/create_main_screen.png" alt="Create MainScreen" width="200"/>

<br>MainScreen представляет собой модель главного экрана. Для того чтобы эту модель можно было использовать в автотестах, необходимо унаследоваться от класса KScreen, и в угловых скобках указать название этого класса.

```kotlin
package screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {
}

```

<br>У нас возникает ошибка - раз мы унаследовались от класса KScreen, то нужно обязательно переопределить два метода. для того чтобы сделать это быстро в Android Studio можно нажать комбинацию клавиш `ctrl + i` и выбрать элементы, которые мы хотим переопределить. 

<img src="../images/simple_test/override.png" alt="Override methods" width="200"/>

<br>Удерживая `ctrl` выбираем все пункты и нажимаем OK. 

```kotlin
package screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int?
        get() = TODO("Not yet implemented")
    override val viewClass: Class<*>?
        get() = TODO("Not yet implemented")
}

```

<br>Здесь вместо TODO нужно написать корректную реализацию - id макета, который установлен на экране и название класса. В будущем мы разберем, для чего это нужно и напишем правильную реализацию, а сейчас просто вернем null. Наш тест будет работать и так.

```kotlin
package screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
}
```

<br>Теперь внутри класса KScreen мы будем объявлять все элементы пользовательского интерфейса, с которыми будет взаимодействовать тест. В нашем случае на главном экране нас интересует только кнопка SimpleTest. 

<img src="../images/simple_test/simple_test_button.png" alt="Override methods" width="200"/>

<br>Чтобы тест мог с ней взаимодйствовать, нужно знать id, по которому эту кнопку можно найти на экране. Эти идентификаторы присваивает разработчик при написании приложения. Чтобы узнать, какой id был присвоен какому-то элементу интерфейса, можно воспользоваться инструментом, встроенным в Android Studio - LayoutInspector.

1. Запускаем приложение
2. В правом нижнем углу Android Studio выбираем пункт Layout Inspector

<img src="../images/simple_test/bottom_layout_inspector.png" alt="Find bottom layout inspector" width="200"/>

3. Ждем пока загрузится экран

<img src="../images/simple_test/loaded_inspector.png" alt="Layout inspector loaded" width="200"/>

4. Если экран не загрузился, то проверьте, что вас выбран нужный процесс

<img src="../images/simple_test/choose_pocess.png" alt="Choose process" width="200"/>

<br>Ищем пункт id - это тот идентификатор, который нас интересует. 

<img src="../images/simple_test/button_id_search.png" alt="Search for button id" width="200"/>

<br>Также важно понимать, с каким элементом UI мы работаем. Для этого можно перейти в макет, где элемент был объявлен и посмотреть всю информацию о нем.

<img src="../images/simple_test/find_layout.png" alt="Find layout" width="200"/>

<br>В данном случае это элемент Button c вот таким id 'simple_activity_btn'

<img src="../images/simple_test/button_in_layout.png" alt="Find button in layout" width="200"/>

<br>Можем добавлять эту кнопку в MainScreen, обычно название переменной дают такое же, как id, но без нижних подчеркиваний, какждое следующее слово с заглавной буквы (это называется camelCase)

```kotlin
package screen

import com.kaspersky.kaspresso.screens.KScreen

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = 
}
```

Пременной simpleActivityButton нужно присвоить значение, она представляет собой кнопку, которую можно протестировать - за это отвечает class KButton. Вот так будет выглядеть установка значения в эту переменную, сейчас мы подробно разберем, что делает этот код.

```kotlin
package screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = KButton { withId(R.id.simple_activity_btn) }
}

```

<br>Во-первых, давайте перейдем в определение KButton и посмотрим, что это. Для этого, удерживая ctrl, кликаем на название класса KButton левой кнопкой мыши.

<img src="../images/simple_test/show_kbutton_source.png" alt="Find source of KButton" width="200"/>

<br>Видим, что это класс, который наследуется от KBaseView и реализует интерфейс TextViewAssertions. Можем перейти в определение KBaseView и посмотреть всех наследников этого класса, их тут достаточно много. 

<img src="../images/simple_test/kbaseview_children.png" alt="Find kbaseview children" width="200"/>

<br>Зачем они все нужны?
<br>Дело в том, что каждый элемент пользовательского интерфейса можно протестировать по разному. К примеру, у TextView мы можем проверить, какой текст сейчас в него установлен, можем установить новый текст, в то же время ProgressBar - не содержит никакой текст и осуществлять проверку на то, какой текст в него установлен, нет смысла. Поэтому в зависимости от того, какой элемент интерфейса мы тестируем, нужно выбирать правильную реализацию KBaseView. Сейчас мы тестируем кнопку, поэтому выбрали KButton. На следующем экране мы будем тестировать заголовок (TextView) и поле ввода (EditText) и выберем соответствующие реализации KBaseView.

<img src="../images/simple_test/needed_children.png" alt="Show children which we need" width="200"/>

<br>Идем дальше, эту кнопку тест должен найти на экране по какому-то критерию. В данном случае мы осуществим поиск элемента по id, поэтому используем матчер withId, куда в качестве параметра передаем идентификатор кнопки, который мы нашли благодаря Layout Inpector.

<br>Для того чтобы укзатать этот id мы использовали синтаксис R.id..., где R - это класс со всеми ресурсами приложения. Благодаря нему, можно находить id элементов интерфейса, строк, которые есть в проекте, картинок и т.д. При вводе названия этого класса Android Studio должна импортировать его автоматически, но иногда этого не происходит, тогда нужно ввести этот импорт вручную

```kotlin
import com.kaspersky.kaspresso.tutorial.R
```

Все, теперь у нас есть модель главного экрана и эта модель содержит кнопку, которую можно тестировать. Можем писать сам тест

## Добавляем SimpleActivityTest

<br> В папке `androidTest` - `kotlin` создаем класс `SimpleActivityTest` наследуемся от `TestCase()`. 

<img src="../images/simple_test/create_test_1.png" alt="Creating Test First part" width="200"/>
<img src="../images/simple_test/create_test_2.png" alt="Creating Test Second part" width="200"/>

<br> Обратите внимание на импорты, класс TestCase должен быть импортирован из пакета `import com.kaspersky.kaspresso.testcases.api.testcase.TestCase`.

```kotlin
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase

class SimpleActivityTest: TestCase() {
}

```
<br> И добавляем метод test(), в котором будем проверять работу приложения. У него может быть любое имя, необязательно "test", но важно, чтобы он был помечен аннотацией @Test (import org.junit.Test).

```kotlin
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

class SimpleActivityTest : TestCase() {

    @Test
    fun test() {

    }
}
```

<br> Тест `SimpleActivityTest` можно запустить. Информацию по запуску тестов в Android Studio можно найти в  [предыдущем уроке](https://kasperskylab.github.io/Kaspresso/Tutorial/Running_the_first_test/)

<img src="../images/simple_test/success_1.png" alt="Success passed test" width="200"/>

<br> Сейчас этот тест ничего не делает, поэтому и завершается успешно. Давайте добавим ему логики и протестируем MainScreen. 

```kotlin
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import screen.MainScreen

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

<br>Внутри метода test мы получаем объект MainScreen, открываем фигурные скобки и обращаемся к кнопке, которую будем тестировать, дальше открываем еще раз фигурные скобки и тут пишем все проверки. Сейчас, благодаря методам `isVisible()` и `isClickable()` мы проверяем, что кнопка видима и по ней можно кликнуть. Запускаем и наш тест падает.

<img src="../images/simple_test/test_failed_1.png" alt="Feailed test" width="200"/>

<br>Дело в том, что Page Object `MainScreen` относится к `MainActivity` (именно эту активити видит пользователь, когда запускает приложение) и, для того чтобы элементы отобразились на экране, эту активити нужно запустить перед выполнением теста. Для того, чтобы перед тестом была запущена какая-то активити, ножно добавить следующие строки:

```kotlin
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()
```

<br> Этот тест осуществит запуск указанной activity `MainActivity` перед запуском теста и закроет после прогона теста
<br> Подробнее про `activityScenarioRule` можно почитать [здесь](https://developer.android.com/reference/androidx/test/ext/junit/rules/ActivityScenarioRule)

<br>Тогда весь код теста будет выглядеть следующим образом:

```kotlin
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import org.junit.Rule
import org.junit.Test
import screen.MainScreen

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

<br>Запускаем. Все отлично, у нас тест проходит успешно и вы можете увидеть на девайсе, что во время теста открывается нужная нам активити и закрывается после прогона.

<img src="../images/simple_test/success_2.png" alt="Success test" width="200"/>

<br>Хорошей практикой во время написания тестов является проверка, что тест не только успешно выполняется, но и падает, если условие не выполняется. Так вы исключите ситуацию, когда тесты "зеленые", но на самом деле из-за какой-то ошибки в коде проверки вообще не выполнялись. Давайте это сделаем, проверим, что кнопка содержит некорректный текст. 

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

<br>Тест падает, меняем текст на корректный.

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

<br>Тест проходит успешно. 

<br>Теперь нам нужно протестировать SimpleActivity. Делаем по аналогии с MainScreen - создаем Page Object
```kotlin
object SimpleActivityScreen : KScreen<SimpleActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null
}
```
<br>Ищем айди элементов через Layout Inspector

<img src="../images/simple_test/title_inspect.png" alt="Title id in inspector" width="200"/>
<img src="../images/simple_test/input_inspect.png" alt="Input id in inspector" width="200"/>
<img src="../images/simple_test/button_inspect.png" alt="Button id in inspector" width="200"/>

<br>Не забываем указывать корректные View элементы, для заголовка - KTextView, для поля ввода - KEditText, для кнопки - KButton

```kotlin
object SimpleActivityScreen : KScreen<SimpleActivityScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleTitle = KTextView { withId(R.id.simple_title) }
    val inputText = KEditText { withId(R.id.input_text) }
    val changeTitleButton = KButton { withId(R.id.change_title_btn) }
}
```

<br>И теперь можем тестировать этот экран. Для того, чтобы на него перейти, на главном экране нужно кликнуть на кнопку, вызываем `click()`

<br>Добавляем проверки для этого экрана

```kotlin
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import org.junit.Rule
import org.junit.Test
import screen.MainScreen
import screen.SimpleActivityScreen

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

<br>Наш первый тест практически готов. Единственное изменение, которое стоит сделать - тут мы используем захардкоженный текст "Default title". При этом тест успешно проходит, но если вдруг приложение будет локализовано на разные языки, то при запуске теста с английской локалью тест может проходить успешно, а если запустим на устройстве с российской локалью, то тест упадет. 

<br>Поэтому вместо того, чтобы хардкодить строку, мы возьмем ее из ресурсов приложения. В макете активити мы можем посмотреть, какая строка использовалась в этом TextView.

<img src="../images/simple_test/find_string_in_layout.png" alt="Find string in layout" width="200"/>

<br>Переходим в строковые ресурсы (файл values/strings.xml)

<img src="../images/simple_test/string_in_values.png" alt="Find string in values folder" width="200"/>

<br>Копируем id строки
<br>Вместо использования строки используем ее id. `R.string.simple_activity_default_title`
<br>Импортируем класс ресурсов R

Финальный код теста выглядит вот так:

```kotlin
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R
import org.junit.Rule
import org.junit.Test
import screen.MainScreen
import screen.SimpleActivityScreen

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
<br> В этом уроке мы написали наш первый тест на Kaspresso. На практике познакомились с подходом PageObject. Научились получать идентификаторы элементов интерфейса при помощи Layout instector. 
