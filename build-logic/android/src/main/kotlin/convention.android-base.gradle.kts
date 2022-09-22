import com.android.build.gradle.BaseExtension

@Suppress("MagicNumber")
configure<BaseExtension> {

    compileSdkVersion(33)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdk = 18
        targetSdk = 30
    }

    packagingOptions {
        merges.apply {
            add("META-INF/LGPL2.1")
            add("META-INF/AL2.0")
        }
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
