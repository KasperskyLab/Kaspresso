[kautomator](../../index.md) / [com.kaspersky.components.kautomator.component.common.builders](../index.md) / [UiViewBuilder](index.md) / [withDescendant](./with-descendant.md)

# withDescendant

`fun withDescendant(function: `[`UiViewBuilder`](index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Matches the view which has descendant of given matcher

### Parameters

`function` - ViewBuilder which will result in descendant matcher`fun withDescendant(maxDepth: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, function: `[`UiViewBuilder`](index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Matches the view which has descendant of given matcher with the maximum depth under the
element to search the descendant

### Parameters

`function` - ViewBuilder which will result in descendant matcher

`maxDepth` - The maximum depth under the element to search the descendant