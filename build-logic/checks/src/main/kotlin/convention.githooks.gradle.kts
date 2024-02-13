tasks.register<Exec>("installGitHooks") {
    description = "Install local git hooks"
    group = "Build Setup"

    commandLine("git")
    args("config", "core.hooksPath", ".githooks")

    val isCi = project.hasProperty("CI")
    onlyIf { !isCi }
}

val initialTaskNames: List<String> = project.gradle.startParameter.taskNames
project.gradle.startParameter.setTaskNames(initialTaskNames + listOf("installGitHooks"))
