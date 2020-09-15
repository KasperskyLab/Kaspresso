//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.apps](../index.md)/[AppsImpl](index.md)/[installIfNotExists](install-if-not-exists.md)



# installIfNotExists  
[androidJvm]  
Brief description  




Installs an app via ADB only if packageName is not installed



Required Permissions: INTERNET.





## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| apkPath| <br><br>a path to the apk to be installed. The apk is hosted on the test server.<br><br>
| packageName| <br><br>an android package name of the app to be checked.<br><br>
  
  
Content  
open override fun [installIfNotExists](install-if-not-exists.md)(packageName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), apkPath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  



