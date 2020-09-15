//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.logcat](../index.md)/[Logcat](index.md)/[disableChatty](disable-chatty.md)



# disableChatty  
[androidJvm]  
Brief description  




NOT WORKING ON ANDROID 8+



The problem: Android OS has a special introduced mechanism to filter and collapse of some bunches of logs produced by applications. The name of the such mechanism is Chatty. Chatty turns on when an application writes a lot of logs. The goal of Logcat interface is to analyze all logs. But Chatty prevents achievement of the mentioned goal. That's why, there is this method to disable Chatty. Please, call the method in "before" section of a test.



  
Content  
abstract fun [disableChatty](disable-chatty.md)()  



