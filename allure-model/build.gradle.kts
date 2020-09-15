plugins {
    kotlin
    javaLibrary
}

sourceSets {
    val main by getting {
        java.srcDirs("src/main/kotlin")
    }
}

dependencies {
    compileOnly(Dependencies.kotlinStdlib)
    compileOnly(Dependencies.gson)
}