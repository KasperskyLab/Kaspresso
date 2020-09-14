//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.location](../index.md)/[LocationImpl](index.md)



# LocationImpl  
 [androidJvm] 

The implementation of the [Location](../-location/index.md) interface.

class [LocationImpl](index.md)(**adbServer**: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)) : [Location](../-location/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [LocationImpl](-location-impl.md)|  [androidJvm] fun [LocationImpl](-location-impl.md)(adbServer: [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [disableGps](disable-gps.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Disables GPS on the device.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>open override fun [disableGps](disable-gps.md)()  <br><br><br>
| [enableGps](enable-gps.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Enables GPS on the device.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>open override fun [enableGps](enable-gps.md)()  <br><br><br>
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [setLocation](set-location.md)| [androidJvm]  <br>Brief description  <br><br><br><br><br>Sets current location.<br><br><br><br>Required Permissions: INTERNET<br><br><br><br>  <br>Content  <br>open override fun [setLocation](set-location.md)(lat: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), lon: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

