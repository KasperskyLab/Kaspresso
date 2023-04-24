# Нашли проблему?
Kaspresso объединило вокруг себя большое сообщество, которое позволяет улучшить фреймворк, предлагая новые идеи, сообщая о найденных ошибках с детальным описанием и оформляя pull request-ы, предоставляя готовую реализации доработок.

## Создание новых issue
В нашей [вкладке Issue](https://github.com/KasperskyLab/Kaspresso/issues) вы можете создать новую issue. Чаще всего используются два типа issue: ошибки и доработки.

### Шаблон для ошибок
Если вы нашли ошибку, вы можете [создать новую issue](https://github.com/KasperskyLab/Kaspresso/issues/new/choose). Введите заголовок и описание (детали ошибки) в поля ввода. Мы будем благодарны, если вы будете использовать этот готовый шаблон:

```text
Description:
...
Expected Behavior:
...
Actual Behavior:
...
Steps to Reproduce the Problem:
...
Specifications:
...
```

Например:
```text
When using newer versions of the library, Gradle is unable to find and download the library sources (which allow you to read and debug the source code directly on the IDE).

Expected Behavior
Projects with the Kaspresso dependency should be able to download sources.

Actual Behavior
When trying do download sources, the following error appears:

* What went wrong:
Execution failed for task ':app:DownloadSources'.
> Could not resolve all files for configuration ':app:downloadSources_10c6f7e9-408b-4f6a-8bd9-fe15e255981e'.
   > Could not find com.kaspersky.android-components:kaspresso:1.4.1@aar.
     Searched in the following locations:
       - https://dl.google.com/dl/android/maven2/com/kaspersky/android-components/kaspresso/1.4.1@aar/kaspresso-1.4.1@aar.pom
       - https://repo.maven.apache.org/maven2/com/kaspersky/android-components/kaspresso/1.4.1@aar/kaspresso-1.4.1@aar.pom
     Required by:
         project :app
         
Steps to Reproduce the Problem
Create an empty project;
Add the dependency androidTestImplementation "com.kaspersky.android-components:kaspresso:1.4.1";
Create a test using classes from Kaspresso;
Try to access the source (on IntelliJ IDE, Ctrl+left click a Kaspresso class name/method call);
You will only be able to see the decompiled bytecode.
Specifications
Library version: at least >= 1.4.1
IDE used: Android Studio

Observations
I haven't tested on all versions, but sources were able to be downloaded at least up to version 1.2.1.
```

### Шаблон для улучшения
Если у вас есть идея для доработки вы можете [создать новую issue](https://github.com/KasperskyLab/Kaspresso/issues/new/choose). Введите заголовок и описание в поля ввода. Мы будем благодарны, если вы будете использовать этот готовый шаблон:

```text
Description:
...
How the new enhancement will help?:
...
Existing analogs (with links):
...
```

### Доработки в виде Pull request всегда приветствуются!
Если у вас есть не просто запрос на доработку, но и готовая реализация, вы можете отправить ее на Github, оформив Pull request.

## Спасибо!
