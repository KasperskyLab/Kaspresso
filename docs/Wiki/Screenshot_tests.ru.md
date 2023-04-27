# Скриншот тесты

## Основная цель

Иногда при разработке новых функций возникает необходимость проверить, корректно ли работает приложение на всех поддерживаемых языках. Ручное изменение настроек локали может занять много времени и потребовать усилий разработчиков, QA-инженеров и т. д. Кроме того, это может увеличить продолжительность процесса локализации.

Чтобы избежать этого, Kaspresso предоставляет класс ```DocLocScreenshotTestCase```, что позволяет делать скриншоты во всех указанных вами локалях. `DocLocScreenshotTestCase` расширяется класс `TestCase` и предлагает возможность делать скриншоты из коробки, вызывая метод `DocLocScreenshotTestCase#captureScreenshot(String)`.

## Использование

Чтобы создать скриншот тест, вы должны расширить класс `DocLocScreenshotTestCase`, как показано ниже:

```kotlin
@RunWith(AndroidJUnit4::class)
class ScreenshotSampleTest : DocLocScreenshotTestCase(
    locales = "en,ru"
) {

    @ScreenShooterTest
    @Test
    fun test() {
        before{
        }.after {
        }.run {
            
            step("1. Do the first step") {
                // ...
                captureScreenshot("First step")
            } 
            
            step("2. Do the second step") {
                // ... 
                captureScreenshot("Second step")
            }
        }
    }        
}
```

В базовый конструктор передается один параметр: `locales` - строка с разделенными запятыми локалями для запуска теста. Сделанные скриншоты будут доступны в памяти устройства по пути `/sdcard/screenshots/`.

Полный пример см. в [ScreenshotSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/1c1fc42f704ea6baa08e5c0e4e0269ded9243986/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/ScreenshotSampleTest.kt).

Обратите внимание, что тест помечен аннотацией `@ScreenShooterTest`. Эта аннотация предназначена для фильтрации скриншот тестов от всех остальных для запуска. Например, вы можете передать эту аннотацию стандартному `AndroidJUnitRunner` при помощи команды:

```
adb shell am instrument -w -e annotation com.kaspersky.kaspresso.annotations.ScreenShooterTest your.package.name/android.support.test.runner.AndroidJUnitRunner
```

## Расположение файлов скриншотов

Все файлы снимков экрана хранятся по умолчанию в каталоге `screenshots`.
Они отсортированы по локали и названию теста:

`<base directory>/<test class canonical name>/<locale>/<your tag>.png`

Для тестового кейса из примера дерево файлов должно выглядеть так:

    - screenshots
        - com.kaspersky.kaspressample.tests.docloc.ScreenshotSampleTest
            - en
                // файлы скриншотов
            - ru
                // файлы скриншотов

Итак, для сохранения скриншотов на внешнее хранилище тестовому приложению требуется разрешение `android.permission.WRITE_EXTERNAL_STORAGE`.

## Дополнительная метаинформация скриншота

Когда разработчик вызывает метод  `captureScreenshot("la-la-la")` , Kaspresso создает не только снимок экрана, но и специальный xml-файл. Этот xml-файл содержит данные обо всех элементах пользовательского интерфейса с их идентификаторами, расположенными на экране. Пример:
```
<Metadata>
    <Window Left="0" Top="0" Width="1440" Height="2560">
        <LocalizedString Text="Simple Fragment" LocValueDescription="com.kaspersky.kaspressample.test:id/text_view_title" Top="140" Left="307" Width="825" Height="146"/>
        <LocalizedString Text="Button 1" LocValueDescription="com.kaspersky.kaspressample.test:id/button_1" Top="370" Left="84" Width="1272" Height="168"/>
        <LocalizedString Text="Button 2" LocValueDescription="com.kaspersky.kaspressample.test:id/button_2" Top="622" Left="84" Width="1272" Height="168"/>
        <LocalizedString Text="Kaspresso" LocValueDescription="com.kaspersky.kaspressample.test:id/edit" Top="874" Left="84" Width="1272" Height="158"/>
        <LocalizedString Text="Simple screen" LocValueDescription="com.kaspersky.kaspressample.test:id/[id:ffffffff]" Top="51" Left="56" Width="446" Height="93"/>
    </Window>
</Metadata>
```
Подобные данные могут быть полезны для разных систем, автоматизирующих процесс локализации приложения. Система автоматизации сохраняет файл xml для каждого экрана и сравнивает его с новыми версиями, полученными при прогонах новых скриншотов. При обнаружении каких-либо отличий система дает сигнал подготовить и отправить порцию новых слов на сервер перевода.

## Скриншоты системных диалогов/окон

Иногда вам нужно сделать скриншоты системных диалогов или окон. Вот почему вы должны изменить язык для всей системы. Для этого в конструкторе `DocLocScreenshotTestCase` есть дополнительный параметр - `changeSystemLocale`. Обратите внимание на то, что `changeSystemLocale`, определенный в true, требует системного разрешения `Manifest.permission.CHANGE_CONFIGURATION`. <br>
Взгляните на код ниже:
```kotlin
@RunWith(AndroidJUnit4::class)
class ChangeSysLanguageTestCase : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,ru",
    changeSystemLocale = true
) {

    @ScreenShooterTest
    @Test
    fun test() {
        before{
        }.after {
        }.run {
            
            step("1. Do the first step") {
                // ...
                captureScreenshot("First step")
            } 
            
            step("2. Do the second step") {
                // ... 
                captureScreenshot("Second step")
            }
        }
    }        
}
```
Полный пример находится по адресу [ChangeSysLanguageTestCase](https://github.com/KasperskyLab/Kaspresso/blob/1c1fc42f704ea6baa08e5c0e4e0269ded9243986/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/ChangeSysLanguageTestCase.kt).

## Расширенное использование

В большинстве случаев нет необходимости запускать какую-то Activity, делать много шагов, прежде чем добраться до необходимого функционала. Часто показа фрагментов будет достаточно, чтобы сделать нужные скриншоты.
Кроме того, когда вы используете архитектурный шаблон Model-View-Presenter, вы можете управлять состоянием пользовательского интерфейса непосредственно через интерфейс View. Таким образом, нет необходимости взаимодействовать с интерфейсом приложения и ждать изменений.

Сначала создайте базовую тестовую Activity с методом `setFragment(Fragment)` в вашем приложении:

```kotlin
class FragmentTestActivity : AppCompatActivity() {

    fun setFragment(fragment: Fragment) = with(supportFragmentManager.beginTransaction()) {
        replace(android.R.id.content, fragment)
        commit()
    }
}
```

Затем добавьте тестовый пример скриншота базового продукта:

 ```kotlin
open class ProductDocLocScreenshotTestCase : DocLocScreenshotTestCase(
    locales = "en,ru"
) {

    @get:Rule
    val activityTestRule = ActivityTestRule(FragmentTestActivity::class.java, false, true)

    protected val activity: FragmentTestActivity
        get() = activityTestRule.activity

}
```  

Этот тестовый пример будет запускать вашу `FragmentTestActivity` при запуске. Теперь вы можете писать тесты для скриншотов.
Например, создайте новый тестовый класс, который расширяет `ProductDocLocScreenshotTestCase`:

```kotlin
@RunWith(AndroidJUnit4::class)
class AdvancedScreenshotSampleTest : ProductDocLocScreenshotTestCase() {

    private lateinit var fragment: FeatureFragment
    private lateinit var view: FeatureView

    @ScreenShooterTest
    @Test
    fun test() {
        before {
            fragment = FeatureFragment()
            view = getUiSafeProxy(fragment as FeatureView)
            activity.setFragment(fragment)
        }.after {
        }.run {

            step("1. Step 1") {
                // ... [view] calls
                captureScreenshot("Step 1")
            }

            step("2. Step 2") {
                // ... [view] calls
                captureScreenshot("Step 2")
            }

            step("3. Step 3") {
                // ... [view] calls
                captureScreenshot("Step 3")
            }

            // ... другие шаги
        }
    }
}
```

Как вы могли заметить, метод `getUiSafeProxy` вызывается для получения экземпляра `FeatureView`.
Этот метод обертывает ваш интерфейс View и возвращает на него прокси.
Прокси гарантирует, что все методы интерфейса `View`, которые вы вызвали, будут вызываться в основном потоке.
Существует также `getUiSafeProxyFromImplementation`, который оборачивает реализацию, а не интерфейс.

Полный пример см. в классе [AdvancedScreenshotSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/advanced/AdvancedScreenshotSampleTest.kt).

## Изменение пути и имени скриншотов

По умолчанию все скриншоты хранятся по адресу: <br>
```/sdcard/screenshots/<locale>/<full qualified test class name>/<method name>.``` <br>
Вы можете изменить это поведение, предоставив свою реализацию интерфейсов
[ResourcesRootDirsProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourcesRootDirsProvider.kt),
[ResourcesDirsProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourcesDirsProvider.kt),
[ResourceFileNamesProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourceFileNamesProvider.kt) и [ResourcesDirNameProvider](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/files/resources/ResourcesDirNameProvider.kt).
Узнайте подробности [здесь](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/docloc_tests/customdirectory/CustomDirectoryScreenshotSampleTest.kt).

## Изменения

Мы были вынуждены изменить нашу систему предоставления ресурсов для поддержки Allure.
Изменения затронули основной конструктор [DocLocScreenshotTestCase](https://github.com/KasperskyLab/Kaspresso/blob/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/testcases/api/testcase/DocLocScreenshotTestCase.kt).
Но мы сохранили старый вариант использования `DocLocScreenshotTestCase` со старой системой предоставления ресурсов в качестве вторичного конструктора.
Вы можете просмотреть вторичный конструктор как пример миграции со старой системы на новую.
Кроме того, мы сохранили тесты с использованием старой системы предоставления ресурсов в примерах, чтобы убедиться, что ничего не сломано.

