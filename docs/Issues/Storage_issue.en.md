
!!! info
    The problem described below is relevant for versions of Kaspresso below 1.5.0. Starting with this version, Kaspresso fully supports the new format of working with system storage.

Kaspresso can use external storage to save various data about executed tests. The example of such data is screenshots, xml dumps, logs, video and anymore.
But, new Android OS provides absolutely new way to work with external storage - Scoped Storage. Currently, we are working on the support of Scoped Storage.
On versions of Kaspresso prior to 1.5.0, work with Scoped storage is supported only by requesting various permissions.
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

This is a temporary solution. We recommend migrating to the latest version of Kaspresso (1.5.0 and above) to avoid these problems.
