import io.gitlab.arturbosch.detekt.Detekt

plugins {
    base
    id("convention.dependency-updates")
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("convention.air")
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
        xml.enabled = false
        html.enabled = false
    }
}

tasks.register<Exec>("installGitHooks") {
    description = "Install local git hooks"
    group = "Build Setup"

    commandLine("chmod")
    args("-R", "u+x", ".githooks")

    commandLine("git")
    args("config", "core.hooksPath", ".githooks")

    onlyIf { !project.hasProperty("CI") }
}

val initialTaskNames: List<String> = project.gradle.startParameter.taskNames
project.gradle.startParameter.setTaskNames(initialTaskNames + listOf("installGitHooks"))
