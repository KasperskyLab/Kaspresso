import io.gitlab.arturbosch.detekt.Detekt

plugins {
    base
    id("convention.githooks")
    id("convention.dependency-updates")
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("convention.air")
    alias(libs.plugins.compose.compiler) apply false
}

apply {
    from("static-analysis/quality.gradle")
}

buildscript {
    dependencies {
        classpath(libs.kotlinPlugin)
        classpath(libs.androidPlugin)
    }
}

dependencies {
    add("detektPlugins", libs.detektFormatting)
}

val detektAll = tasks.register<Detekt>("detektAll") {
    description = "Runs over whole code base without the starting overhead for each module."
    parallel = true
    autoCorrect = true
    setSource(files(projectDir))

    config.setFrom(files(project.rootDir.resolve("static-analysis/config/detekt/config.yml")))
    buildUponDefaultConfig = false

    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
    reports {
        xml.required.set(false)
        html.required.set(true)
        html.outputLocation.set(File("static-analysis/reports/html/detekt.html"))
    }
}
