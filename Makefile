static_analysis:
	./gradlew detektAll

unit_tests:
	./gradlew test

compile_all:
	./gradlew assemble assembleAndroidTest

fast_check:
	./gradlew clean assemble assembleAndroidTest test detektAll

publish_release:
	./gradlew publishRelease --no-parallel --stacktrace
