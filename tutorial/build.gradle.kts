plugins {
    androidApplication
    kotlinAndroid
    kotlinAndroidExtensions
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.tutorials"
        minSdkVersion(18)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")

    androidTestImplementation("com.kaspersky.android-components:kaspresso:1.2.0")
}
