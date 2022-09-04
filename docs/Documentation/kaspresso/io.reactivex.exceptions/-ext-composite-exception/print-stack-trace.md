//[kaspresso](../../index.md)/[io.reactivex.exceptions](../index.md)/[ExtCompositeException](index.md)/[printStackTrace](print-stack-trace.md)



# printStackTrace  
[androidJvm]  
Brief description  




All of the following {@code printStackTrace} functionality is derived from JDK [Throwable](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html){@code printStackTrace} . In particular, the {@code PrintStreamOrWriter} abstraction is copied wholesale. Changes from the official JDK implementation:

<ul><li>no infinite loop detection</li><li>smaller critical section holding [PrintStream](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html) lock</li><li>explicit knowledge about the exceptions [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) that this loops through</li></ul>

  
Content  
open fun [printStackTrace](print-stack-trace.md)()  


[androidJvm]  
Content  
open fun [printStackTrace](print-stack-trace.md)(s: [PrintStream](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html))  
open fun [printStackTrace](print-stack-trace.md)(s: [PrintWriter](https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html))  



