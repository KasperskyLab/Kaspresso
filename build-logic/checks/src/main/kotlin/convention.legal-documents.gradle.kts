tasks.register("checkLegalDocuments") {
    description = "Check that the NOTICE.txt and LICENSE.txt files are present and have the right content"

    val file = File(projectDir, "NOTICE.txt")
    require(file.exists()) { "Legal document not found by path $file" }

    val firstLine = File(projectDir, "NOTICE.txt").readLines().first()
    require(firstLine.contains("Kaspresso/${project.name}")) {
        "Legal document doesn't contain module name (${project.name}). Document path: $file"
    }

    val kaspressoVersion = project.property("kaspresso.snapshotVersion").toString().split("-SNAPSHOT").first()
    require(firstLine.contains(kaspressoVersion)) {
        "Failed to check whether the existing NOTICE.txt was made for the current release ($kaspressoVersion)"
    }

    val licenseFile = File(rootDir, "LICENSE.txt")
    require(licenseFile.exists()) { "LICENSE.txt not found by path $licenseFile" }
}
