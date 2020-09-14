//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.keyboard](../index.md)/[Keyboard](index.md)/[typeText](type-text.md)



# typeText  
[androidJvm]  
Brief description  




Types text char by char in the focused text field. Use it only when Espresso or UiAutomator are not appropriate (e.g. when you are on the lock screen).



Consider to use ViewActions.typeText. Also, consider to use UiObject.setText.



Required Permissions: INTERNET



  
Content  
abstract fun [typeText](type-text.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  



