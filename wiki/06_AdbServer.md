# AdbServer

## Description
**AdbServer** is a simple comfortable wrapper over [AdbServer repository](https://github.com/KasperskyLab/AdbServer). Please check [AdbServer repository README](https://github.com/KasperskyLab/AdbServer/blob/master/README.md) for more information about principles of how it works.<br>
As it was mentioned at [Device wiki](./05_Device.md), ```device``` uses **AdbServer** under the hood in most cases. <br>

## Usage
**AdbServer** instance is available in ```BaseTestContext``` scope and ```BaseTestCase``` via ```adbServer``` property. <br> 
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

**AdbServer** requires started server side that is called ```desktop.jar```. <br>
How to start ```desktop.jar```:
1. Download a file "kaspresso/artifacts/desktop.jar"
2. Start AdbServer => input in cmd ```java jar path_to_file/desktop.jar```
