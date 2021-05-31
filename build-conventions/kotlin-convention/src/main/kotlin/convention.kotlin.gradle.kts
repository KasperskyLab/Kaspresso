import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

sourceSets {
    named("main").configure { java.srcDir("src/main/kotlin") }
    named("test").configure { java.srcDir("src/test/kotlin") }
}
