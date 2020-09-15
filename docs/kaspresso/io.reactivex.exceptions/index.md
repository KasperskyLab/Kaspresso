//[kaspresso](../index.md)/[io.reactivex.exceptions](index.md)



# Package io.reactivex.exceptions  


## Types  
  
|  Name|  Summary| 
|---|---|
| [ExtCompositeException](-ext-composite-exception/index.md)| [androidJvm]  <br>Brief description  <br><br><br>Represents an exception that is a composite of one or more other exceptions. A {@code ExtCompositeException} does not modify the structure of any exception it wraps, but at print-time it iterates through the list of Throwables contained in the composite in order to print them all. Its invariant is to contain an immutable, ordered (by insertion order), unique list of non-composite exceptions. You can retrieve individual exceptions in this list with getExceptions . The [printStackTrace](-ext-composite-exception/print-stack-trace.md) implementation handles the StackTrace in a customized way instead of using {@code getCause()} so that it can avoid circular references. If you invoke getCause , it will lazily create the causal chain but will stop if it finds any Throwable in the chain that it has already seen.<br><br>  <br>Content  <br>class [ExtCompositeException](-ext-composite-exception/index.md) : [RuntimeException](https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html)  <br><br><br>

