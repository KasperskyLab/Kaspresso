//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.systemsafety](../index.md)/[SystemDialogSafetyProvider](index.md)/[passSystemDialogs](pass-system-dialogs.md)



# passSystemDialogs  
[androidJvm]  
Brief description  


Invokes the given action and hides the system dialog if the invocation is failed and the system dialog is actually shown via suppressSystemDialogs call.



#### Return  


the result of action's invocation.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| action| <br><br>the action to invoke.<br><br>
  
  
Content  
abstract fun <[T](pass-system-dialogs.md)> [passSystemDialogs](pass-system-dialogs.md)(action: () -> [T](pass-system-dialogs.md)): [T](pass-system-dialogs.md)  



