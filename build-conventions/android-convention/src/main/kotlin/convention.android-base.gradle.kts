import com.android.build.gradle.BaseExtension

@Suppress("MagicNumber")
configure<BaseExtension> {

    buildToolsVersion("30.0.3")
    compileSdkVersion(31)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdk = 21
        targetSdk = 30
    }

    packagingOptions {
        excludes.add("META-INF/*")
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
}
