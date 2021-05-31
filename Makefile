static_analysis:
	./gradlew detektAll

unit_tests:
	./gradlew test

compile_all:
	./gradlew assemble

fast_check:
	./gradlew clean
	make static_analysis
	make unit_tests
	make compile_all
