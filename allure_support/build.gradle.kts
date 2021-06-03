import Dependencies.Versions

plugins {
    androidLibrary
    kotlinAndroid
    kotlinAndroidExtensions
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
    }

    sourceSets {
        val main by getting {
            java.srcDirs("src/main/kotlin", "src/main/java")
        }
    }
}

repositories {
    maven { url = uri("https://dl.bintray.com/qameta/maven") }
}

dependencies {
    implementation(project(Projects.Kaspresso.framework))

    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.truth)
    implementation(Dependencies.testCore)
    implementation(Dependencies.uiAutomator)

    implementation(Dependencies.allureCommons)
    implementation(Dependencies.allureModel)
    implementation(Dependencies.allureEspresso)

    implementation(Dependencies.runner)

    testImplementation(Dependencies.junit)

    androidTestImplementation(Dependencies.espressoCore)
}
