plugins {
    id("convention.kotlin-app")
    id("convention.third-party-report")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinCli)
    implementation(files("src/libs/kakao.jar"))
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
            from("$buildDir/libs/page-object-code-gen.jar")
            into("$rootDir/artifacts")
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }
}
