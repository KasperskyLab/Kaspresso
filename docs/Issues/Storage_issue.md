
## Storage issues
Kaspresso can use external storage to save various data about executed tests. The example of such data is screenshots, xml dumps, logs, video and anymore.
But, new Android OS provides absolutely new way to work with external storage - Scoped Storage. Currently, we are working on the support of Scoped Storage.
While Scoped Storage support is on the way, there is an option to request different permissions to make an access to saved data possible on any Android OS.
Here, it's a detailed instruction:
1. AndroidManifest.xml (in your debug build variant to keep production manifest without any changes)
```xml
# Please, add these permissions
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE"/>

<application
    # storage support for Android API 29         
    android:requestLegacyExternalStorage="true"
    ...
</application>             
```
2. Your test class:
```kotlin
class SampleTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple( // simple/advanced - it doesn't matter
        customize = { 
            // storage support for Android API 30+
            if (isAndroidRuntime) {
                UiDevice
                    .getInstance(instrumentation)
                    .executeShellCommand("appops set --uid ${InstrumentationRegistry.getInstrumentation().targetContext.packageName} MANAGE_EXTERNAL_STORAGE allow")
            }
        }
    )
) {

    // storage support for Android API 29-
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    
    //...
}    
```
Remember, it's a temporary working solution.
A little bit later, Kaspresso will use external storage only through Scoped Storage and you will not be forced to request all mentioned permissions.
