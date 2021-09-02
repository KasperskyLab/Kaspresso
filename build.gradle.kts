import io.gitlab.arturbosch.detekt.Detekt

plugins {
    base
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
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
        xml.enabled = false
        html.enabled = false
    }
}
