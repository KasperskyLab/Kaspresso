enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "build-conventions"

include("android-convention")
include("kotlin-convention")

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
        mavenCentral()
        jcenter()
    }
}
