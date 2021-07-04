rootProject.name = "kaspresso-framework"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

includeBuild("build-conventions")

include(
    ":allure-model",
    ":allure-interceptors",

    ":adb-server:adbserver-command-types",
    ":adb-server:adbserver-common",
    ":adb-server:adbserver-connection",
    ":adb-server:adbserver-desktop",
    ":adb-server:adbserver-desktop-device-connection",
    ":adb-server:adbserver-device",

    ":kaspresso",
    ":kautomator",

    ":samples:adbserver-sample",
    ":samples:kaspresso-sample",
    ":samples:kautomator-sample",
    ":samples:kautomator-sample-app-upgrade",

    ":tutorial"
)
