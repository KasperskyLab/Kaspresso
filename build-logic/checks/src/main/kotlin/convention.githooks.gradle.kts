tasks.register<Exec>("installGitHooks") {
    description = "Install local git hooks"
    group = "Build Setup"

    commandLine("git")
    args("config", "core.hooksPath", ".githooks")

    onlyIf { !project.hasProperty("CI") }
}

val initialTaskNames: List<String> = project.gradle.startParameter.taskNames
project.gradle.startParameter.setTaskNames(initialTaskNames + listOf("installGitHooks"))
