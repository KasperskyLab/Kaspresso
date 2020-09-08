//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.network](../index.md)/[NetworkImpl](index.md)/[toggleWiFi](toggle-wi-fi.md)



# toggleWiFi  
[androidJvm]  
Brief description  


Toggles only Wi-Fi. Tries, first and foremost, to change Wi-Fi state using Android API if targetApi is lower than 29 and [Manifest.permission.CHANGE_WIFI_STATE](https://developer.android.com/reference/kotlin/android/Manifest.permission.html#change_wifi_state) permission is granted. In case of failure, sends ADB command. If this attempt fails too, opens Android Settings screen and tries to switch Wi-Fi setting thumb.

  
Content  
open override fun [toggleWiFi](toggle-wi-fi.md)(enable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  



