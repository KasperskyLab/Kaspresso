plugins {
    `maven-publish`
}

group = "com.kaspersky.android-components"

version = providers.gradleProperty("stableVersion")
    .forUseAtConfigurationTime()
    .get()

publishing.publications.withType<MavenPublication> {
    pom {
        name.set("Kaspresso")
        description.set("Android framework for UI testing")
        url.set("https://github.com/KasperskyLab/Kaspresso")

        scm {
            url.set("https://github.com/KasperskyLab/Kaspresso")
        }

        licenses {
            license {
                name.set("Apache License, Version 2.0")
                url.set("https://github.com/KasperskyLab/Kaspresso/blob/master/LICENSE")
            }
        }

        developers {
            developer {
                id.set("matzuk")
                name.set("Eugene Matsyuk")
                url.set("https://github.com/matzuk")
            }
            developer {
                id.set("eakurnikov")
                name.set("Egor Kurnikov")
                url.set("https://github.com/eakurnikov")
            }
            developer {
                id.set("RuslanMingaliev")
                name.set("Ruslan Mingaliev")
                url.set("https://github.com/RuslanMingaliev")
            }
        }
    }
}
