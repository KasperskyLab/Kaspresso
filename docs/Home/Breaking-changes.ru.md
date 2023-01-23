## Breaking changes
### 1.2.0
- We've totally reworked AdbServer and Kaspresso 1.2.0 works only with new `artifacts/adbserver-desktop.jar`<br>
  The old version `artifacts/desktop_1_1_0.jar` is also available for use with older versions of Kaspresso.
- If you use `device.logcat` in your tests, you should call `device.logcat.disableChatty` in the `before` section of your test.
  In previous version of Kaspresso, `device.logcat.disableChatty` was called automatically during initialization. This resulted in the need to always run AdbServer before tests.
### 1.2.1
- Kaspresso migrated to a new version of Kakao which has `io.github.kakaocup.kakao` package name. Replace all imports using command
  `find . -type f \( -name "*.kt" -o -name "*.java" \) -print0 | xargs -0 sed -i '' -e 's/com.agoda/io.github.kakaocup/g'` or using global replacement tool in IDE.
