//[kaspresso](../../index.md)/[io.reactivex.exceptions](../index.md)/[ExtCompositeException](index.md)/[printStackTrace](print-stack-trace.md)



# printStackTrace  
[androidJvm]  
Brief description  




All of the following {@code printStackTrace} functionality is derived from JDK [Throwable](https://developer.android.com/reference/kotlin/java/lang/Throwable.html){@code printStackTrace} . In particular, the {@code PrintStreamOrWriter} abstraction is copied wholesale. Changes from the official JDK implementation:

<ul><li>no infinite loop detection</li><li>smaller critical section holding [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html) lock</li><li>explicit knowledge about the exceptions [List](https://developer.android.com/reference/kotlin/java/util/List.html) that this loops through</li></ul>

  
Content  
open fun [printStackTrace](print-stack-trace.md)()  


[androidJvm]  
Content  
open fun [printStackTrace](print-stack-trace.md)(s: [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html))  
open fun [printStackTrace](print-stack-trace.md)(s: [PrintWriter](https://developer.android.com/reference/kotlin/java/io/PrintWriter.html))  



