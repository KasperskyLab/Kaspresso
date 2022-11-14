plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation(libs.androidPlugin)
    implementation(projects.adbServer.adbserverCommon)
    implementation(projects.adbServer.adbserverDesktop)
}

// TODO setup maven publication
group = "com.kaspersky.kaspresso"
gradlePlugin {
    plugins {
        create("SignerPlugin") {
            id = "com.kaspersky.kaspresso"
            implementationClass = "com.kaspersky.kaspresso.plugin.KaspressoPlugin"
        }
    }
}
