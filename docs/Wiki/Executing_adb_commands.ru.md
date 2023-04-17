# Выполнение команд adb

## Описание
Как вы помните из [предыдущей части, посвященной интерфейсу Device](https://kasperskylab.github.io/Kaspresso/ru/Wiki/Working_with_Android_OS/), под капотом интерфейса устройства находятся следующие сущности:
<ul>
    <li>Espresso</li>
    <li>UI Automator</li>
    <li>ADB</li>
</ul>

Внимательный читатель мог заметить, что ADB недоступен в тестах Espresso. Но используя некоторые другие фреймворки, такие как Appium, вы можете выполнять команды ADB. Поэтому мы решили добавить и эту важную функцию.<br>
Мы разработали специальный AdbServer для автотестов, чтобы компенсировать отсутствие этой функции.
Основная идея инструмента аналогична идее в Appium. Мы создали простую клиент-серверную систему, состоящую из двух частей: <br>

- Устройство, которое запускает тест, действует как клиент
- Desktop отправляет команды ADB для выполнения на устройстве.
  Также в системе используется переадресация портов, чтобы можно было организовать сокет-туннель между Устройством и Десктопом через любой вид соединения (Wi-Fi, Bluetooth, USB и т.д.).

## Использование
Алгоритм использования AdbServer:

1. Запустите Desktop-часть на своей рабочей станции. <br>
   Выполните следующую команду: `java -jar <path/to/kaspresso>/artifacts/adbserver-desktop.jar` в терминале
2. Запустите часть устройства. <br>
   Соберите и запустите [модуль adbserver-sample](https://github.com/KasperskyLab/Kaspresso/ru/tree/master/samples/adbserver-sample). Вы должны увидеть следующий экран:
   <img src="https://habrastorage.org/webt/zq/rt/ia/zqrtiaglx5a1zc4ned-qykl0t_g.png" width="432" height="768"/>

Например, введите `shell input text abc` в EditText приложения и нажмите кнопку *Выполнить*. В результате вы получите `shell input text abcabc`
в EditText, потому что команда ADB была выполнена и символы `abc` были добавлены в сфокусированный EditText. <br>
Вы можете заметить, что приложение использует класс AdbTerminal для выполнения команд ADB.

## Использование в Kaspresso
В Kaspresso мы оборачиваем AdbTerminal в специальный интерфейс AdbServer.
Экземпляр `AdbServer` доступен в области `BaseTestContext` и `BaseTestCase` со свойством `adbServer`: <br>
```kotlin
@Test
fun test() =
    run {
        step("Open Simple Screen") {
            activityTestRule.launchActivity(null)
 ======>    adbServer.performShell("input text 1")   <======

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
Также не забудьте предоставить необходимое разрешение:
```
<uses-permission android:name="android.permission.INTERNET" />
``` 

## Параметры и ведение журнала

### Desktop-часть
Вы также можете использовать несколько специальных флагов, когда запускате `adbserver-desktop.jar`. <br>
Например, `java -jar adbserver-desktop.jar -e emulator-5554,emulator-5556 -p 5041 -l VERBOSE`. <br>
Флаги:

- `e`, `--emulators` - список эмуляторов, которые могут быть захвачены `adbserver-desktop.jar` (по умолчанию `adbserver-desktop.jar` захватывает все доступные эмуляторы)
- `p`, `--port` - номер порта сервера adb (значение по умолчанию 5037)
- `l`, `--logs` - какой тип логов показывать (значение по умолчанию INFO).
  Для получения дополнительной информации вы можете запустить `java -jar adbserver-desktop.jar --help`

Рассмотрим доступные типы логов:
1. ERROR <br>
   В выводе вы увидите только сообщения об ошибках. Например,
```
ERROR 09.10.2020 11:37:19.893 рабочий стол = рабочий стол-25920 устройство = эмулятор-5554 сообщение: неверный тип сообщения...
```
Взгляните на формат журнала. Вы можете увидеть тип сообщения, дату и время, имя хоста и эмулятор, который выполняет команду, а также сообщение.

2. WARN <br>
   Печатает сообщения об ошибках и предупреждения.

3. INFO <br>
   Значение по умолчанию, предоставляет все базовые события. Например,
```
INFO 10/09/2020 11:37:04.822  desktop=Desktop-25920    message: Desktop started with arguments: emulators=[], adbServerPort=null
INFO 10/09/2020 11:37:19.859  desktop=Desktop-25920    message: New device has been found: emulator-5554. Initialize connection to the device...
INFO 10/09/2020 11:37:19.892  desktop=Desktop-25920 device=emulator-5554   message: The connection establishment to device started
INFO 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: WatchdogThread is started from Desktop to Device
INFO 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: Desktop tries to connect to the Device.
 Это может занять некоторое время, поскольку устройство может быть не готово. Возможная причина: не запущен тест kaspresso
INFO 10/09/2020 11:37:20.185  desktop=Desktop-25920 device=emulator-5554   message: The attempt to connect to Device was success
INFO 10/09/2020 11:44:47.810  desktop=Desktop-25920 device=emulator-5554   message: The received command to execute: AdbCommand(body=shell input text abc)
INFO 10/09/2020 11:44:49.115  desktop=Desktop-25920 device=emulator-5554   message: The executed command: AdbCommand(body=shell input text abc). The result: CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-25920)
```
Также Desktop печатает имя эмулятора, в котором была выполнена конкретная команда (эта информация доступна на рабочей станции и на устройстве).
Это может быть очень полезно при отладке. Взгляните на поле `serviceInfo` в конце:
```
INFO 10/09/2020 11:44:49.115  desktop=Desktop-25920 device=emulator-5554   message: The executed command: AdbCommand(body=shell input text abc). The result: CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-25920)
```

4. VERBOSE <br>
   Бывают случаи, когда вам может потребоваться отладка Desktop-части AdbServer. Поэтому существует специальный очень подробный формат — VERBOSE. <br>
   Взгляните на логи, отражающие аналогичные события, представленные выше (инициализация, подключение устройства и выполнение команды):
```
INFO 10/09/2020 11:48:16.850  desktop=Desktop-27398  tag=MainKt  method=main  message: Desktop started with arguments: emulators=[], adbServerPort=null
DEBUG 10/09/2020 11:48:16.853  desktop=Desktop-27398  tag=Desktop  method=startDevicesObserving  message: start
INFO 10/09/2020 11:48:16.913  desktop=Desktop-27398  tag=Desktop  method=startDevicesObserving  message: New device has been found: emulator-5554. Initialize connection to the device...
DEBUG 10/09/2020 11:48:16.918  desktop=Desktop-27398 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl  method=getDesktopSocketLoad  message: calculated desktop client port=21234
DEBUG 10/09/2020 11:48:16.918  desktop=Desktop-27398 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl  method=forwardPorts  message: fromPort=21234, toPort=8500 started
DEBUG 10/09/2020 11:48:16.919  desktop=Desktop-27398 device=emulator-5554 tag=CommandExecutorImpl  method=execute  message: The created adbCommand=adb -s emulator-5554 forward tcp:21234 tcp:8500
DEBUG 10/09/2020 11:48:16.925  desktop=Desktop-27398 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl  method=forwardPorts  message: fromPort=21234, toPort=8500) finished with result=CommandResult(status=SUCCESS, description=exitCode=0, message=21234
, serviceInfo=The command was executed on desktop=Desktop-27398)
DEBUG 10/09/2020 11:48:16.925  desktop=Desktop-27398 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl  method=getDesktopSocketLoad  message: desktop client port=21234 is forwarding with device server port=8500
INFO 10/09/2020 11:48:16.927  desktop=Desktop-27398 device=emulator-5554 tag=DeviceMirror  method=startConnectionToDevice  message: The connection establishment to device started
INFO 10/09/2020 11:48:16.928  desktop=Desktop-27398 device=emulator-5554 tag=DeviceMirror$WatchdogThread  method=run  message: WatchdogThread is started from Desktop to Device
DEBUG 10/09/2020 11:48:16.928  desktop=Desktop-27398 device=emulator-5554 tag=DeviceMirror$WatchdogThread  method=run  message: The attempt to connect to Device. It may take time because the device can be not ready (for example, a kaspresso test was not started).
INFO 10/09/2020 11:48:16.928  desktop=Desktop-27398 device=emulator-5554 tag=DeviceMirror$WatchdogThread  method=run  message: Desktop tries to connect to the Device.
 Это может занять некоторое время, поскольку устройство может быть не готово. Возможная причина: не запущен тест kaspresso
DEBUG 10/09/2020 11:48:16.929  desktop=Desktop-27398 device=emulator-5554 tag=ConnectionServerImplBySocket  method=tryConnect  message: Start the process
DEBUG 10/09/2020 11:48:16.929  desktop=Desktop-27398 device=emulator-5554 tag=ConnectionMaker  method=connect  message: Start a connection establishment. The current state=DISCONNECTED
DEBUG 10/09/2020 11:48:16.929  desktop=Desktop-27398 device=emulator-5554 tag=ConnectionMaker  method=connect  message: The current state=CONNECTING
DEBUG 10/09/2020 11:48:16.930  desktop=Desktop-27398 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl$getDesktopSocketLoad$1  method=invoke  message: started with ip=127.0.0.1, port=21234
DEBUG 10/09/2020 11:48:16.938  desktop=Desktop-27398 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl$getDesktopSocketLoad$1  method=invoke  message: completed with ip=127.0.0.1, port=21234
DEBUG 10/09/2020 11:48:16.941  desktop=Desktop-27398 device=emulator-5554 tag=SocketMessagesTransferring  method=prepareListening  message: Start
DEBUG 10/09/2020 11:48:16.948  desktop=Desktop-27398 device=emulator-5554 tag=SocketMessagesTransferring  method=prepareListening  message: IO Streams were created
DEBUG 10/09/2020 11:48:16.948  desktop=Desktop-27398 device=emulator-5554 tag=ConnectionMaker  method=connect  message: The connection is established. The current state=CONNECTED
DEBUG 10/09/2020 11:48:16.948  desktop=Desktop-27398 device=emulator-5554 tag=ConnectionServerImplBySocket$tryConnect$2  method=invoke  message: The connection is ready. Start messages listening
DEBUG 10/09/2020 11:48:16.949  desktop=Desktop-27398 device=emulator-5554 tag=SocketMessagesTransferring  method=startListening  message: Started
INFO 10/09/2020 11:48:16.949  desktop=Desktop-27398 device=emulator-5554 tag=DeviceMirror$WatchdogThread  method=run  message: The attempt to connect to Device was success
DEBUG 10/09/2020 11:48:16.949  desktop=Desktop-27398 device=emulator-5554 tag=SocketMessagesTransferring$MessagesListeningThread  method=run  message: Start listening
DEBUG 10/09/2020 11:48:24.132  desktop=Desktop-27398 device=emulator-5554 tag=SocketMessagesTransferring  method=peekNextMessage  message: The message=TaskMessage(command=AdbCommand(body=shell input text abc))
INFO 10/09/2020 11:48:24.132  desktop=Desktop-27398 device=emulator-5554 tag=DeviceMirror$Companion$create$connectionServerLifecycle$1  method=onReceivedTask  message: The received command to execute: AdbCommand(body=shell input text abc)
DEBUG 10/09/2020 11:48:24.132  desktop=Desktop-27398 device=emulator-5554 tag=ConnectionServerImplBySocket$handleMessages$1  method=invoke  message: Received taskMessage=TaskMessage(command=AdbCommand(body=shell input text abc))
DEBUG 10/09/2020 11:48:24.133  desktop=Desktop-27398 device=emulator-5554 tag=CommandExecutorImpl  method=execute  message: The created adbCommand=adb -s emulator-5554 shell input text abc
INFO 10/09/2020 11:48:24.389  desktop=Desktop-27398 device=emulator-5554 tag=DeviceMirror$Companion$create$connectionServerLifecycle$1  method=onExecutedTask  message: The executed command: AdbCommand(body=shell input text abc). The result: CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-27398)
DEBUG 10/09/2020 11:48:24.389  desktop=Desktop-27398 device=emulator-5554 tag=ConnectionServerImplBySocket$handleMessages$1$1  method=run  message: Result of taskMessage=TaskMessage(command=AdbCommand(body=shell input text abc)) => result=CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-27398)
DEBUG 09.10.2020 11:48:24.389 desktop=Desktop-27398 device=emulator-5554 tag=SocketMessagesTransferring method=sendMessage message: Input sendModel=ResultMessage(command=AdbCommand(body=shell input text abc), data=CommandResult( status=SUCCESS, description=exitCode=0, message=, serviceInfo=Команда была выполнена на рабочем столе=Desktop-27398))
```
Обратите внимание, что строка журнала также содержит два дополнительных поля: `tag` и `method`. Оба поля автоматически генерируются с использованием метода `Throwable().stacktrace`.

5. DEBUG <br>
   В отличие от типа VERBOSE, DEBUG упаковывает повторяющиеся фрагменты логов. Например,
```
DEBUG 10/09/2020 12:11:37.006  desktop=Desktop-30548 device=emulator-5554 tag=DeviceMirror$WatchdogThread  method=run  message: The attempt to connect to Device. It may take time because the device can be not ready (for example, a kaspresso test was not started).
DEBUG 10/09/2020 12:11:44.063  desktop=Desktop-30548 device=emulator-5554 tag=ServiceInfo  method=Start  message: ////////////////////////////////////////FRAGMENT IS REPEATED 7 TIMES////////////////////////////////////////
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ConnectionServerImplBySocket  method=tryConnect  message: Start the process
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ConnectionMaker  method=connect  message: Start a connection establishment. The current state=DISCONNECTED
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ConnectionMaker  method=connect  message: The current state=CONNECTING
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl$getDesktopSocketLoad$1  method=invoke  message: started with ip=127.0.0.1, port=37110
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=DesktopDeviceSocketConnectionForwardImpl$getDesktopSocketLoad$1  method=invoke  message: completed with ip=127.0.0.1, port=37110
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=SocketMessagesTransferring  method=prepareListening  message: Start
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ConnectionMaker  method=connect  message: The connection establishment process failed. The current state=DISCONNECTED
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ConnectionServerImplBySocket$tryConnect$3  method=invoke  message: The connection establishment attempt failed. The most possible reason is the opposite socket is not ready yet
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=DeviceMirror$WatchdogThread  method=run  message: The attempt to connect to Device. It may take time because the device can be not ready (for example, a kaspresso test was not started).
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ServiceInfo  method=End  message: ////////////////////////////////////////////////////////////////////////////////////////////////////
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ConnectionServerImplBySocket  method=tryConnect  message: Start the process
DEBUG 10/09/2020 12:11:44.064  desktop=Desktop-30548 device=emulator-5554 tag=ConnectionMaker  method=connect  message: Start a connection establishment. The current state=DISCONNECTED
``` 

### Часть Device

В Kaspresso интерфейс AdbServer имеет реализацию по умолчанию `AdbServerImpl`. Эта реализация устанавливает уровень журнала WARN для AdbServer.
Итак, в LogCat можно увидеть такие логи: <br>
```
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: __________________________________________________________________________________________
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: TEST STEP: "1. Disable network" in DeviceNetworkSampleTest
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: AdbServer. The command to execute=su 0 svc data disable
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample W/KASPRESSO_ADBSERVER: Something went wrong (fake message)
```
Все журналы печатаются с тегом KASPRESSO_ADBSERVER с уровнем журнала WARN. <br>
Если вы хотите отладить код, вы можете установить уровень журнала `VERBOSE`:
```kotlin
class DeviceNetworkSampleTest: TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        libLogger = UiTestLoggerImpl(Kaspresso.DEFAULT_LIB_LOGGER_TAG)
        adbServer = AdbServerImpl(LogLevel.VERBOSE, libLogger)
    }
) {...}
```
Теперь логи выглядят так:
```
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: TEST STEP: "1. Disable network" in DeviceNetworkSampleTest
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: AdbServer. The command to execute=su 0 svc data disable
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO_ADBSERVER: Start to execute the command=AdbCommand(body=shell su 0 svc data disable)
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=ConnectionClientImplBySocket method=executeCommand message: Started command=AdbCommand(body=shell su 0 svc data disable)
2020-09-10 12:24:27.241 10349-10378/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=SocketMessagesTransferring method=sendMessage message: Input sendModel=TaskMessage(command=AdbCommand(body=shell su 0 svc data disable))
2020-09-10 12:24:27.427 10349-10406/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=SocketMessagesTransferring method=peekNextMessage message: The message=ResultMessage(command=AdbCommand(body=shell su 0 svc data disable), data=CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-30548))
2020-09-10 12:24:27.427 10349-10406/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=ConnectionClientImplBySocket$handleMessages$1 method=invoke message: Received resultMessage=ResultMessage(command=AdbCommand(body=shell su 0 svc data disable), data=CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-30548))
2020-09-10 12:24:27.427 10349-10378/com.kaspersky.kaspressample D/KASPRESSO_ADBSERVER: class=ConnectionClientImplBySocket method=executeCommand message: Command=AdbCommand(body=shell su 0 svc data disable) completed with commandResult=CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-30548)
2020-09-10 12:24:27.427 10349-10378/com.kaspersky.kaspressample I/KASPRESSO_ADBSERVER: The result of command=AdbCommand(body=shell su 0 svc data disable) => CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-30548)
```

## Разработка
Исходный код AdbServer доступен в модуле [adb-server](https://github.com/KasperskyLab/Kaspresso/ru/tree/master/adb-server). <br>
Если вы хотите собрать `adbserver-desktop.jar` вручную, просто выполните `./gradlew :adb-server:adbserver-desktop:assemble`.
