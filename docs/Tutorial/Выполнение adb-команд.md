# WORK IN PROGRESS

<br> Рассказать, что такое adb, для чего он нужен и что каспрессо умеет adb
<br> Показать тест 

```kotlin
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
```
<br> Описать, что здесь происходит, в чем разница cmd, shell и adb. 
<br> Рассказать про adbserver.jar - где взять, как можно запустить, в каких режимах
<br> Предупредить о необходимости разрешения на интернет у основного приложения
<br> Рассказать, что 
