## 1.2.0
- Мы полностью переработали AdbServer. Kaspresso 1.2.0 и выше работает только с новым артефактом `artifacts/adbserver-desktop.jar`<br>
  Старая версия `artifacts/desktop_1_1_0.jar` также доступна для ранних версий Kaspresso.
- Если вы использовали метод `device.logcat` в ваших тестах, вам следует использовать метод `device.logcat.disableChatty` в секции `before`.
  В предыдущей версии Kaspresso `device.logcat.disableChatty` вызывался автоматически во время инициализации. Как результат, всегда приходилось перезапускать AdbServer перед каждым тестом.
## 1.2.1
- Kaspresso мигрировал на новую версию Kakao с новым именем пакета `io.github.kakaocup.kakao`. Замените все импорты с помощью комманды
  `find . -type f \( -name "*.kt" -o -name "*.java" \) -print0 | xargs -0 sed -i '' -e 's/com.agoda/io.github.kakaocup/g'` или с помощью утилиты среды разработки для глобальной замены импортов.
## 1.5.0
- Из-за системных ограничений на доступ к памяти артифакты сохраняются в папку `/sdcard/Documents`.
  Для записи видео необходимо использовать новый Kaspresso builder: `Kaspresso.Builder.withForcedAllureSupport()` и заменить test runner (`io.qameta.allure.android.runners.AllureAndroidJUnitRunner`) на `com.kaspersky.kaspresso.runner.KaspressoRunner`.
  TestFailRule устарел. Поправили падающие скриншот-тесты.
  Улучшено автоматическое закрытие системных окон. Посмотреть изменения можно [здесь](https://github.com/KasperskyLab/Kaspresso/pull/460/files).
