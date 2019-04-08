[kaspresso](../../index.md) / [com.kaspersky.kaspresso.extensions.spoonext](../index.md) / [UiAutomatorSpoon](index.md) / [screenshot](./screenshot.md)

# screenshot

`fun screenshot(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)

Takes a screenshot with the specified tag.

### Parameters

`tag` - Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-](#)+.

**Return**
the image file that was created

`fun screenshot(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, testClassName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, testMethodName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](https://developer.android.com/reference/java/io/File.html)

Takes a screenshot with the specified tag.  This version allows the caller to manually specify
the test class name and method name.  This is necessary when the screenshot is not called in
the traditional manner.
.

### Parameters

`tag` - Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-](#)+.

**Return**
the image file that was created

