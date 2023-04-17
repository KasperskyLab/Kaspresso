# Взаимодействие с ОС Android. Класс `Device`.

**Device** — это поставщик менеджеров для всей работы вне приложения.

### Структура

Все примеры находятся в [device_tests](https://github.com/KasperskyLab/Kaspresso/tree/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests).
Класс **Device** содержит следующие свойства:

1. `apps` позволяет устанавливать или удалять приложения. Использует команды `adb install` и `adb uninstall`. См. пример [DeviceAppSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceAppSampleTest.kt).
2. `activities` — это интерфейс для работы с отображаемыми в данный момент Activity. AdbServer не требуется. См. пример [DeviceActivitiesSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceActivitiesSampleTest.kt).
3. `files` обеспечивает возможность загрузки или удаления файлов с устройства. Использует команды `adb push` и `adb rm` и не требует разрешения `android.permission.WRITE_EXTERNAL_STORAGE`. См. пример [DeviceFilesSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceFilesSampleTest.kt).
4. `internet` позволяет переключать настройки Wi-Fi и передачи данных по сети. Будьте осторожны при использовании этого интерфейса, изменения настроек Wi-Fi могут не работать с некоторыми версиями Android. См. пример [DeviceNetworkSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceNetworkSampleTest.kt).
5. `keyboard` — это интерфейс для отправки событий клавиатуры через adb. Используйте его только тогда, когда Espresso или UiAutomator не подходят (например, экран заблокирован). См. пример [DeviceKeyboardSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceKeyboardSampleTest.kt).
6. `location` имитирует поддельное местоположение и позволяет переключать настройки GPS. См. пример [DeviceLocationSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceLocationSampleTest.kt).
7. `phone` позволяет эмулировать входящие звонки и принимать SMS-сообщения. Работает только на эмуляторах, так как использует команды `adb emu`. См. пример [DevicePhoneSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DevicePhoneSampleTest.kt).
8. `screenshots` — интерфейс для скриншотов пользовательского интерфейса. Требуется разрешение `android.permission.WRITE_EXTERNAL_STORAGE`. См. пример [DeviceScreenshotSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceScreenshotSampleTest.kt).
9. `accessibility` - позволяет включать или отключать специальные возможности. Доступно с API 24. См. пример [DeviceAccessibilitySampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceAccessibilitySampleTest.kt).
10. `permissions` - предоставляет возможность выдавать или отклонять запросы разрешений через диалоговое окно разрешений Android по умолчанию. См. пример [DevicePermissionsSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DevicePermissionsSampleTest.kt).
11. `hackPermissions` предоставляет возможность выдавать любые разрешения без системного диалога Android по умолчанию. См. пример [DeviceHackPermissionsSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceHackPermissionsSampleTest.kt).
12. `exploit` позволяет менять ориентацию устройства или нажимать системные кнопки. См. пример [DeviceExploitSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceExploitSampleTest.kt).
13. `language` позволяет переключать язык. См. пример [DeviceLanguageSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceLanguageSampleTest.kt).
14. `logcat` обеспечивает доступ к adb logcat. См. пример [DeviceLogcatSampleTest](https://github.com/KasperskyLab/Kaspresso/blob/master/samples/kaspresso-sample/src/androidTest/kotlin/com/kaspersky/kaspressample/device_tests/DeviceLogcatSampleTest.kt). <br>
    Назначение `logcat`: <br>
    Если вы не слышали о [GDPR](https://gdpr-info.eu/) и [громких судебных процессах](https://www.theverge.com/2019/1/21/18191591/google-gdpr-fine-50-million-euro-data-consent-cnil) то вам повезло. Но, если ваше приложение работает в Европе, то очень важно включить/отключить всю аналитику/статистику согласно принятым соглашениям.
    Один из самых надежных способов проверить отправку аналитики/статистики — это проверить logcat, в котором все аналитики/статистика пишут свои журналы (конечно, в режиме отладки).
    Вот почему мы создали специальный класс `Logcat`, предоставляющий множество способов проверки logcat.
15. `uiDevice` возвращает экземпляр ```android.support.test.uiautomator.UiDevice```. Мы не рекомендуем использовать его напрямую, потому что есть **Kautomator**, который предлагает более читаемый, предсказуемый и стабильный API для работы вне вашего приложения.

Также **Device** предоставляет контексты приложений и тестов — `targetContext` и `context`.

### Использование

Экземпляр `Device` доступен в области `BaseTestContext` и `BaseTestCase` через свойство `device`.
```kotlin
@Test
fun test() =
    run {
        step("Open Simple Screen") {
            activityTestRule.launchActivity(null)
  ======>   device.screenshots.take("Additional_screenshot")  <======

            mainScreen {
                simpleButton {
                    isVisible()
                    click()
                }
            }
        }
        // ....
}
```

### Ограничения

Большинство функций, которые предоставляет **Device**, используют команды adb и требуют запуска AdbServer.
Некоторые из них, такие как эмуляция звонков или прием СМС, могли выполняться только на эмуляторе. Все такие методы отмечены аннотацией ```@RequiresAdbServer```.

Все методы, использующие команды ADB, требуют разрешения `android.permission.INTERNET`.
Для получения дополнительной информации смотрите документацию [AdbServer](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Executing_adb_commands/).
