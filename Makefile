static_analysis:
	./gradlew detektAll lint

unit_tests:
	./gradlew test

compile_all:
	./gradlew assemble assembleAndroidTest

fast_check:
	./gradlew clean
	make static_analysis
	make unit_tests
	make compile_all

publish_to_maven_local:
	./gradlew publishToMavenLocal -PstableVersion=local --no-configuration-cache

publish_release:
	./gradlew publishRelease --no-parallel --stacktrace
