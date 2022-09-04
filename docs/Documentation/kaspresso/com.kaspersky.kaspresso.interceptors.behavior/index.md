//[kaspresso](../index.md)/[com.kaspersky.kaspresso.interceptors.behavior](index.md)



# Package com.kaspersky.kaspresso.interceptors.behavior  


## Types  
  
|  Name|  Summary| 
|---|---|
| [BehaviorInterceptor](-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The interface for all interceptors that change the default interaction in Kakao=>Espresso. Often it wraps the interaction calls.<br><br>  <br>Content  <br>interface [BehaviorInterceptor](-behavior-interceptor/index.md)<[Interaction](-behavior-interceptor/index.md)>  <br><br><br>
| [DataBehaviorInterceptor](-data-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting DataInteraction.check behavior.<br><br>  <br>Content  <br>interface [DataBehaviorInterceptor](-data-behavior-interceptor/index.md) : [BehaviorInterceptor](-behavior-interceptor/index.md)<DataInteraction>   <br><br><br>
| [ViewBehaviorInterceptor](-view-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting ViewInteraction.perform and ViewInteraction.check behavior.<br><br>  <br>Content  <br>interface [ViewBehaviorInterceptor](-view-behavior-interceptor/index.md) : [BehaviorInterceptor](-behavior-interceptor/index.md)<ViewInteraction>   <br><br><br>
| [WebBehaviorInterceptor](-web-behavior-interceptor/index.md)| [androidJvm]  <br>Brief description  <br><br><br>The derived from [BehaviorInterceptor](-behavior-interceptor/index.md) interface for intercepting Web.WebInteraction.perform and Web.WebInteraction.check behavior.<br><br>  <br>Content  <br>interface [WebBehaviorInterceptor](-web-behavior-interceptor/index.md) : [BehaviorInterceptor](-behavior-interceptor/index.md)<Web.WebInteraction<*>>   <br><br><br>

