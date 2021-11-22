plugins {
    id("convention.publication-base")
    id("convention.sonatype")
}

tasks.register("publishRelease") {
    group = "publication"

    dependsOn(tasks.named("publishToSonatype"))
}
