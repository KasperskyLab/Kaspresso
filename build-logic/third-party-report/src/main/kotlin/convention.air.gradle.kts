// Please do not remove this file. This file is used for new releases.
import java.time.Duration

plugins {
    id("com.kaspersky.gradle.air-client-plugin")
}

air {
    clientSettings {
        timeout = Duration.ofMinutes(30)
    }
}
