#Взаимодействуем с Android вне приложения

## Формулировка задачи

<img src="../images/Tutorial wifi.png" alt="Tutorial WiFi screen" width="200"/>

<br> Экран состоит из:
<br> 1. *Заголовка `TextView`*, который отображает статус WiFi на момент последней проверки
<br> 2. *Кнопки `Button`*

<br> При нажатии на кнопку текст в заголовке меняется на акутальный статус WiFi.

## Создаем Page object для текущего экрана.
<br/> Про паттерн PageObject в Kaspresso можно прочитать в [документации](https://azamatcherchesov.github.io/github_pages/Documentation/PageObject/).<br/>
<br/> В папке `androidTest` создаем объект `WiFiScreen`

```kotlin
object WiFiScreen: KScreen<WiFiScreen>() {
    override val layoutId: Int = R.layout.activity_wifi
    override val viewClass: Class<*> = WiFiActivity::class.java

    val wifiStatusTitle = KTextView { withId(R.id.wifi_status) }
    val wifiStatusButton = KButton { withId(R.id.check_wifi_btn) }
}
```

## Реализуем первый тест. Проверяем корректный статус WiFi.
<br/> При нажатии на кнопку заголовок в статусе заменится с дефолтного. Но то, на какой статус он заменится, будет зависеть от текущего статуса WiFi. Для таких случаев тест должен обладать детерминизмом - на момент старта теста состояние девайса должно соответствовать ожиданию. Хорошей практикой является восстановление состояния девайса после теста. 
<br/> Про before/after section
```` Kotlin

class WiFiTest: TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<WiFiActivity>()

    @Test
    fun check_wifi_status() = before {
        device.network.disable()
        device.network.toggleWiFi(false)
    }.after {
        device.network.enable()
        device.network.toggleWiFi(true)
    }.run {

        step("Check correct wifi status") {
            WiFiScreen {
                wifiStatusButton.click()
                wifiStatusTitle.containsText("disabled")
            }
        }
    }
}
````
## Фасадный класс Device
<br/> Для взаимодействия с операционной системой Android Kaspresso предоставляет фасадный класс Device. Device содержит много говорящих интерфейсов для взаимодействия с различными сущностями Android:


1. Apps - позволяет устанавливать или удалять приложения. Использует команды `adb install` и `adb uninstall`. См. пример DeviceAppSampleTest.

2. Activity — это интерфейс для работы с отображаемыми в данный момент Activity. AdbServer не требуется. См. пример DeviceActivitiesSampleTest.

3. Files - обеспечивает возможность отправки или удаления файлов с устройства. Использует команды `adb push` и `adb rm` и не требует разрешения `android.permission.WRITE_EXTERNAL_STORAGE`. См. пример DeviceFilesSampleTest.

4. Internet - позволяет переключать настройки Wi-Fi и передачи данных по сети. Будьте осторожны при использовании этого интерфейса, изменения настроек Wi-Fi могут не работать с некоторыми версиями Android. См. пример DeviceNetworkSampleTest.

5. Keyboard — это интерфейс для отправки ключевых событий через `adb`. Используйте его только тогда, когда Espresso или UiAutomator не подходят (например, экран заблокирован). См. пример DeviceKeyboardSampleTest.

6. Location - эмулирует поддельное местоположение и позволяет переключать настройки GPS. См. пример DeviceLocationSampleTest.

7. Telephone - позволяет эмулировать входящие вызовы и принимать SMS-сообщения. Работает только на эмуляторах, т.к. использует команды `adb emu`. См. пример DevicePhoneSampleTest.

8. Screenshots — скриншоты отображаемой в данный момент Activity. Требуется разрешение `android.permission.WRITE_EXTERNAL_STORAGE`. См. пример DeviceScreenshotSampleTest.

9. Accessibility - позволяет включать или отключать службы специальных возможностей. Доступно с API 24. См. пример DeviceAccessibilitySampleTest.
разрешения предоставляют возможность разрешать или отклонять запросы разрешений через диалоговое окно разрешений Android по умолчанию. См. пример DevicePermissionsSampleTest.

10. HackPermissions предоставляет возможность разрешать любые запросы разрешений без диалога разрешений Android по умолчанию. См. пример DeviceHackPermissionsSampleTest.

11. Exploit - позволяет вращать устройство или нажимать системные кнопки. См. пример DeviceExploitSampleTest.
язык позволяет переключать язык. См. пример DeviceLanguageSampleTest.

12. Logcat - предоставляет доступ к adb logcat. См. пример DeviceLogcatSampleTest.

13. UiDevice - возвращает экземпляр `android.support.test.uiautomator.UiDevice`. Мы не рекомендуем использовать его напрямую, потому что есть `Kaautomator`, который предлагает более читаемый, предсказуемый и стабильный API для работы вне Вашего приложения.
<br> Также Device предоставляет контексты приложений и тестов — targetContext и context.<br/>
## Реализуем второй тест. Проверяем корректный статус WiFi после поворота экрана.
```` Kotlin

    @Test
    fun check_same_wifi_status_after_rotation() = before {
        device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
        device.exploit.setAutoRotationEnabled(true)
        device.network.disable()
        device.network.toggleWiFi(false)
    }.after {
        device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
        device.exploit.setAutoRotationEnabled(true)
        device.network.enable()
        device.network.toggleWiFi(true)
    }.run {

        step("Check correct wifi status") {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
            WiFiScreen {
                wifiStatusButton.click()
                wifiStatusTitle.containsText("disabled")
            }
        }

        step("Rotate device and check wifi status") {
            device.exploit.rotate()
            Assert.assertTrue(Configuration.ORIENTATION_LANDSCAPE == device.context.resources.configuration.orientation)
            WiFiScreen.wifiStatusTitle.containsText("disabled")
        }
    }
````


