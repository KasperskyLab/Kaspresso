[kaspresso](../../index.md) / [com.kaspersky.kaspresso.kviews](../index.md) / [KScreen](./index.md)

# KScreen

`abstract class KScreen<out T : `[`KScreen`](./index.md)`<`[`T`](index.md#T)`>> : ScreenActions`

An extension of [com.agoda.kakao.screen.Screen](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `KScreen()`<br>An extension of [com.agoda.kakao.screen.Screen](#). |

### Properties

| Name | Summary |
|---|---|
| [layoutId](layout-id.md) | `abstract val layoutId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?`<br>The layout id with which the screen is associated. |
| [view](view.md) | `open val view: ViewInteractionDelegate` |
| [viewClass](view-class.md) | `abstract val viewClass: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>?`<br>The view class with which the screen is associated. |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun invoke(function: `[`T`](index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [idle](idle.md) | `fun idle(duration: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 1000L): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Idles for given amount of time |
| [onScreen](on-screen.md) | `fun <T : `[`KScreen`](./index.md)`<`[`T`](on-screen.md#T)`>> onScreen(function: `[`T`](on-screen.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](on-screen.md#T) |
