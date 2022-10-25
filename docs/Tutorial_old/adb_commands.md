# Выполнение команд adb в тесте Kaspresso

<br/> Kaspresso предоставляет возможность выполнять adb команды.

## Общее описание принципа работы.

<br/> Для выполнения adb команд устанавливается клиент-серверное взаимодействие между мобильным устройством (или эмулятором) и дектопом. Сперва нужно скачать файл `adbserver-desktop.jar` на официальном гитхабе [Kaspresso](https://github.com/KasperskyLab/Kaspresso/blob/master/artifacts/adbserver-desktop.jar) и выполнить следующую команду в терминале:
````Java
java -jar <path/to/file>/adbserver-desktop.jar
````
<br/> Запустится AdbServer - реализация десктопной части взаимодействия. При запуске теста девайс сообщит десктопу необходимые для выполнения теста adb команды.

## Пример теста с использованием adb

<br/> Ниже приведен пример теста, который выполняет 3 различные команды и проверяет полученные результаты.


```` Kotlin

class AdbTest : TestCase() {

    @Test
    fun serverSampleTest() {
        run {

            step("Execute command on host") {
                val result = adbServer.performCmd("hostname")
                Assert.assertTrue(result.isNotEmpty())
            }

            step("Execute ADB command") {
                val command = "undefined_command"

                try {
                    adbServer.performAdb(command)
                } catch (ex: AdbServerException) {
                    Assert.assertTrue("unknown command $command" in ex.message)
                }
            }

            step("Execute ADB Shell command") {
                val command = "pm list packages"

                val result = adbServer.performShell(command)
                Assert.assertTrue("package:${device.targetContext.packageName}" in result.first())
            }
        }
    }
}
````
<br/> В Kaspresso для работы с adb есть специальная абстракция `AdbServer`. Экземлпяр этого класса доступен в `BaseTestContext` и в `BaseTestCase`. На примере теста `AdbTest` показана возможность выполнения трех видов команд: `cmd`, `adb` и `adb shell`.
> Для успешного выполнения этого теста потребуется разрешение на Интернет и запущенный AdbServer

<br/>
## Разные сценарии запуска AdbServer

<br/> AdbServer можно запускать с разными флагами. Например, так: 
````java
java -jar adbserver-desktop.jar -e emulator-5554,emulator-5556 -p 5041 -l VERBOSE
````
<br/>Рассмотрим флаги:
<br/> `e, --emulators` — список эмуляторов, которые может захватить adbserver-desktop.jar (по умолчанию adbserver-desktop.jar захватывает все доступные эмуляторы)
<br/> `p, --port` - номер порта adb сервера (значение по умолчанию 5037)
<br/> `l, --logs` - какой тип логов показывать (значение по умолчанию INFO). 

<br/> Для получения дополнительной информации вы можете запустить `java -jar adbserver-desktop.jar --help`

### Различные режимы логирования:
<br/> 1. ERROR - в выводе будут только сообщения об ошибке.
```` kotlin
ERROR 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: Incorrect type of the message...
````

<br/> 2. WARN - в выводе будут отображаться предупреждения и сообщения об ошибке.

<br/> 3. INFO (значение по умолчанию) - в выводе будут отображаться все базовые сообщения.
```` kotlin
INFO 10/09/2020 11:37:04.822  desktop=Desktop-25920    message: Desktop started with arguments: emulators=[], adbServerPort=null
INFO 10/09/2020 11:37:19.859  desktop=Desktop-25920    message: New device has been found: emulator-5554. Initialize connection to the device...
INFO 10/09/2020 11:37:19.892  desktop=Desktop-25920 device=emulator-5554   message: The connection establishment to device started
INFO 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: WatchdogThread is started from Desktop to Device
INFO 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: Desktop tries to connect to the Device.
 It may take time because the device can be not ready. Possible reason: a kaspresso test has not been started
INFO 10/09/2020 11:37:20.185  desktop=Desktop-25920 device=emulator-5554   message: The attempt to connect to Device was success
INFO 10/09/2020 11:44:47.810  desktop=Desktop-25920 device=emulator-5554   message: The received command to execute: AdbCommand(body=shell input text abc)
INFO 10/09/2020 11:44:49.115  desktop=Desktop-25920 device=emulator-5554   message: The executed command: AdbCommand(body=shell input text abc). The result: CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-25920)
````

<br/> 4. VERBOSE - специальный очень подробный формат (используется для отладки Desktop-части AdbServer). Взгляните на логи, отражающие аналогичные события, представленные выше (инициализация, подключение устройства и выполнение команды).
```` kotlin
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
 It may take time because the device can be not ready. Possible reason: a kaspresso test has not been started
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
DEBUG 10/09/2020 11:48:24.389  desktop=Desktop-27398 device=emulator-5554 tag=SocketMessagesTransferring  method=sendMessage  message: Input sendModel=ResultMessage(command=AdbCommand(body=shell input text abc), data=CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-27398))
````

<br/> 5. DEBUG - такой же подробный, как VERBOSE, но не содержит повторяющихся строк (объединяет в одну с указанием количества повторений).
```` kotlin
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
````
