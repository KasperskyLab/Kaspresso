echo off
echo Make ui damp and pull it
adb shell uiautomator dump
adb pull /sdcard/window_dump.xml
echo Create page object
java -jar page-object-code-gen.jar window_dump.xml %~1 %~2
del window_dump.xml
