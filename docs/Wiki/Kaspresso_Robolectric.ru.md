# Запуск тестов Kaspresso на JVM с помощью Robolectric

## Основная цель

Начиная с Robolectric 4.0, мы также можем запускать тесты, подобные Espresso, также на JVM с помощью Robolectric.
Это часть [проекта Nitrogen от Google](https://www.youtube.com/watch?v=-_kZC29sWAo) (стала унифицированной тестовой платформой), с помощью которой разработчики могут один раз написать тест пользовательского интерфейса и запускать их везде.

Однако до Kaspresso 1.3.0, если вы пытались запустить тест, подобный Kaspresso, расширяющий TestCase на JVM с помощью Robolectric, вы получали следующую ошибку:
```
java.lang.NullPointerException
	at androidx.test.uiautomator.QueryController.<init>(QueryController.java:95)
	at androidx.test.uiautomator.UiDevice.<init>(UiDevice.java:109)
	at androidx.test.uiautomator.UiDevice.getInstance(UiDevice.java:261)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder.<init>(Kaspresso.kt:297)
	at com.kaspersky.kaspresso.kaspresso.Kaspresso$Builder$Companion.simple(Kaspresso.kt:215)
	...
```
Это потому, что Robolectric совместим с Espresso, но не совместим с UI Automator.

Теперь все тесты Kaspresso могут корректно выполняться на JVM с Robolectric со следующими ограничениями:

1. Простая настройка вашего проекта в соответствии с [руководством Robolectric](http://robolectric.org/blog/2018/10/25/robolectric-4-0/).
2. Невозможно использовать adb-сервер, потому что в среде JVM нет такого термина, как «Рабочий стол». Тесты, использующие adb-server, будут падать на JVM с Robolectric с поясняющим сообщением об ошибке.
3. Невозможно работать с классами `UiDevice` и `UiAutomation`. Вот почему многие (не все!) реализации в `Device` будут падать на JVM с Robolectric с `NotSupportedInstrumentalTestException`.
4. Нерабочий Kautomator. Упомянутая проблема с классами `UiDevice` и `UiAutomation` затрагивает весь Kautomator. Таким образом, тесты с использованием Kaautomator будут аварийно завершать работу на JVM с Robolectric с `KautomatorInUnitTestException`.
5. Перехватчики, использующие UiDevice, UiAutomation или adb-server, автоматически отключаются на JVM с Robolectric.
6. `DocLocScreenshotTestCase` будет аварийно завершать работу на JVM с Robolectric с `DocLocInUnitTestException`.

## Использование
Чтобы создать тест, который может работать на устройстве/эмуляторе и на JVM, мы рекомендуем создать папку `sharedTest` и соответствующим образом настроить `sourceSets` в gradle.

```kotlin
sourceSets {
   ...
   //настраиваем общую тестовую папку
   val sharedTestFolder = "src/sharedTest/kotlin"
   val androidTest by getting {
       java.srcDirs("src/androidTest/java", sharedTestFolder)
   }
   val test by getting {
       java.srcDirs("src/test/java", sharedTestFolder)
   }
}
```

Также важно, чтобы такие тесты использовали ``@RunWith(AndroidJUnit4::class)``, так как это требуется Robolectric.

Чтобы запустить ваши общие тесты как модульные тесты на JVM, вам нужно запустить команду, выглядящую следующим образом:
```
./gradlew :MODULE:testVARIANTUnitTest --info --tests "PACKAGE.CLASS"
```

Например, чтобы запустить RobolectricTest на JVM, вам нужно выполнить:
```
./gradlew :samples:kaspresso-sample:testDebugUnitTest --info --tests "com.kaspersky.kaspressample.sharedtest.SharedSimpleFlakyTest"
```

Чтобы запустить их на устройстве/эмуляторе, команда для запуска будет выглядеть так:
```
./gradlew :MODULE:connectedVARIANTAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=PACKAGE.CLASS
```

Например, чтобы запустить SharedTest на устройстве/эмуляторе, вам нужно выполнить:
```
./gradlew :samples:kaspresso-sample:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.kaspersky.kaspressample.sharedtest.SharedSimpleFlakyTest
```

## Адаптация тестов для работы в среде JVM (с Robolectric)

Мы подготовили набор инструментов и советов, чтобы приспособить ваши тесты к среде JVM (с Robolectric).

Рассмотрим наиболее популярную проблему, когда в тесте используется класс, содержащий вызовы `UiDevice`/`UiAutomation`/`AdbServer` или другие не работающие в среде JVM вещи.

Например, ваш тест выглядит следующим образом:
```kotlin
@RunWith(AndroidJUnit4::class)
class FailingSharedTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test
    fun exploitSampleTest() =
        run {
            step("Press Home button") {
                device.exploit.pressHome()
            }
            //...
        }
}
```

`device.exploit.pressHome()` вызывает `UiDevice` под капотом, что приводит к сбою среды JVM.

Существует следующее возможное решение:
``` kotlin
// изменить реализацию класса Exploit
@RunWith(AndroidJUnit4::class)
class FailingSharedTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        exploit = 
            if (isAndroidRuntime) ExploitImpl() // старая реализация
            else ExploitUnit() // новая реализация без UiDevice
    }
) { ... }

// свойство isAndroidRuntime доступно в Kaspresso.Builder.
``` 

Кроме того, если ваш пользовательский перехватчик использует `UiDevice`/`UiAutomation`/`AdbServer`, вы можете отключить этот перехватчик для JVM. Пример:
```kotlin
class KaspressoConfiguringTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = if (isAndroidRuntime) mutableListOf(
           YourCustomInterceptor(),
           FlakySafeViewBehaviorInterceptor (flakySafetyParams, libLogger)
       ) else mutableListOf(
           FlakySafeViewBehaviorInterceptor (flakySafetyParams, libLogger)
       )
    }
) { ... }
``` 

Конечно, есть очень очевидный последний вариант. Просто не включайте тест в набор модульных тестов.

**Дополнительные замечания**

Начиная с Robolectric 4.8.1, у sharedTest есть некоторые ограничения: эти тесты работают безупречно на эмуляторе/устройстве, но не работают на JVM. 

1. Robolectric-Espresso поддерживает Idling, но [не поддерживает публикацию отложенных сообщений в Looper](https://github.com/robolectric/robolectric/issues/4807#issuecomment-1075863097)
2. Robolectric-Espresso не будет поддерживать [тесты, которые запускают новые activity](https://github.com/robolectric/robolectric/issues/5104)
