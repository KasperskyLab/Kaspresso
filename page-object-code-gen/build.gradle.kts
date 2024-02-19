plugins {
    id("convention.kotlin-app")
    id("convention.third-party-report")
//    id("convention.android-library")
//    id("convention.publication-android-lib")
//    id("convention.third-party-report")
//    id("convention.legal-documents")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinCli)
    // api(libs.kakao)
    testImplementation(libs.junit)
    testImplementation(libs.assertj)
}

setProperty("mainClassName", "com.kaspresso.components.pageobjectcodegen.CreatePageObjectFromUiDumpKt")

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "com.kaspresso.components.pageobjectcodegen.CreatePageObjectFromUiDumpKt"
    }
    from(
        configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        },
    ) {
        exclude("META-INF/**/**/module-info.class")
    }
    exclude("NOTICE.txt")
    exclude("LICENSE.txt")
    doLast {
        copy {
            from("build/libs/page-object-code-gen.jar")
            into("/Users/ovsyannikov_m/AndroidStudioProjects/Kaspresso/artifacts")
        }
    }
}
