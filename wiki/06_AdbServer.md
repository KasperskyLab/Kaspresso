# Autotest AdbServer

## What is it?
As you remember from the [previous part devoted to Device interface](./05_Device.md) Device uses the following things under the hood:
1. Espresso
2. UI Automator
3. ADB

Yep, an attentive reader may notice that ADB is not enabled in Espresso tests! But Appium can. That’s really bad news.
That's why we've written special Autotest AdbServer to compensate for the Espresso and UI Automator disadvantage. <br>
The main idea of the tool is so similar with the idea of Appium. We just have to build a simple client-server system 
where the client is a device running a test and the server is a desktop giving the command to execute tests on the device.
Also, we use a port forwarding to be able to organise a socket tunnel between a device and a desktop through any kind of connection (wi-fi, bluetooth, usb and etc.).

## Usage
The algorithm how to use Autotest AdbServer:
1. Run the Desktop part. <br>
In cmd, execute the following command: `java -jar <path-to-kaspresso>/artifacts/adbserver-desktop.jar`
2. Run the Device part. <br>
Build and start [adbserver-sample module](../adb-server/adbserver-sample). You will see the following screen:
<img src="https://habrastorage.org/webt/zq/rt/ia/zqrtiaglx5a1zc4ned-qykl0t_g.png" width="432" height="768"/>
For example, you can input `shell input text abc` in the EditText. As a result you will get `shell input text abcabc` 
in your EditText because ADB has executed the command and has added symbols `abc` into the focused EditText. <br>
You may notice that we use `AdbTerminal` in [adbserver-sample module](../adb-server/adbserver-sample) to execute ADB commands.

## Usage in Kaspresso
In Kaspresso, we wrap `AdbTerminal` into a special interface `AdbServer`.
**AdbServer** instance is available in `BaseTestContext` scope and `BaseTestCase` via `adbServer` property: <br>
```kotlin
@Test
fun test() =
    run {
        step("Open Simple Screen") {
            activityTestRule.launchActivity(null)
 ======>    adbServer.performShell("input text 1")   <======

            MainScreen {
                simpleButton {
                    isVisible()
                    click()
                }
            }
        }
        // ....
}
```
Please, don't forget to give a permission:
```gradle
<uses-permission android:name="android.permission.INTERNET" />
``` 

## Options and Logging

### Desktop part
A developer can use several special flags when he starts `adbserver-desktop.jar`. <br>
For example, the possible command: `java -jar adbserver-desktop.jar -e emulator-5554,emulator-5556 -p 5041 -l VERBOSE`. <br>
Flags:
- `e`, `--emulators` - the list of emulators that can be captured by `adbserver-desktop.jar` (by default, `adbserver-desktop.jar` captures all available emulators)
- `p`, `--port` - the adb server port number (the default value is 5037)
- `l`, `--logs` - what type of logs show (the default value is INFO).

Let's consider available types of logs:
1. ERROR <br>
You will see only error messages. The example:
```
ERROR 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: Incorrect type of the message...
```
Please, have a look at the log format. You can observe the type of a message, date and time, the desktop executing the message and the emulator giving the task, and the message.

2. WARN <br>
You will see error and warning messages.

3. INFO <br>
The default value. Such type provides all base events. The example:
```
INFO 10/09/2020 11:37:04.822  desktop=Desktop-25920    message: Desktop started with arguments: emulators=[], adbServerPort=null
INFO 10/09/2020 11:37:19.859  desktop=Desktop-25920    message: New device has been found: emulator-5554. Initialize connection to the device...
INFO 10/09/2020 11:37:19.892  desktop=Desktop-25920 device=emulator-5554   message: The connection establishment to device started
INFO 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: WatchdogThread is started from Desktop to Device
INFO 10/09/2020 11:37:19.893  desktop=Desktop-25920 device=emulator-5554   message: Desktop tries to connect to the Device.
 It may take time because the device can be not ready. Possible reason: a kaspresso test has not been started
INFO 10/09/2020 11:37:20.185  desktop=Desktop-25920 device=emulator-5554   message: The attempt to connect to Device was success
INFO 10/09/2020 11:44:47.810  desktop=Desktop-25920 device=emulator-5554   message: The received command to execute: AdbCommand(body=shell input text abc)
INFO 10/09/2020 11:44:49.115  desktop=Desktop-25920 device=emulator-5554   message: The executed command: AdbCommand(body=shell input text abc). The result: CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-25920)
```
Also, the Desktop writes where the concrete command was executed (the information is available on the Desktop and on the Device). 
It could be very useful in debugging. Have a look at the field `serviceInfo` at the end:
```
INFO 10/09/2020 11:44:49.115  desktop=Desktop-25920 device=emulator-5554   message: The executed command: AdbCommand(body=shell input text abc). The result: CommandResult(status=SUCCESS, description=exitCode=0, message=, serviceInfo=The command was executed on desktop=Desktop-25920)
```

4. VERBOSE <br>
There are cases when a developer wants to debug Desktop part of Autotest AdbServer. That's why there is a special very detailed format - VERBOSE mode. <br>
Have a glance at logs reflecting similar events presented above (the initialising, the device connecting and the executing of one command):
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
```
Pay attention that a log row now contains two additional fields: `tag` and `method`. Both fields are autogenerated using `Throwable().stacktrace` method. 
Be aware of mentioned fact.

5. DEBUG <br>
It's a VERBOSE type but DEBUG can pack repeating pieces of logs. For example:
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

### Device part

In Kaspresso, the special interface `AdbServer` has a default implementation `AdbServerImpl`. This implementation set `WARN` log level for Autotest AdbServer.
So, you can observe such logs in any kaspresso test: <br>
```
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: ___________________________________________________________________________
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: TEST STEP: "1. Disable network" in DeviceNetworkSampleTest
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample I/KASPRESSO: AdbServer. The command to execute=su 0 svc data disable
2020-09-10 12:24:27.240 10349-10378/com.kaspersky.kaspressample W/KASPRESSO_ADBSERVER: Something went wrong (fake message)
```
Tag `KASPRESSO_ADBSERVER` describes logs from Autotest AdbServer with `WARN` log level. <br>
If you desire to debug the internals of Autotest AdbServer (the device part) then you can set `VERBOSE` log level:
```kotlin
class DeviceNetworkSampleTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        libLogger = UiTestLoggerImpl(Kaspresso.DEFAULT_LIB_LOGGER_TAG)
        adbServer = AdbServerImpl(LogLevel.VERBOSE, libLogger)
    }
) {...}
```
The logs now:
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

## Development
All details about the Autotest AdbServer code are available in [adb-server module](../adb-server). <br>
If you want to build `adbserver-desktop.jar` then just execute `////`.
