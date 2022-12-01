enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }

    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    }
}

includeBuild("../")

include(
    ":adbserver-sample",
    ":kaspresso-sample",
    ":kautomator-sample",
    ":kautomator-sample-app-upgrade",
    ":kaspresso-allure-support-sample",
    ":kaspresso-compose-support-sample",
)
