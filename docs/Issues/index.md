# Found an issue?
Kaspresso has a great community that helps make it better by suggesting new ideas, reporting bugs with detailed descriptions and making pull requests.

## Creating new issues
In our [Issues tab](https://github.com/KasperskyLab/Kaspresso/issues) you can create a new one. There are two most popular types of issues: bug and enhancement.

### Template for bugs
If you found a bug you can [create new issue](https://github.com/KasperskyLab/Kaspresso/issues/new/choose). Enter a title and provide a description (bug details) in the input fields. We will be very grateful if you use this template:

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

For example:
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

### Template for enhancements
If you have an idea of a new enhancement you can [create new issue](https://github.com/KasperskyLab/Kaspresso/issues/new/choose). Enter a title and provide a description in the input fields. We will be very grateful if you use this template:

```text
Description:
...
How the new enhancement will help?:
...
Existing analogs (with links):
...
```

### Pull requests are allways welcome
If you have not only an issue, but also a ready implementation, you can always submit the pull request on Github.

## Thanks!
