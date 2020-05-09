import Dependencies.Versions

plugins {
    androidApplication
    kotlinAndroid
    kotlinAndroidExtensions
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.kaspersky.kaspressample"

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        val main by getting {
            java.srcDir("src/main/kotlin")
        }
    }
}

dependencies {
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraint)

    androidTestImplementation(Dependencies.runner)
    androidTestImplementation(Dependencies.rules)
    androidTestImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.espressoWeb)
    androidTestImplementation(Dependencies.kakao)
    androidTestImplementation(project(Projects.KASPRESSO_FRAMEWORK))
    androidTestImplementation(project(Projects.KAUTOMATOR_FRAMEWORK))
}