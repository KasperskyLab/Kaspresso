plugins {
    id("convention.publication-base")
    signing
}

val ossrhUsername: Provider<String> = providers.gradleProperty("kaspresso.ossrh.user")
    .forUseAtConfigurationTime()

val ossrhPassword: Provider<String> = providers.gradleProperty("kaspresso.ossrh.password")
    .forUseAtConfigurationTime()

val sonatypeRepoName = "SonatypeReleases"

tasks.withType<PublishToMavenRepository>().configureEach {

    // https://docs.gradle.org/current/userguide/publishing_customization.html#sec:configuring_publishing_tasks
    if (name.contains(sonatypeRepoName)) {
        doFirst {
            repository = repository.apply { setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") }
        }
    }
}

val publishTask = tasks.register<Task>("publishToSonatype") {
    group = "publication"

    dependsOn(tasks.named("publishAllPublicationsTo${sonatypeRepoName}Repository"))
}

publishing {
    repositories {
        maven {
            name = sonatypeRepoName
            credentials {
                username = ossrhUsername.orNull
                password = ossrhPassword.orNull
            }
        }
    }
}

signing {
    sign(publishing.publications)

    val signingKeyId = providers.gradleProperty("kaspresso.pgp.keyid")
        .forUseAtConfigurationTime()
        .orNull
    val signingKey = providers.gradleProperty("kaspresso.pgp.key")
        .forUseAtConfigurationTime()
        .orNull
    val signingPassword = providers.gradleProperty("kaspresso.pgp.password")
        .forUseAtConfigurationTime()
        .orNull

    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
}

tasks.withType<Sign>().configureEach {
    onlyIf {
        gradle.taskGraph.hasTask(publishTask.get())
    }
}

fun String?.toFile() = if (this.isNullOrBlank()) null else File(this)
