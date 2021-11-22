enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "build-conventions"

include("android-convention")
include("checks")
include("kotlin-convention")
include("publication-convention")

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
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
