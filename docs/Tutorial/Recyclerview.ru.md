# RecyclerView. Тестирование списков

На практике часто приходится работать с экранами, которые содержат списки элементов, причем эти списки динамические, и их размер и содержимое могут изменяться. При тестировании таких экранов есть свои особенности. О них мы поговорим в этом уроке.

Откройте приложение `tutorial` и кликните по кнопке `List Activity`.

<img src="../images/recycler_view/main_screen.png" alt="Main Screen" width="300"/>

У вас откроется следующий экран:

<img src="../images/recycler_view/todo_list.png" alt="Todo List " width="300"/>

На нем отображается список дел пользователя. У каждого элемента списка есть порядковый номер, текст и цвет, который устанавливается в зависимости от приоритета. Если приоритет низкий, то цвет фона зеленый, если средний, то оранжевый, если высокий, то красный.

Также имеется возможность удалять элементы списка при помощи свайпа.

<img src="../images/recycler_view/swiped.png" alt="Swipe element" width="300"/>

<img src="../images/recycler_view/removed.png" alt="Remove element" width="300"/>

Давайте напишем тесты на этот экран. Нам понадобится id элементов списка, для их поиска воспользуемся LayoutInspector

<img src="../images/recycler_view/layout_inspector.png" alt="Layout Inspector"/>

Обратите внимание, что все элементы списка лежат внутри `RecyclerView`, у которого id `rv_notes`. Внутри него лежит три объекта, у которых одинаковые идентификаторы: `note_container`, содержащий `tv_note_id` и `tv_note_text`.

Получается, что протестировать экран обычным способом у нас не получится, так как у всех элементов один и тот же id, вместо этого мы используем другой подход. PageObject экрана со списком заметок будет содержать всего один элемент – `RecyclerView`, а элементы списка будут представлять собой отдельные PageObject-ы, данные которых мы будем проверять.

Начинаем создавать тест. Первым делом добавляем PageObject `NoteListScreen`:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.recycler.KRecyclerView

object NoteListScreen : KScreen<NoteListScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val rvNotes = KRecyclerView { withId(R.id.rv_notes) }
}
```
 
Если мы напишем такой код, то у нас возникнут ошибки. Дело в том, что если вы тестируете `RecyclerView`, то предполагается, что проверять вы будете элементы списка, а не контейнер с этими элементами. Поэтому при создании экземпляра `KRecyclerView` недостаточно передать только matcher, по которому объект будет найден, необходимо передать второй параметр, который называется `itemTypeBuilder`.

!!! info
     Если вы хотите узнать, какие параметры нужно передать в определенный метод или конструктор, то можно нажать комбинацию клавиш `ctrl + P` (`cmd + P` на Mac OS), и у вас отобразится подсказка, в которой будут указаны необходимые аргументы

Мы уже говорили ранее о том, что Page Object нам понадобится для каждого элемента списка, поэтому необходимо создать соответствующий класс, экземпляр этого класса мы будем передавать в `itemTypeBuilder`.

В этом же файле добавляем класс `NoteItemScreen`, в этот раз наследуемся не от `KScreen`, а от `KRecyclerViewItem`, так как сейчас это не обычный Page Object, а элемент списка `RecyclerView`

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView

object NoteListScreen : KScreen<NoteListScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val rvNotes = KRecyclerView { withId(R.id.rv_notes) }

    class NoteItemScreen: KRecyclerItem<NoteItemScreen>() {

    }
}
```

Обратите внимание на то, что раньше при создании Page Object мы писали ключевое слово `object`, а здесь нужно написать `class`. Причина в том, что все тестируемые экраны до сих пор были в единственном экземпляре, а здесь у нас будет несколько элементов списка, каждый из которых будет Page Object-ом, поэтому мы создаем класс, и для каждого элемента будем получать экземпляр этого класса.

!!! info
     Подробнее про [классы]( https://kotlinlang.org/docs/classes.html) и [объекты]( https://kotlinlang.org/docs/object-declarations.html) вы можете почитать в официальной документации Kotlin.

В заметках нам нужны будут элементы - корневой `note_container` и два `TextView`. Если мы попытаемся найти их на экране по id, то возникнет ошибка, так как на экране таких элементов несколько и непонятно, какой конкретно нам нужен. 

Эта проблема решается следующим образом - каждая заметка представляет собой отдельный экземпляр View и поиск элементов мы будем осуществлять не на всем экране, а только внутри этих самых View (заметок). Для реализации такой логики в качестве параметра конструктора `KRecyclerViewItem` необходимо передать объект `matcher`. Во время тестирования для каждого объекта будет передан свой `matcher`, в котором мы найдем необходимые View-элементы.

Поэтому в качестве параметра передаем `matcher`:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import org.hamcrest.Matcher

object NoteListScreen : KScreen<NoteListScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val rvNotes = KRecyclerView { withId(R.id.rv_notes) }

    class NoteItemScreen(matcher: Matcher<View>) : KRecyclerItem<NoteItemScreen>(matcher) {
        
    }
}
```
Можем в NoteItemScreen добавлять элементы интерфейса, которые будем тестировать.

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object NoteListScreen : KScreen<NoteListScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val rvNotes = KRecyclerView { withId(R.id.rv_notes) }

    class NoteItemScreen(matcher: Matcher<View>) : KRecyclerItem<NoteItemScreen>(matcher) {

        val noteContainer = KView(matcher) { withId(R.id.note_container) }
        val tvNoteId = KTextView(matcher) { withId(R.id.tv_note_id) }
        val tvNoteText = KTextView(matcher) { withId(R.id.tv_note_text) }
    }
}
```
Обратите внимание на два важных момента:

Первое - в конструктор View-элементов теперь необходимо передать matcher, в котором будем произведен поиск необходимого объекта. Если этого не сделать, тест завершится неудачно
    
Второе - если мы проверяем какое-то специфичное поведение элемента UI, то указываем конкретного наследника `KView` (`KTextView`, `KEditText`, `KButton`...). Например, если мы хотим проверить наличие текста, то создаем `KTextView`, у которого есть возможность получить текст. 

А если мы проверяем какие-то общие вещи, которые доступны во всех элементах интерфейса (цвет фона, размеры, видимость и т.д.), то можно использовать родительский KView. В данном случае мы будем проверять тексты у `tvNoteId` и `tvNoteText`, поэтому указали тип `KTextView`. А контейнер, в котором лежат эти `TextView` является экземпляром `CardView`, у него мы будем проверять только цвет фона, каких-то специфичных вещей проверять у него нет необходимости, поэтому в качестве типа мы указали родительский - `KView`

Когда PageObject элемента списка готов, можно создавать экземпляр `KRecyclerView`, для этого передаем два параметра: 

Первый – `builder`, в котором найдем `RecyclerView` по его id:

```kotlin
val rvNotes = KRecyclerView(
    builder = { withId(R.id.rv_notes) },
)
```
Второй – `itemTypeBuilder`, здесь необходимо вызвать функцию `itemType`, где создать экземпляр `NoteItemScreen`:

```kotlin
val rvNotes = KRecyclerView(
    builder = { withId(R.id.rv_notes) },
    itemTypeBuilder = {
        itemType {
            NoteItemScreen(it)
        }
    }
)
```

!!! info
     Подробнее про лямбда-выражения можно почитать [здесь](https://kotlinlang.org/docs/lambdas.html).

Эту запись можно сократить, используя [Method Reference](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html), тогда финальная версия класса будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object NoteListScreen : KScreen<NoteListScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val rvNotes = KRecyclerView(
        builder = { withId(R.id.rv_notes) },
        itemTypeBuilder = { itemType(::NoteItemScreen) }
    )

    class NoteItemScreen(matcher: Matcher<View>) : KRecyclerItem<NoteItemScreen>(matcher) {

        val noteContainer = KView(matcher) { withId(R.id.note_container) }
        val tvNoteId = KTextView(matcher) { withId(R.id.tv_note_id) }
        val tvNoteText = KTextView(matcher) { withId(R.id.tv_note_text) }
    }
}
```
Теперь давайте в Page Object `Main Screen` добавим кнопку перехода на данный экран:

```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.text.KButton

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val simpleActivityButton = KButton { withId(R.id.simple_activity_btn) }
    val wifiActivityButton = KButton { withId(R.id.wifi_activity_btn) }
    val loginActivityButton = KButton { withId(R.id.login_activity_btn) }
    val notificationActivityButton = KButton { withId(R.id.notification_activity_btn) }
    val makeCallActivityButton = KButton { withId(R.id.make_call_activity_btn) }
    val flakyActivityButton = KButton { withId(R.id.flaky_activity_btn) }
    val listActivityButton = KButton { withId(R.id.list_activity_btn) }
}
```
Теперь можно приступать к проверке экрана со списком заметок.

## Тестирование NoteListScreen

Создаем класс для тестирования, и, как обычно, добавляем переход на данный экран:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
    }
}
```

Теперь давайте проверим, что на экране со списком заметок отображается три элемента, для этого у `KRecyclerView` можно вызвать метод `getSize`:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check notes count") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
    }
}
```

У `KRecyclerView` есть множество полезных методов, можете поставить точку после названия объекта и посмотреть все возможности. Например, при помощи `firstChild` или `lastChild` можно получить соответственно первый или последний элемент `NoteItemScreen`. Также можно найти элемент по его позиции или выполнить проверки для абсолютно всех заметок при помощи метода `children`. Для их использования в угловых скобках нужно указать тип `KRecyclerViewItem`, в нашем случае это `NoteItemScreen`.

Давайте проверим видимость всех элементов и что все они содержат какой-то текст:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check notes count") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
        step("Check elements visibility") {
            NoteListScreen {
                rvNotes {
                    children<NoteListScreen.NoteItemScreen> {
                        tvNoteId.isVisible()
                        tvNoteText.isVisible()
                        noteContainer.isVisible()

                        tvNoteId.hasAnyText()
                        tvNoteText.hasAnyText()
                    }
                }
            }
        }
    }
}
```
Также можем протестировать каждый элемент в отдельности. Давайте проверим, что каждая заметка содержит правильные тексты и цвета фона:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check notes count") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
        step("Check elements visibility") {
            NoteListScreen {
                rvNotes {
                    children<NoteListScreen.NoteItemScreen> {
                        tvNoteId.isVisible()
                        tvNoteText.isVisible()
                        noteContainer.isVisible()

                        tvNoteId.hasAnyText()
                        tvNoteText.hasAnyText()
                    }
                }
            }
        }
        step("Check elements content") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_green_light)
                        tvNoteId.hasText("0")
                        tvNoteText.hasText("Note number 0")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(2) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
    }
}
```

## Проверка свайпа

В приложении есть возможность удалять заметки при помощи свайпа. Давайте проверим этот момент – удалим первую заметку и убедимся, что на экране осталось два элемента с соответствующим контентом.

Чтобы выполнять какие-то действия с View-элементами, мы можем получить объект `view` и вызвать у него метод `perform` в качестве параметра передав нужное действие. В данном случае выполняем swipe влево, тогда код будет выглядеть следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check notes count") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
        step("Check elements visibility") {
            NoteListScreen {
                rvNotes {
                    children<NoteListScreen.NoteItemScreen> {
                        tvNoteId.isVisible()
                        tvNoteText.isVisible()
                        noteContainer.isVisible()

                        tvNoteId.hasAnyText()
                        tvNoteText.hasAnyText()
                    }
                }
            }
        }
        step("Check elements content") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_green_light)
                        tvNoteId.hasText("0")
                        tvNoteText.hasText("Note number 0")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(2) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
        step("Check swipe to dismiss action") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        view.perform(ViewActions.swipeLeft())
                    }
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
    }
}
```

В последнем шаге мы удаляем элемент по индексу 0 и проверяем, что теперь по этому индексу лежит “Note number 1”.

## Wait for idle

Вы могли обратить внимание, что все проверки выполняются сразу после свайпа, даже не дожидаясь завершения анимации. Сейчас тест проходит успешно, но иногда это может привести к ошибкам. 

Поэтому в случаях, когда какое-то действие выполняется с анимацией и для его завершения требуется время, можно вызвать метод `device.uiDevice.waitForIdle`. Этот метод остановит выполнения теста до тех пор, пока экран не перейдет в состояние idle (бездействующее) – когда не происходит никаких действий и не выполняются анимации.

Добавляем эту строчку в тест после свайпа, и проверим, что количество элементов стало равно двум:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check notes count") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
        step("Check elements visibility") {
            NoteListScreen {
                rvNotes {
                    children<NoteListScreen.NoteItemScreen> {
                        tvNoteId.isVisible()
                        tvNoteText.isVisible()
                        noteContainer.isVisible()

                        tvNoteId.hasAnyText()
                        tvNoteText.hasAnyText()
                    }
                }
            }
        }
        step("Check elements content") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_green_light)
                        tvNoteId.hasText("0")
                        tvNoteText.hasText("Note number 0")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(2) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
        step("Check swipe to dismiss action") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        view.perform(ViewActions.swipeLeft())
                        device.uiDevice.waitForIdle()
                    }

                    Assert.assertEquals(2, getSize())

                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }

                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
    }
}
```
## Extract methods to Page Object

Остался еще один момент, который мы рассмотрим в этом уроке. 

Бывают случаи, когда в Page Object нужно добавить какое-то поведение. Например, сейчас можно делать свайп по элементам списка. В тесте это делается при помощи этой строчки кода `view.perform(ViewActions.swipeLeft())`.

Каждый раз, когда нам понадобится сделать свайп, придется выполнять те же действия – получать объект `view`, вызывать метод передавая параметр. Вместо этого мы можем в классе Page Object добавить необходимую функциональность и затем использовать ее, где необходимо.

Добавляем метод в класс NoteItemScreen, назовем swipeLeft:

```kotlin
class NoteItemScreen(matcher: Matcher<View>) : KRecyclerItem<NoteItemScreen>(matcher) {

    val noteContainer = KView(matcher) { withId(R.id.note_container) }
    val tvNoteId = KTextView(matcher) { withId(R.id.tv_note_id) }
    val tvNoteText = KTextView(matcher) { withId(R.id.tv_note_text) }

    fun swipeLeft() {
        view.perform(ViewActions.swipeLeft())
    }
}
```
Теперь в любом месте, где необходимо сделать свайп, мы просто у объекта `NoteItemScreen` вызовем созданный нами метод:

```kotlin
childAt<NoteListScreen.NoteItemScreen>(0) {
    swipeLeft()
    device.uiDevice.waitForIdle()
}
```
Тогда весь код теста будет выглядеть так:

```kotlin
package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.NoteListScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NoteListTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkNotesScreen() = run {
        step("Open note list screen") {
            MainScreen {
                listActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check notes count") {
            NoteListScreen {
                Assert.assertEquals(3, rvNotes.getSize())
            }
        }
        step("Check elements visibility") {
            NoteListScreen {
                rvNotes {
                    children<NoteListScreen.NoteItemScreen> {
                        tvNoteId.isVisible()
                        tvNoteText.isVisible()
                        noteContainer.isVisible()

                        tvNoteId.hasAnyText()
                        tvNoteText.hasAnyText()
                    }
                }
            }
        }
        step("Check elements content") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_green_light)
                        tvNoteId.hasText("0")
                        tvNoteText.hasText("Note number 0")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }
                    childAt<NoteListScreen.NoteItemScreen>(2) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
        step("Check swipe to dismiss action") {
            NoteListScreen {
                rvNotes {
                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        swipeLeft()
                        device.uiDevice.waitForIdle()
                    }

                    Assert.assertEquals(2, getSize())

                    childAt<NoteListScreen.NoteItemScreen>(0) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_orange_light)
                        tvNoteId.hasText("1")
                        tvNoteText.hasText("Note number 1")
                    }

                    childAt<NoteListScreen.NoteItemScreen>(1) {
                        noteContainer.hasBackgroundColor(android.R.color.holo_red_light)
                        tvNoteId.hasText("2")
                        tvNoteText.hasText("Note number 2")
                    }
                }
            }
        }
    }
}
```

!!! info
    Обратите внимание, что никакой бизнес-логики добавлять в Page Object не нужно. Вы можете наделить эти объекты определенными свойствами, добавить функциональность, но добавлять сложную логику не следует. Page Object должен оставаться моделью экрана с описанными элементами интерфейса и функциями по взаимодействию с этими элементами.

## Итог

В этом уроке мы научились тестировать списки элементов, установленные в RecyclerView. Узнали, как можно найти элементы, как взаимодействовать с ними и проверять их поведение на соответствие ожидаемому результату. 



<br>
