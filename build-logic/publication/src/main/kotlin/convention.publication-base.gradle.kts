plugins {
    `maven-publish`
    id("convention.sonatype")
}

group = "com.kaspersky.android-components"

version = property("kaspresso.version") ?: error("kaspresso.version property not set")

publishing.publications.withType<MavenPublication>().configureEach {
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
                url.set("https://github.com/KasperskyLab/Kaspresso/blob/master/LICENSE.txt")
            }
        }

        developers {
            developer {
                id.set("AzamatCherchesov")
                name.set("Azamat Cherchesov")
                url.set("https://github.com/AzamatCherchesov")
            }
            developer {
                id.set("eakurnikov")
                name.set("Egor Kurnikov")
                url.set("https://github.com/eakurnikov")
            }
            developer {
                id.set("Fullik")
                name.set("Pogos Azaryan")
                url.set("https://github.com/Fullik")
            }
            developer {
                id.set("matzuk")
                name.set("Eugene Matsyuk")
                url.set("https://github.com/matzuk")
            }
            developer {
                id.set("Nikitae57")
                name.set("Nikita Evdokimov")
                url.set("https://github.com/Nikitae57")
            }
            developer {
                id.set("RuslanMingaliev")
                name.set("Ruslan Mingaliev")
                url.set("https://github.com/RuslanMingaliev")
            }
            developer {
                id.set("VladislavSumin")
                name.set("Vladislav Sumin")
                url.set("https://github.com/VladislavSumin")
            }
        }
    }
}
