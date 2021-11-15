import com.android.build.gradle.BaseExtension

@Suppress("MagicNumber")
configure<BaseExtension> {
    sourceSets {
        named("main").configure { java.srcDir("src/main/kotlin") }
        named("test").configure { java.srcDir("src/test/kotlin") }
        named("androidTest").configure { java.srcDir("src/androidTest/kotlin") }
    }

    // temp solution of the following bug
    // https://stackoverflow.com/questions/67358179/android-espresso-test-error-no-static-method-loadsingleserviceornull
    // in the Kaspresso, it is caused by androidx.compose.ui:ui-test-junit4:1.0.3 in Kakao-compose that uses androidx.test.espresso:espresso-core:3.3.0 under the hood
    // but we expect using of androidx.test.espresso:espresso-core:3.4.0
    configurations.all {
        resolutionStrategy {
            force("androidx.test:monitor:1.4.0")
        }
    }

    // temp solution
    // appeared with kakao-compose library including
    // similar to https://github.com/Kotlin/kotlinx.coroutines/issues/2023
    packagingOptions {
        exclude("META-INF/*")
    }

    buildToolsVersion("30.0.3")
    compileSdkVersion(31)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        setMinSdkVersion(21)
        setTargetSdkVersion(29)
    }

    @Suppress("UnstableApiUsage")
    with(buildFeatures) {
        aidl = false
        buildConfig = false
        compose = false
        prefab = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = false
    }

    // This is necessary to use activityScenarioRule in tests
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
