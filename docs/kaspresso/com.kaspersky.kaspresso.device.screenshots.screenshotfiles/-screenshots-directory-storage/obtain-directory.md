//[kaspresso](../../index.md)/[com.kaspersky.kaspresso.device.screenshots.screenshotfiles](../index.md)/[ScreenshotsDirectoryStorage](index.md)/[obtainDirectory](obtain-directory.md)



# obtainDirectory  
[androidJvm]  
Brief description  


Returns directory for a particular test. If the directory exists, it will be deleted on the first method call. If the directory doesn't exist, it will be created.



#### Return  


[File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) which represents an existing directory.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| screenshotTestDir| <br><br>desired directory resolved by the root dir.<br><br>
  
  
Content  
fun [obtainDirectory](obtain-directory.md)(screenshotTestDir: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)  



