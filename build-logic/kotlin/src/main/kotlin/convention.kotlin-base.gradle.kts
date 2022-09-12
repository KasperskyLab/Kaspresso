import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinLanguageVersion = "1.7"

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {

        jvmTarget = JavaVersion.VERSION_1_8.toString()

//        TODO: fix all warnings before
//        allWarningsAsErrors = true

        languageVersion = kotlinLanguageVersion
        apiVersion = kotlinLanguageVersion

        freeCompilerArgs = freeCompilerArgs +
                "-opt-in=kotlin.RequiresOptIn" +
                "-progressive"
    }
}
