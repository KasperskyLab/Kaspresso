rootProject.name = "kaspresso-framework"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    val kasperskyRepoUrl: String? by settings
    repositories {
        gradlePluginPortal()
        google()
        kasperskyRepoUrl?.let { maven { setUrl(it) } }
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

includeBuild("build-logic")

include(
    ":adb-server:adbserver-command-types",
    ":adb-server:adbserver-common",
    ":adb-server:adbserver-connection",
    ":adb-server:adbserver-desktop",
    ":adb-server:adbserver-desktop-device-connection",
    ":adb-server:adbserver-device",

    ":kaspresso",
    ":kautomator",
    ":kaspresso-allure-support",
    ":kaspresso-compose-support",

    ":tutorial"
)
