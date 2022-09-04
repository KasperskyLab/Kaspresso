//[kaspresso](../../index.md)/[io.reactivex.exceptions](../index.md)/[ExtCompositeException](index.md)/[getRootCause](get-root-cause.md)



# getRootCause  
[androidJvm]  
Brief description  


Returns the root cause of {@code e} . If {@code e.getCause()} returns {@code null} or {@code e} , just return {@code e} itself.



#### Return  


The root cause of {@code e} . If {@code e.getCause()} returns {@code null} or {@code e} , just return {@code e} itself.



## Parameters  
  
androidJvm  
  
|  Name|  Summary| 
|---|---|
| e| <br><br>the [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html){@code e} .<br><br>
  
  
Content  
open fun [getRootCause](get-root-cause.md)(e: [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html)): [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html)  



