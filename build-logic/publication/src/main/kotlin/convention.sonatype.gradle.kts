plugins {
    `maven-publish`
    signing
}

val ossrhUsername = findProperty("kaspresso.ossrh.user")?.toString()
val ossrhPassword = findProperty("kaspresso.ossrh.password")?.toString()

val sonatypeReleasesRepoName = "SonatypeReleases"
val sonatypeSnapshotsRepoName = "SonatypeSnapshots"

publishing {
    repositories {
        maven {
            name = sonatypeReleasesRepoName
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }

        maven {
            name = sonatypeSnapshotsRepoName
            setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

signing {
    sign(publishing.publications)

    val signingKeyId = findProperty("kaspresso.pgp.keyid")?.toString()
    val signingKey = findProperty("kaspresso.pgp.key")?.toString()
    val signingPassword = findProperty("kaspresso.pgp.password")?.toString()

    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
}

tasks.withType<Sign>().configureEach {
    onlyIf {
        val isReleaseQueued = gradle.taskGraph.hasTask("publishAllPublicationsTo${sonatypeReleasesRepoName}Repository")
        val isSnapshotQueued = gradle.taskGraph.hasTask("publishAllPublicationsTo${sonatypeSnapshotsRepoName}Repository")
        isReleaseQueued || isSnapshotQueued
    }
}
