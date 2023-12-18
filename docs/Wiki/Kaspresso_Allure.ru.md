# Поддержка Allure в Kaspresso

## Что нового
В версии **1.3.0** Kaspresso была добавлена поддержка [**allure-framework**](https://github.com/allure-framework/allure-kotlin). Теперь очень легко создавать красивые тестовые отчеты, используя фреймворки Kaspresso и Allure.

В этом выпуске семейство классов управления файлами, отвечающее за предоставление файлов для снимков экрана и журналов, было реорганизовано для лучшего использования и расширяемости. Это изменение затронуло старые классы, которые сейчас помечены как устаревшие (см. пакет com.kaspersky.kaspresso.files). Пример использования: [**CustomizedSimpleTest**](../samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/simple_tests/CustomizedSimpleTest.kt).

Также были добавлены следующие перехватчики:

1. [**VideoRecordingInterceptor**](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/interceptors/watcher/testcase/impl/video/VideoRecordingInterceptor.kt) - перехватчик записи видео.
2. [**DumpViewsInterceptor**](https://github.com/KasperskyLab/Kaspresso/tree/master/kaspresso/src/main/kotlin/com/kaspersky/kaspresso/interceptors/watcher/testcase/impl/views/DumpViewsInterceptor.kt) - перехватчик, который предоставляет XML-представление иерархии View в случае сбоя теста.

В пакете [**com.kaspersky.components.alluresupport.interceptors**](https://github.com/KasperskyLab/Kaspresso/tree/master/allure-support/src/main/kotlin/com/kaspersky/components/alluresupport/interceptors) есть специальные перехватчики Kaspresso, помогающие связать и обработать файлы для Allure-отчета.

## Как использовать
Прежде всего, добавьте следующую зависимость Gradle и Allure runner в файл gradle вашего проекта, чтобы включить модуль **allure-support** Kaspresso:
```groovy
android {
    defaultConfig {
        //...    
        testInstrumentationRunner "com.kaspersky.kaspresso.runner.KaspressoRunner"
    }
    //...
}

dependencies {
    //...
    androidTestImplementation "com.kaspersky.android-components:kaspresso-allure-support:<последняя_версия>"
}
```
Затем используйте специальную функцию [**withForcedAllureSupport**](https://github.com/KasperskyLab/Kaspresso/tree/master/allure-support/src/main/kotlin/com/kaspersky/components/alluresupport/AllureSupportKaspressoBuilder.kt) в вашем конструкторе TestCase или в вашем TestCaseRule, чтобы включить все доступные перехватчики, поддерживающие Allure:
```kotlin
class AllureSupportTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withForcedAllureSupport()
) {
    
}
```
Если вы хотите указать параметры или добавить больше перехватчиков, вы можете использовать функцию [**addAllureSupport**](https://github.com/KasperskyLab/Kaspresso/tree/master/allure-support/src/main/kotlin/com/kaspersky/components/alluresupport/AllureSupportKaspressoBuilder.kt):
```kotlin
class AllureSupportCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            videoParams = VideoParams(bitRate = 10_000_000)
            screenshotParams = ScreenshotParams(quality = 1)
        }
    ).addAllureSupport().apply {
        testRunWatcherInterceptors.apply {
            add(object : TestRunWatcherInterceptor {
                override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
                    viewHierarchyDumper.dumpAndApply("ViewHierarchy") { attachViewHierarchyToAllureReport() }
                }
            })
        }
    }
) {

}
```
Если вам не нужны все эти перехватчики, предоставляемые [**withForcedAllureSupport**](https://github.com/KasperskyLab/Kaspresso/tree/master/allure-support/src/main/kotlin/com/kaspersky/components/alluresupport/AllureSupportKaspressoBuilder.kt) и [**addAllureSupport **](../allure-support/src/main/kotlin/com/kaspersky/components/alluresupport/AllureSupportKaspressoBuilder.kt), то вы можете добавить только те перехватчики, которые вам нравятся. Но обратите внимание, что [**AllureMapperStepInterceptor.kt**](../allure-support/src/main/kotlin/com/kaspersky/components/alluresupport/interceptors/step/AllureMapperStepInterceptor.kt) является обязательным для работы службы поддержки Allure. Например, если вам не нужны видеоролики и просмотр иерархий после неудачных тестов, вы можете сделать что-то вроде:
```kotlin
class AllureSupportCustomizeTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().apply {
        stepWatcherInterceptors.addAll(
            listOf(
                ScreenshotStepInterceptor(screenshots),
                AllureMapperStepInterceptor()
            )
        )
        testRunWatcherInterceptors.addAll(
            listOf(
                DumpLogcatTestInterceptor(logcatDumper),
                ScreenshotTestInterceptor(screenshots),
            )
        )
    }
) {
...
}
```
Для просмотра, запуска и экспериментирования со всем этим функционалом вам доступен [**kaspresso-allure-support-sample**](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-allure-support-sample/src/androidTest/kotlin/com/kaspersky/kaspresso/alluresupport/sample).

## Посмотреть результат
Итак, вы добавили в свою конфигурацию Kaspresso список необходимых перехватчиков, поддерживающих Allure, и запустили тест. После завершения теста на устройстве будет создан каталог **sdcard/allure-results** со всеми обработанными файлами, которые будут включены в отчет Allure.

Этот каталог следует переместить с устройства на хост-компьютер, который будет генерировать отчет.

Например, вы можете использовать для этого команду **adb pull** на своем хосте. Допустим, вы хотите найти данные для отчета в **/Users/username/Desktop/allure-results**, поэтому вы вызываете:
```
adb pull /sdcard/allure-results /Users/username/Desktop
```
Если к вашему хосту подключено несколько устройств, вы должны указать нужный идентификатор устройства. Для просмотра списка подключенных устройств вы можете выполнить:
```
adb devices
```
Вывод будет примерно таким:
```
List of devices attached
CLCDU18508004769	device
emulator-5554	device
```
Выберите необходимое устройство и вызовите:
```
adb -s emulator-5554 pull /sdcard/allure-results /Users/username/Desktop
```
Вот и все, директория **allure-results** со всеми тестовыми ресурсами теперь находится по адресу **/Users/username/Desktop**.

Теперь мы хотим создать и просмотреть отчет. Для этого на нашей машине должен быть установлен сервер Allure. Чтобы узнать, как это сделать со всеми подробностями, следуйте [**документации Allure**](https://docs.qameta.io/allure/).

Например, чтобы установить сервер Allure на MacOS, мы можем использовать следующую команду:
```
brew install allure
```
Теперь мы готовы сгенерировать и посмотреть отчет, просто вызовите:
```
allure serve /Users/username/Desktop/allure-results
```
Затем сервер Allure создает html-страницу, представляющую отчет, и помещает ее во временный каталог в вашей системе. Вы увидите, что отчет открывается в новой вкладке вашего браузера (вкладка открывается автоматически).

Если вы хотите сохранить сгенерированный html-отчет в определенном каталоге для использования в будущем, вы можете просто вызвать:
```
allure generate -o ~/kaspresso-allure-report /Users/username/Desktop/allure-results
```
Чтобы посмотреть его, в своем браузере вы просто вызываете:
```
allure open ~/kaspresso-allure-report
```
После всех этих шагов вы увидите что-то вроде:
![](https://habrastorage.org/webt/9e/i1/ks/9ei1ks9txbqzquyk5egywvqxj6k.png)

Детали успешного теста:
![](https://habrastorage.org/webt/tq/t7/ch/tqt7chcdczrgduhoukqhx1ertfc.png)

Сведения о неудачном тесте:
![](https://habrastorage.org/webt/z_/ml/bj/z_mlbjspdd8uvkw4t3cafh6-g6k.png)

## Детали, которые вам нужно знать
По умолчанию, Kaspresso-Allure вводит дополнительные тайм-ауты, чтобы максимально гарантировать правильность видеозаписи. Эти тайм-ауты увеличивают время выполнения теста на 5 секунд.
Вы можете изменить эти значения, настроив `videoParams` в `Kaspresso.Builder`. См. пример выше.
