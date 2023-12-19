plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.2.1"
}

dependencies {
    implementation(libs.androidPlugin)
    implementation(projects.adbServer.adbServerCommon)
    implementation(projects.adbServer.adbserverDesktop)
}

group = "com.kaspersky.kaspresso"
version = "1.0"
gradlePlugin {
    // TODO: fix for tests on API 30 and uncomment
    // website.set("https://kasperskylab.github.io/Kaspresso/en/")
    // vcsUrl.set("https://github.com/KasperskyLab/Kaspresso/")

    plugins {
        create("AdbServerPlugin") {
            id = "com.kaspersky.kaspresso-adb-server-plugin"
            displayName = "Kaspresso ADB-server plugin"
            description = "Run Kaspresso ADB server for Android UI tests"
            // tags.set(listOf("testing", "UI tests", "test automation", "android", "kasresso", "adb server"))
            implementationClass = "com.kaspersky.kaspresso.plugin.KaspressoPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "localPluginRepository"
            url = uri("../local-plugin-repository")
        }
    }
}
