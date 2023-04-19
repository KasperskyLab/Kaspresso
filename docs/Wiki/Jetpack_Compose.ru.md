# Поддержка Compose в Kaspresso
Поддержка Jetpack Compose состоит из двух частей: [библиотека Kakao Compose](https://github.com/KakaoCup/Compose) и механизм Kaspresso Interceptors.

## Библиотека Kakao Compose
Вся подробная информация доступна в [README](https://github.com/KakaoCup/Compose) библиотеки.

Поддержка Jetpack Compose обеспечивается отдельным модулем, чтобы не заставлять разработчиков обновлять версию minSDK до 21.

Итак, прежде всего, добавьте зависимость в build.gradle:
```groovy
dependencies {
    androidTestImplementation "com.kaspersky.android-components:kaspresso-compose-support:<latest_version>"
}
```

Вкратце, давайте посмотрим, как выглядит Kakao Compose DSL:
```kotlin
// Screen class
class ComposeMainScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ComposeMainScreen>(
        semanticsProvider = semanticsProvider,
        // Экран в Kakao Compose тоже может быть Node-ой из-за параметра viewBuilderAction.
        // Параметр 'viewBuilderAction' может принимать значение NULL.
        viewBuilderAction = { hasTestTag ("ComposeMainScreen") }
) {

    // Вы можете установить четкие отношения родитель-потомок благодаря расширению 'child'
    // Здесь 'simpleFlakyButton' является дочерним элементом 'ComposeMainScreen' (это тоже Node)
    val simpleFlakyButton: KNode = child {
        hasTestTag("main_screen_simple_flaky_button")
    }
}

// Эта аннотация предназначена для того, чтобы тест подходил для среды JVM (с Robolectric)
@RunWith(AndroidJUnit4::class)
// Объявление тестового класса
class ComposeSimpleFlakyTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    // Специальный класс Rule для тестов Compose
    @get:Rule
    val composeTestRule = createAndroidComposeRule<JetpackComposeActivity>()

    // Тест с DSL. Это так похоже на Kakao или Kautomator DSL.
    @Test
    fun test() = run {
        step("Open Flaky screen") {
            onComposeScreen<ComposeMainScreen>(composeTestRule) {
                simpleFlakyButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        step("Click on the First button") {
            onComposeScreen<ComposeSimpleFlakyScreen>(composeTestRule) {
                firstButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }

        // ...
    }
}
```
Опять же, вся информация, связанная с DSL, доступна в [документации](https://github.com/KakaoCup/Compose).

## Механизм Kaspresso Interceptors
Перехватчики — одно из главных преимуществ и возможностей библиотеки Kaspresso.<br>
Перечислим дефолтные перехватчики, которые по умолчанию работают под капотом, когда вы пишете тесты с Kaspresso.

### Behavior interceptors (Поведенческие перехватчики)
1. `FailureLoggingSemanticsBehaviorInterceptor`<br>
   Создайте ясное и понятное исключение в случае сбоя теста.
2. `FlakySafeSemanticsBehaviorInterceptor`<br>
   Пытается повторить неудачное действие или утверждение в течение заданного времени ожидания. Все параметры этого перехватчика находятся в `FlakySafetyParams`.
3. `SystemDialogSafetySemanticsBehaviorInterceptor`<br>
   Устраняет различные системные диалоги, мешающие корректному выполнению теста.
4. `AutoScrollSemanticsBehaviorInterceptor`<br>
   Выполняет автопрокрутку к элементу, если элемент не виден на экране.
5. `ElementLoaderSemanticsBehaviorInterceptor`<br>
   Запрашивает связанный `SemanticNodeInteraction`, используя сохраненный `Matcher`, когда элемент не найден.

### Watcher interceptors (Перехватчики-наблюдатели)
`LoggingSemanticsWatcherInterceptor`. Interceptor создает удобочитаемые журналы. Пример:
```
I/KASPRESSO: TEST STEP: "1. Open Flaky screen" in ComposeSimpleFlakyTest SUCCEED. It took 0 minutes, 0 seconds and 212 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "2. Click on the First button" in ComposeSimpleFlakyTest
I/KASPRESSO: Operation: Check=IS_DISPLAYED(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: Reloading of the element is started
I/KASPRESSO: Reloading of the element is finished
I/KASPRESSO: Repeat action again with the reloaded element
I/KASPRESSO: Operation: Check=IS_DISPLAYED(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: SemanticsNodeInteraction autoscroll successfully performed.
I/KASPRESSO: Operation: Check=IS_DISPLAYED(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: Operation: Perform=PERFORM_CLICK(description={null}).
    ComposeInteraction: matcher: (hasParentThat(TestTag = 'simple_flaky_screen_container')) && (TestTag = 'simple_flaky_screen_simple_first_button'); position: 0; useUnmergedTree: false.
I/KASPRESSO: TEST STEP: "2. Click on the First button" in ComposeSimpleFlakyTest SUCCEED. It took 0 minutes, 0 seconds and 123 millis. 
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: ___________________________________________________________________________
I/KASPRESSO: TEST STEP: "3. Click on the Second button" in ComposeSimpleFlakyTest
```

## Предостережения
Помните, что Jetpack Compose и все сопутствующие инструменты находятся в стадии разработки.
Это означает, что Jetpack Compose изучен не очень хорошо, и некоторые вещи могут быть неожиданными.
Покажу интересный случай.

Например, этот код
```kotlin
composeSimpleFlakyScreen (composeTestRule) {
    firstButton {
        performClick()
    }
}
``` 
может быть источником ненадежного поведения, если `firstButton` расположен в области, невидимой для пользователя
(вам просто нужно прокрутить, чтобы увидеть элемент).

Но, этот код всегда будет стабильно работать:
```kotlin
composeSimpleFlakyScreen (composeTestRule) {
    firstButton {
        assertIsDisplayed()
        performClick()
    }
}
``` 

Объяснение кроется в природе SemanticsNode Tree и Jetpack Compose. Элемент firstButton — это узел, представленный в дереве.
Это означает, что `performClick()` может сработать и ничего страшного не произойдет. Но `firstButton` физически не виден, и настоящий клик не происходит.
Такое поведение приводит к падению теста чуть позже.<br>
Но проверка `assertIsDisplayed()` не проходит с первой попытки (мы не видим элемент на экране) и запускает работу всех перехватчиков, включая перехватчик Autoscroll, который прокручивает экран до нужного элемента.

Пожалуйста, [поделитесь своим опытом](https://github.com/KasperskyLab/Kaspresso/issues/new), чтобы помочь другим разработчикам.

## Что еще

### Конфигурация
Поддержка Jetpack Compose полностью настраивается. Взгляните на различные параметры для настройки:
```kotlin
// Редактируем только semanticsBehaviorInterceptors
// Теперь semanticsBehaviorInterceptors содержит только FailureLoggingSemanticsBehaviorInterceptor
class ComposeCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport { composeBuilder ->
        composeBuilder.semanticsBehaviorInterceptors = composeBuilder.semanticsBehaviorInterceptors.filter {
            it is FailureLoggingSemanticsBehaviorInterceptor
        }.toMutableList()
    }
)

// Редактируем flakySafetyParams и semanticsBehaviorInterceptors
// Также мы меняем semanticsBehaviorInterceptors, исключая SystemDialogSafetySemanticsBehaviorInterceptor
class ComposeCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport(
        // Очень важно изменить flakySafetyParams в разделе настройки
        // В противном случае все перехватчики будут использовать версию flakySafetyParams по умолчанию
        customize = {
            flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 5000, intervalMs = 1000)
        },
        lateComposeCustomize = { composeBuilder ->
            composeBuilder.semanticsBehaviorInterceptors = composeBuilder.semanticsBehaviorInterceptors.filter {
                it !is SystemDialogSafetySemanticsBehaviorInterceptor
            }.toMutableList()
        }
    ).apply {
        // Помните, лучше настраивать ComposeSupport только после настройки Kaspresso
        // Поскольку перехватчики ComposeSupport могут зависеть от некоторых сущностей Kaspresso
        // Например, изменение flakySafetyParams в этом разделе не повлияет на перехватчики ComposeSupport
    }
)

// Есть еще один способ сделать то же самое
class ComposeCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        flakySafetyParams = FlakySafetyParams.custom(timeoutMs = 5000, intervalMs = 1000)
    }.apply {
        addComposeSupport { composeBuilder ->
            composeBuilder.semanticsBehaviorInterceptors = composeBuilder.semanticsBehaviorInterceptors.filter {
                it !is SystemDialogSafetySemanticsBehaviorInterceptor
            }.toMutableList()
        }
    }
)
```

### Поддержка Robolectric 
Вы можете запускать тесты Compose в среде JVM с помощью Robolectric.<br>
В качестве примера можно запустить тест ComposeSimpleFlakyTest (из модуля `kaspresso-sample`) на JVM прямо сейчас:
```
./gradlew :samples:kaspresso-compose-support-sample:testDebugUnitTest --info --tests "com.kaspersky.kaspresso.composesupport.sample.test.ComposeSimpleFlakyTest"  
```
Вся информация о поддержке Robolectric доступна [здесь](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Kaspresso_Robolectric/).

### Compose совместим со всеми расширениями Kaspresso.
Расширения Kaspresso подразумевают использование таких конструкций, как:

1. `flakySafely`
2. `continuously`

Идет поддержка некоторых конструкций: [issue-317](https://github.com/KasperskyLab/Kaspresso/issues/317).

