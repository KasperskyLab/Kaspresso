static_analysis:
	./gradlew detektAll

unit_tests:
	./gradlew test

compile_all:
	./gradlew assemble assembleAndroidTest

fast_check:
	./gradlew clean
	make static_analysis
	make unit_tests
	make compile_all

publish_release:
	./gradlew publishRelease --no-parallel --stacktrace
