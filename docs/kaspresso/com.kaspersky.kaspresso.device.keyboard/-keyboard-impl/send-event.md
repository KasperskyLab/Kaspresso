//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.keyboard](../index.md)/[KeyboardImpl](index.md)/[sendEvent](send-event.md)



# sendEvent  
[androidJvm]  
Brief description  




Sends a key event. Use constants from [KeyEvent](https://developer.android.com/reference/kotlin/android/view/KeyEvent.html) to get the code.



Consider to use ViewActions.pressKey. Also, consider to use UiDevice.pressKeyCode, or more semantic methods like UiDevice.pressMenu, UiDevice.pressDPadLeft and so on.



Required Permissions: INTERNET





## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| keyEvent| <br><br>the code from a [KeyEvent](https://developer.android.com/reference/kotlin/android/view/KeyEvent.html) constant to send on device.<br><br>
  
  
Content  
open override fun [sendEvent](send-event.md)(keyEvent: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  



