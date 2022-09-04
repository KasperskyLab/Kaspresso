//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.network](../index.md)/[NetworkImpl](index.md)



# NetworkImpl  
 [androidJvm] 

The implementation of the [Network](../-network/index.md) interface.

class [NetworkImpl](index.md)(**targetContext**: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), **adbServer**: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), **logger**: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)) : [Network](../-network/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [NetworkImpl](-network-impl.md)|  [androidJvm] fun [NetworkImpl](-network-impl.md)(targetContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), adbServer: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), logger: [UiTestLogger](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [androidJvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [disable](disable.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Disables both Wi-Fi and mobile data.<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>open override fun [disable](disable.md)()  <br><br><br>
| [enable](enable.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Enables both Wi-Fi and mobile data.<br><br><br><br>Required Permissions: INTERNET.<br><br><br><br>  <br>Content  <br>open override fun [enable](enable.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toggleMobileData](toggle-mobile-data.md)| [androidJvm]  <br>Brief description  <br><br><br>Toggles only mobile data. Tries to change state with adb command first and using Settings then.<br><br>  <br>Content  <br>open override fun [toggleMobileData](toggle-mobile-data.md)(enable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [toggleWiFi](toggle-wi-fi.md)| [androidJvm]  <br>Brief description  <br><br><br>Toggles only Wi-Fi. Tries, first and foremost, to change Wi-Fi state using Android API if targetApi is lower than 29 and [Manifest.permission.CHANGE_WIFI_STATE](https://developer.android.com/reference/kotlin/android/Manifest.permission.html#change_wifi_state) permission is granted. In case of failure, sends ADB command. If this attempt fails too, opens Android Settings screen and tries to switch Wi-Fi setting thumb.<br><br>  <br>Content  <br>open override fun [toggleWiFi](toggle-wi-fi.md)(enable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

