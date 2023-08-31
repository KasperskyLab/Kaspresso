# Screenshot-тесты. Часть 2. Установка стейтов и работа с ViewModel.

Если в вашем приложении планируется использование screenshot-тестов, то этот момент нужно учитывать не только при написании тестов, но также при разработке приложения. В сегодняшнем уроке мы поближе познакомимся с установкой стейтов, внесем изменения в код приложения, чтобы его можно было покрыть тестами, и напишем первый скриншот тест, в котором будет работа с ViewModel.

## Предварительные знания

Если вы ранее не разрабатывали приложения под Android, то сегодняшний урок может быть сложным для понимания. Поэтому мы настоятельно рекомендуем перед прохождением данного урока ознакомиться со следующими темами:

1. [Фрагменты](https://developer.android.com/guide/fragments) – что это, и как с ними работать
2. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) и шаблон проектирования MVVM
3. [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
4. [Библиотека Mockk](https://mockk.io/)
5. [Kotlin coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Обзор тестируемого приложения

В приложении, которое мы сегодня будем покрывать тестами, имитируется загрузка данных. При клике на кнопку начинается загрузка, отображается прогресс бар, после окончания загрузки, в зависимости от результата, мы увидим либо загруженные данные, либо сообщение об ошибке.

Откройте приложение tutorial и кликнете по кнопке «Load User Activity»

<img src="../images/screenshot_tests_2/example_1.png" alt="Tutorial app" width="300"/>

Давайте сразу начнем разбираться, что такое стейты. После перехода по кнопке у вас откроется экран, на котором располагается одна кнопка и больше ничего нет.

<img src="../images/screenshot_tests_2/example_2.png" alt="Initial state" width="300"/>

При клике на данную кнопку внешний вид экрана изменится, то есть изменится его состояние или, как его часто называют, стейт. Сейчас мы видим исходный стейт экрана, назовем его `Initial`.

Кликнете по этой кнопке и обратите внимание, как изменится стейт экрана.

<img src="../images/screenshot_tests_2/example_3.png" alt="Progress state" width="300"/>

Кнопка стала неактивной, то есть по ней больше нельзя кликать, и на экране появился Progress Bar, который показывает, что идет процесс загрузки. Этот стейт отличается от исходного, назовем этот стейт `Progress`.

Через несколько секунд загрузка будет завершена, и вы увидите на экране загруженные данные пользователя (его имя и фамилию).

<img src="../images/screenshot_tests_2/example_4.png" alt="Content state" width="300"/>

Это третий стейт экрана. В таком состоянии кнопка снова становится активной, прогресс бар скрывается, и на экране отображаются имя и фамилия пользователя. Назовем такой стейт `Content`.

В данном случае мы имитируем загрузку данных, в реальных приложениях работа с интернетом может завершиться с ошибкой. Такие ошибки нужно каким-то образом обрабатывать, к примеру, показывать уведомление пользователю. В нашем тестовом приложении мы сымитировали подобное поведение. Если вы попытаетесь загрузить пользователя несколько раз, то какая-то из этих попыток завершится с ошибкой и вы увидите следующее состояние экрана:

<img src="../images/screenshot_tests_2/example_5.png" alt="Error state" width="300"/>

Это четвертый и последний стейт экрана, который показывает состояние ошибки, назовем его `Error`.

## Простой Screenshot-тест

Перед тем, как мы изучим правильный способ покрытия тестами данного экрана, давайте напишем тесты тем способом, который мы уже знаем. Это нужно для того, чтобы вы понимали, почему так делать не стоит, и зачем нам вносить изменения в уже написанный код.

В пакете `screenshot_tests` создаем класс `LoadUserScreenshots`

<img src="../images/screenshot_tests_2/create_class.png" alt="Create class"/>

Наследуемся от `DocLocScreenshotTestCase` и передаем список языков в качестве параметра конструктору, сделаем скриншоты для английской и французской локалей

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

}

```
Как мы говорили ранее – screenshot-тесты должны быть максимально легковесными, чтобы их прохождение занимало как можно меньше времени, поэтому вместо открытия главного экрана и перехода на экран загрузки данных пользователя, мы сразу будем открывать `LoadUserActivity`, создаем соответствующее правило.

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import org.junit.Rule

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()
}

```

Для того чтобы получить все состояния экрана мы будем, как и раньше, имитировать действия пользователя – кликать по кнопке и ждать получения результата. Создаем `PageObject` этого экрана. В пакете `com.kaspersky.kaspresso.tutorial.screen` добавляем класс `LoadUserScreen`, тип `Object`

<img src="../images/screenshot_tests_2/page_object.png" alt="Create page object"/>

Наследумся от `KScreen` и добавляем все необходимые UI-элементы: кнопка загрузки, ProgressBar, TextView с именем пользователя и TextView с текстом ошибки


```kotlin
package com.kaspersky.kaspresso.tutorial.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import io.github.kakaocup.kakao.progress.KProgressBar
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object LoadUserScreen : KScreen<LoadUserScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val loadingButton = KButton { withId(R.id.loading_button) }
    val progressBarLoading = KProgressBar { withId(R.id.progress_bar_loading) }
    val username = KTextView { withId(R.id.username) }
    val error = KTextView { withId(R.id.error) }
}
```
Можем создавать скриншот-тест. Добавляем метод `takeScreenshots`

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {

    }
}

``` 

Первым делом давайте дождемся, пока кнопка отобразится на экране и сделаем первый скриншот исходного состояния экрана

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            loadingButton.isVisible()
            captureScreenshot("Initial state")
        }
    }
}

```
Далее необходимо кликнуть по кнопке и сохранить снимок экрана в состоянии загрузки

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            loadingButton.isVisible()
            captureScreenshot("Initial state")
            loadingButton.click()
            progressBarLoading.isVisible()
            captureScreenshot("Progress state")
        }
    }
}

```

На этом этапе уже можно видеть одну проблему - мы загружаем данные из сети, в зависимости от скорости интернета и от работоспособности сервера скорость загрузки может меняться. Есть большая вероятность, что данные о пользователе будут загружены почти мгновенно, и состояние загрузки вы вообще не увидите, оно не будет сохранено на скриншотах, и тест упадет, потому что проверка на `progressBarLoading.isVisible` никогда не вернет значение true. Решение этой проблемы мы разберем далее в этом уроке.

Следующий этап – отображение данных о пользователе (стейт Content)

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            loadingButton.isVisible()
            captureScreenshot("Initial state")
            loadingButton.click()
            progressBarLoading.isVisible()
            captureScreenshot("Progress state")
            username.isVisible()
            captureScreenshot("Content state")
        }
    }
}

```
Теперь нам нужно получить состояние ошибки. Как вариант, мы можем отключить интернет на устройстве, затем кликнуть по кнопке и увидеть сообщение об ошибке. Тогда нужно не забывать перед проведением тестирования, а также после него включать интернет, чтобы наш тест всегда запускался в необходимых условиях, а также не влиял на другие тесты. После включения и выключения теста добавим небольшой таймаут, чтобы быть уверенным, что интернет-подключение перешло в нужное нам состояние.

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            device.network.enable()
            Thread.sleep(5000)
            loadingButton.isVisible()
            captureScreenshot("Initial state")
            loadingButton.click()
            progressBarLoading.isVisible()
            captureScreenshot("Progress state")
            username.isVisible()
            captureScreenshot("Content state")
            device.network.disable()
            Thread.sleep(3000)
            loadingButton.click()
            error.isVisible()
            captureScreenshot("Error state")
            device.network.enable()
            Thread.sleep(5000)
        }
    }
}

```


## Проблемы текущего подхода

Таким образом, мы смогли написать скриншот тест, в котором получили все необходимые состояния экрана, имитируя действия пользователя – кликая по кнопке и ожидая результата выполнения запроса. Но давайте подумаем, насколько эта реализация подойдет для реальных приложений.

1. Состояние прогресса зависит от скорости интернет-соединения и от работоспособности сервера. Если ответ вернется очень быстро, то состояние прогресса мы никогда не получим, и наш тест завершится неудачно.

2. Для получения состояния ошибки мы отключаем интернет на устройстве, но в реальных приложениях часто бывает ситуация, когда в зависимости от типа ошибки поведение приложения должно отличаться. Например, если на устройстве нет интернета, то нужно показать сообщение пользователю, чтобы он включил интернет, а если сервер вернул какую-то ошибку, то отобразить соответствующий диалог. В этом случае сымитировать состояние ошибки в приложении становится очень сложной задачей.

3. Цель «скриншотилки» - сделать снимки всех возможных состояний экрана, а не проверить работоспособность приложения. Тем не менее, если в момент проведения теста будут какие-то проблемы с интернетом, или сервер не будет отвечать на запросы, то при такой реализации тест завершится неудачно, и никаких снимков мы не получим.

Так мы приходим к следующему выводу: получить все необходимые стейты, имитируя действия пользователя, для скриншот-тестов нецелесообразно.

Во-первых, некоторые состояние экрана получить очень сложно или даже невозможно.

Во-вторых, такие тесты начинают зависеть от многих факторов, таких как скорость интернета и работоспособность сервера, следовательно вероятность того, что эти тесты завершатся неудачно, возрастает.


## Взаимодействие View и ViewModel

По причинам, рассмотренным выше, в скриншот-тестах стейты устанавливают другим способом. Имитировать действия пользователя мы не будем.

На этом этапе важно понимать паттерн MVVM (Model-View-ViewModel). Если говорить кратко, то согласно этому паттерну в приложении логика отделяется от видимой части.

Видимая часть, или можно сказать экраны (Activity и Fragments) отвечают за отображение элементов интерфейса и взаимодействия с пользователем. То есть они показывают вам какие-то элементы (кнопки, поля ввода и т.д.) и реагируют на действия пользователя (клики, свайпы и т.д). В паттерне MVVM эта часть называется View.

ViewModel в этом паттерне отвечает за логику.

Их взаимодействие выглядит следующим образом: ViewModel у себя хранит стейт экрана, она определяет, что следует показать пользователю. View получает этот стейт из ViewModel, и, в зависимости от полученного значения, отрисовывает нужные элементы. Если пользователь выполняет какие-то действия, то View вызывает соответствующий метод из ViewModel.

Давайте посмотрим пример из нашего приложения. На экране есть кнопка загрузки, пользователь кликнул по ней, View вызывает метод загрузки данных из ViewModel.

Откройте класс `LoadUserFragment` из пакета `com.kaspersky.kaspresso.tutorial.user`. Этот фрагмент в паттерне MVVM представляет собой View. В следующем фрагменте кода мы устанавливаем слушатель клика на кнопку и говорим, чтобы при клике на нее был вызван метод `loadUser` из ViewModel

```kotlin
binding.loadingButton.setOnClickListener {
    viewModel.loadUser()
}
```

Логика загрузки реализована внутри ViewModel. Откройте класс `LoadUserViewModel` из пакета `com.kaspersky.kaspresso.tutorial.user`.

При вызове этого метода ViewModel меняет стейт экрана: при старте загрузки устанавливает стейт Progress, после окончания загрузки в зависимости от результата она устанавливает стейт Error или Content.

```kotlin
fun loadUser() {
    viewModelScope.launch {
        _state.value = State.Progress
        try {
            val user = repository.loadUser()
            _state.value = State.Content(user)
        } catch (e: Exception) {
            _state.value = State.Error
        }
    }
}

```
View (в данном случае фрагмент `LoadUserFragment`) подписывается на стейт из ViewModel и в зависимости от полученного значения меняет содержимое экрана. Происходит это в методе `observeViewModel`

```kotlin
private fun observeViewModel() {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Content -> {
                        binding.progressBarLoading.isVisible = false
                        binding.loadingButton.isEnabled = true
                        binding.error.isVisible = false
                        binding.username.isVisible = true

                        val user = state.user
                        binding.username.text = "${user.name} ${user.lastName}"
                    }
                    State.Error -> {
                        binding.progressBarLoading.isVisible = false
                        binding.loadingButton.isEnabled = true
                        binding.error.isVisible = true
                        binding.username.isVisible = false
                    }
                    State.Progress -> {
                        binding.progressBarLoading.isVisible = true
                        binding.loadingButton.isEnabled = false
                        binding.error.isVisible = false
                        binding.username.isVisible = false
                    }
                    State.Initial -> {
                        binding.progressBarLoading.isVisible = false
                        binding.loadingButton.isEnabled = true
                        binding.error.isVisible = false
                        binding.username.isVisible = false
                    }
                }
            }
        }
    }
}

```

Таким образом происходит взаимодействие View и ViewModel. ViewModel хранит стейт экрана, а View отображает его. При этом если пользователь совершил какие-то действия, то View сообщает об этом ViewModel и ViewModel меняет стейт экрана.

Получается, что если мы хотим изменить состояние экрана, то можно изменить значение стейта внутри ViewModel, View отреагирует на это и отрисует то, что нам нужно. Именно этим мы и будем заниматься при написании скриншот-тестов.

## Мокирование ViewModel

Давайте внутри тестового класса создадим объект ViewModel, у которого будем устанавливать стейт

```kotlin
class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val viewModel = LoadUserViewModel()

…
}

```
Теперь в эту ViewModel внутри тестового метода мы будем устанавливать новый стейт. Давайте попробуем установить какое-то новое значение в переменную `state`.

!!! info
    Далее мы будем работать с объектами StateFlow и MutableStateFlow, если вы не знаете, что это, и как с ними работать, обязательно прочитайте [документацию]( https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val viewModel = LoadUserViewModel()

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            viewModel.state.value = State.Initial
            …
        }
    }
}

```
У нас возникает ошибка. Дело в том, что переменная `state` внутри ViewModel имеет тип `StateFlow`, который является неизменяемым. То есть установить новый стейт в этот объект не получится. Если вы посмотрите в код `LoadUserViewModel`, то увидите, что все новые значения стейта устанавливаются в переменную с нижним подчеркиванием `_state`, у которой тип `MutableStateFlow`

```kotlin
viewModelScope.launch {
    _state.value = State.Progress
    try {
        val user = repository.loadUser()
        _state.value = State.Content(user)
    } catch (e: Exception) {
        _state.value = State.Error
    }
}

```
Эта переменная с нижним подчеркиванием является изменяемым объектом, в который можно устанавливать новые значения, но она имеет модификатор доступа `private`, то есть снаружи обратиться к ней не получится.

Как быть в этом случае? Нам необходимо добиться такого поведения, чтобы мы внутри тестового метода устанавливали новые значения стейта, а фрагмент на эти изменения реагировал. При этом фрагмент подписывается на `viewModel.state` без нижнего подчеркивания.

Можно пойти другим путем – внутри тестового класса мы создадим свой объект state, который будет изменяемый, и в который мы сможем устанавливать любые значения.

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val _state = MutableStateFlow<State>(State.Initial)
    val viewModel = LoadUserViewModel()

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            _state.value = State.Initial
            …
        }
    }
}

```
Теперь нужно сделать так, чтобы в тот момент, когда фрагмент подписывается на `viewModel.state` вместо настоящей реализации подставлялся наш только что созданный объект. Сделать это можно при помощи библиотеки Mockk. Если вы не работали с этой библиотекой ранее, советуем почитать [официальную документацию]( https://mockk.io)
Для использования данной библиотеки необходимо добавить зависимости в файл `build.gradle`

```kotlin

androidTestImplementation("io.mockk:mockk-android:1.13.3")
```

<img src="../images/screenshot_tests_2/gradle2.png" alt="Gradle"/>

!!! info
    Если после синхронизации и запуска проекта у вас возникают ошибки, следуйте инструкциям в журнале ошибок. В случае, если разобраться не получилось, переключитесь на ветку `TECH-tutorial-results` и сверьте файл `build.gradle` из этой ветки с вашим

Теперь внутренняя реализация ViewModel нас не интересует. Все, что нам нужно – чтобы фрагмент подписывался на `state` из ViewModel, а ему возвращался тот объект, который мы создали внутри тестового класса. Делается это следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val _state = MutableStateFlow<State>(State.Initial)
    val viewModel = mockk<LoadUserViewModel>(relaxed = true){
        every { state } returns _state
    }

    …
}

```

То, что мы сделали, называется мокированием. Мы «замокали» ViewModel таким образом, что если кто-то будет работать с ней и обратится к ее полю `state`, то ему вернется созданный нами объект `_state`. Настоящая реализация `LoadUserViewModel` в тестах использоваться не будет.

Теперь нам не нужно имитировать действия пользователя для получения различных состояний экрана. Вместо этого, мы будем устанавливать стейт в переменную `_state` и затем делать скриншот.

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import com.kaspersky.kaspresso.tutorial.user.User
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val _state = MutableStateFlow<State>(State.Initial)
    val viewModel = mockk<LoadUserViewModel>(relaxed = true) {
        every { state } returns _state
    }

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            _state.value = State.Initial
            captureScreenshot("Initial state")
            _state.value = State.Progress
            captureScreenshot("Progress state")
            _state.value = State.Content(user = User(name = "Test", lastName = "Test"))
            captureScreenshot("Content state")
            _state.value = State.Error
            captureScreenshot("Error state")
        }
    }
}

```
## Дорабатываем код фрагмента

Если мы запустим тест в таком виде, то работать он будет неправильно, никакой смены состояний экрана происходить не будет. Дело в том, что мы создали объект `viewModel`, но нигде его не используем.

Давайте посмотрим, как происходит взаимодействие экрана и ViewModel, и какие изменения нужно внести в код, чтобы экран взаимодействовал не с настоящей ViewModel, а с замоканной.

Для открытия экрана мы запускаем `LoadUserActivity`

```kotlin
package com.kaspersky.kaspresso.tutorial.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.R

class LoadUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_user)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoadUserFragment.newInstance())
                .commit()
        }
    }
}

```
В этой Activity почти нет кода. Дело в том, что в последнее время большинство приложений используют подход [Single Activity]( https://developer.android.com/guide/navigation/migrate#move). При таком подходе все экраны создаются на фрагментах, а активити служит лишь контейнером для них. Если вы хотите узнать больше о преимуществах этого подхода, то мы советуем почитать документацию. Что нужно понимать сейчас – внешний вид экрана и взаимодействие с ViewModel реализовано внутри `LoadUserFragment`, а `LoadUserActivity` представляет собой контейнер, в который мы этот фрагмент установили. Следовательно, изменения нужно делать именно внутри фрагмента.

Открываем `LoadUserFragment`

```kotlin
package com.kaspersky.kaspresso.tutorial.user

class LoadUserFragment : Fragment() {

…

    private lateinit var viewModel: LoadUserViewModel

…

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoadUserViewModel::class.java]
…
}

```
Обратите внимание, что в этом классе есть приватная переменная `viewModel`, а в методе `onViewCreated` мы этой переменной присваиваем значение, создавая объект при помощи `ViewModelProvider`. Нам необходимо добиться такого поведения, чтобы при обычном использовании фрагмента вьюмодель создавалась через `ViewModelProvider`, а если этот фрагмент используется в screenshot-тестах, то должна быть возможность передать замоканную вьюмодель в качестве параметра.

Для создания экземпляра фрагмента мы используем фабричный метод `newInstance`

```kotlin
companion object {

    fun newInstance(): LoadUserFragment = LoadUserFragment()
}

```
В этом методе мы просто создаем объект `LoadUserFragment`. Давайте добавим еще один метод, который будет принимать в качестве параметра замоканную вьюмодель и устанавливать это значение в поле фрагмента. Этот метод мы будем использовать в тестах, поэтому давайте назовем его `newTestInstance`

```kotlin
companion object {

    fun newInstance(): LoadUserFragment = LoadUserFragment()

    fun newTestInstance(
        mockedViewModel: LoadUserViewModel
    ): LoadUserFragment = LoadUserFragment().apply {
        viewModel = mockedViewModel
    }
}

``` 
Теперь для создания фрагмента в активити мы будем вызывать метод `newInstance`, что мы сейчас и делаем

```kotlin
if (savedInstanceState == null) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, LoadUserFragment.newInstance())
        .commit()
}

``` 
А для создания фрагмента внутри скриншот-тестов будем вызывать метод `newTestInstance`.

На данном этапе в методе `onViewCreated` мы присваиваем значение переменной `viewModel` независимо от того, используется этот фрагмент для скриншот-тестов или нет. Давайте это исправим, добавим поле `isForScreenshots` типа `Boolean`, по умолчанию установим значение `false`, а в методе `newTestInstance` установим значение `true`.

```kotlin
package com.kaspersky.kaspresso.tutorial.user

…

class LoadUserFragment : Fragment() {

…

    private lateinit var viewModel: LoadUserViewModel
    private var isForScreenshots = false

…
    companion object {

        fun newInstance(): LoadUserFragment = LoadUserFragment()

        fun newTestInstance(
            mockedViewModel: LoadUserViewModel
        ): LoadUserFragment = LoadUserFragment().apply {
            viewModel = mockedViewModel
            isForScreenshots = true
        }
    }
}

``` 
В методе `onViewCreated` мы будем создавать вьюмодель через `ViewModelProvider` только в том случае, если `isForScreenshots` равен `false`

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (!isForScreenshots) {
        viewModel = ViewModelProvider(this)[LoadUserViewModel::class.java]
    }
    binding.loadingButton.setOnClickListener {
        viewModel.loadUser()
    }
    observeViewModel()
}

```
После создания вьюмодели мы устанавливаем слушатель клика на кнопку загрузки и в этом слушателе вызываем метод вьюмодели. В случае, если мы передали замоканный вариант ViewModel, вызов этого метода `viewModel.loadUser()` приведет к падению теста, так как у нее данный метод не реализован. Поэтому вызов любых методов вьюмодели мы также будем выполнять только в том случае, если этот фрагмент используется не для скриншот-тестов:

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (!isForScreenshots) {
        viewModel = ViewModelProvider(this)[LoadUserViewModel::class.java]
        binding.loadingButton.setOnClickListener {
            viewModel.loadUser()
        }
    }
    observeViewModel()
}

```
Как вы должны помнить, в тестах мы замокали значение переменной `state` из вьюмодели

```kotlin
val _state = MutableStateFlow<State>(State.Initial)
val viewModel = mockk<LoadUserViewModel>(relaxed = true) {
    every { state } returns _state
}

```
Поэтому, когда мы обратимся к полю `viewModel.state` из фрагмента в методе `observeViewModel`

```kotlin
viewModel.state.collect { state ->
    when (state) {
        is State.Content -> {
            …

```
то ошибки не будет, вместо настоящей реализации будет использовано значение из переменной `_state`, созданной внутри теста.

## Тестирование фрагментов

Теперь, для того чтобы написать скриншот тест, нам нужно внутри этого теста создать экземпляр фрагмента, передав ему замоканную вьюмодель в качестве параметра. Но если вы посмотрите на текущий код, то увидите, что мы вообще не создаем здесь никаких фрагментов

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserActivity
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import com.kaspersky.kaspresso.tutorial.user.User
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val _state = MutableStateFlow<State>(State.Initial)
    val viewModel = mockk<LoadUserViewModel>(relaxed = true) {
        every { state } returns _state
    }

    @get:Rule
    val activityRule = activityScenarioRule<LoadUserActivity>()

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            _state.value = State.Initial
            captureScreenshot("Initial state")
            _state.value = State.Progress
            captureScreenshot("Progress state")
            _state.value = State.Content(user = User(name = "Test", lastName = "Test"))
            captureScreenshot("Content state")
            _state.value = State.Error
            captureScreenshot("Error state")
        }
    }
}

```
У нас открывается `LoadUserActivity`, а внутри этой активити создается фрагмент, поэтому передать в тот фрагмент никакие параметры мы не можем.

Если мы тестируем фрагменты, то запускать активити, в которой этот фрагмент лежит, не нужно. Мы можем напрямую тестировать фрагменты. Для того чтобы у нас была такая возможность, необходимо добавить следующие зависимости в build.gradle

<img src="../images/screenshot_tests_2/gradle.png" alt="Gradle"/>

```kotlin
debugImplementation("androidx.fragment:fragment-testing-manifest:1.6.0"){
    isTransitive = false
}
androidTestImplementation("androidx.fragment:fragment-testing:1.6.0")

```

После синхронизации проекта открываем класс `LoadUserScreenshots` и удаляем из него `activityRule`, запускать активити нам больше не нужно.

Для того чтобы запустить фрагмент, необходимо вызвать метод `launchFragmentInContainer` и в фигурных скобках создать фрагмент, который нужно отобразить
```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.fragment.app.testing.launchFragmentInContainer
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserFragment
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import com.kaspersky.kaspresso.tutorial.user.User
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val _state = MutableStateFlow<State>(State.Initial)
    val viewModel = mockk<LoadUserViewModel>(relaxed = true) {
        every { state } returns _state
    }

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            launchFragmentInContainer {
                LoadUserFragment.newTestInstance(mockedViewModel = viewModel)
            }
            _state.value = State.Initial
            captureScreenshot("Initial state")
            _state.value = State.Progress
            captureScreenshot("Progress state")
            _state.value = State.Content(user = User(name = "Test", lastName = "Test"))
            captureScreenshot("Content state")
            _state.value = State.Error
            captureScreenshot("Error state")
        }
    }
}

```

Итак, давайте обсудим, что здесь происходит. Внутри метода `takeScreenshots` мы запускаем фрагмент `LoadUserFragment`. Для создания фрагмента мы воспользовались методом `newTestInstance`, передавая созданный в тестовом классе вариант вьюмодели.

Теперь фрагмент работает не с настоящей вьюмоделью, а с замоканной. Фрагмент отрисовывает стейт, который лежит внутри вьюмодели, но так как мы подменили (замокали) объект `state`, то фрагмент покажет то состояние, которое мы установим в тестовом классе.

С этого момента нам не нужно имитировать действия пользователя, мы просто устанавливаем необходимое состояние, фрагмент его отрисовывает, и мы делаем скриншот.

Если вы сейчас запустите тест, то увидите, что скриншоты всех состояний успешно сохраняются в папку на устройстве, и это происходит гораздо быстрее, чем в предыдущем варианте теста.

## Меняем стиль

Вы могли обратить внимание, что внешний вид экрана в приложении отличается от того, который мы получили в результате выполнения теста. Проблема заключается в использовании [стилей]( https://developer.android.com/develop/ui/views/theming/themes). Во время теста под капотом создается активити, которая является контейнером для нашего фрагмента. Стиль этой активити может отличаться от того, который используется у нас в приложении.

Данная проблема решается очень просто – в качестве параметра в метод `launchFragmentInContainer` можно передать стиль, который должен использоваться внутри фрагмента, его можно найти в манифесте приложения

<img src="../images/screenshot_tests_2/style.png" alt="Style"/>

Передать этот стиль в метод `launchFragmentInContainer` можно следующим образом:

```kotlin
package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.fragment.app.testing.launchFragmentInContainer
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.screen.LoadUserScreen
import com.kaspersky.kaspresso.tutorial.user.LoadUserFragment
import com.kaspersky.kaspresso.tutorial.user.LoadUserViewModel
import com.kaspersky.kaspresso.tutorial.user.State
import com.kaspersky.kaspresso.tutorial.user.User
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class LoadUserScreenshots : DocLocScreenshotTestCase(locales = "en, fr") {

    val _state = MutableStateFlow<State>(State.Initial)
    val viewModel = mockk<LoadUserViewModel>(relaxed = true) {
        every { state } returns _state
    }

    @Test
    fun takeScreenshots() {
        LoadUserScreen {
            launchFragmentInContainer(
                themeResId = R.style.Theme_Kaspresso
            ) {
                LoadUserFragment.newTestInstance(mockedViewModel = viewModel)
            }
            _state.value = State.Initial
            captureScreenshot("Initial state")
            _state.value = State.Progress
            captureScreenshot("Progress state")
            _state.value = State.Content(user = User(name = "Test", lastName = "Test"))
            captureScreenshot("Content state")
            _state.value = State.Error
            captureScreenshot("Error state")
        }
    }
}

```

## Итог

Это был очень насыщенный урок, в котором мы научились правильно устанавливать стейты во вьюмодель, тестировать фрагменты, использовать бибилотеку Mockk для мокирования сущностей, а также дорабатывать код приложения, чтобы его можно было покрыть screenshot-тестами.

