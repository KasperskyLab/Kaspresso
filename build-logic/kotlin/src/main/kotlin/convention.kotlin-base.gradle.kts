import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinLanguageVersion = "1.6"

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {

        jvmTarget = JavaVersion.VERSION_1_8.toString()

//        TODO: remove `android-extensions` plugin before
//        allWarningsAsErrors = true

        languageVersion = kotlinLanguageVersion
        apiVersion = kotlinLanguageVersion

        freeCompilerArgs = freeCompilerArgs +
                "-Xopt-in=kotlin.RequiresOptIn" +
                "-progressive"
    }
}
