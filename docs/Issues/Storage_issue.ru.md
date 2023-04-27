
!!! info
    Описанная ниже проблема актуальна для версий Kaspresso ниже 1.5.0. Начиная с этой версии Kaspresso полноценно поддерживает новый формат работы с системной памятью.

Kaspresso может использовать память девайса для сохранения различных артефактов выполняемых тестов. Например, это могут быть скриншоты, дампы xml, журналы событий, видео и многое другое.
Однако, новые версии Android предполагают абсолютно новый способ взаимодействия с памятью - Scoped storage.
На версиях Kaspresso до 1.5.0 поддерживается работа с Scoped storage только через запрос различных разрешений.
Ниже предоставлена инструкция:

1. AndroidManifest.xml (эти изменения нужно внести только в debug версию сборки, чтобы изменения не затронули основной проектный файл)
```xml
# Пожалуйста, добавьте эти разрешения
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE"/>

<application
    # storage support for Android API 29         
    android:requestLegacyExternalStorage="true"
    ...
</application>             
```
2. В вашем тестовом классе добавьте:
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

Это временное решение. Мы рекомендуем мигрировать на свежую версию Kaspresso (1.5.0 и выше) для избежания этих проблем.
