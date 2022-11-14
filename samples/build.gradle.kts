plugins {
    base
    id("convention.air")
}

buildscript {
    dependencies {
        classpath(libs.kotlinPlugin)
        classpath(libs.androidPlugin)
        classpath("com.kaspersky.kaspresso:kaspresso-plugin")
    }
}
