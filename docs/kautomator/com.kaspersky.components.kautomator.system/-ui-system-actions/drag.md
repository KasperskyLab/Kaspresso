[kautomator](../../index.md) / [com.kaspersky.components.kautomator.system](../index.md) / [UiSystemActions](index.md) / [drag](./drag.md)

# drag

`open fun drag(startX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, endY: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, steps: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Performs a swipe from one coordinate to another coordinate. You can control
the smoothness and speed of the swipe by specifying the number of steps.
Each step execution is throttled to 5 milliseconds per step, so for a 100
steps, the swipe will take around 0.5 seconds to complete.

### Parameters

`startX` - X-axis value for the starting coordinate

`startY` - Y-axis value for the starting coordinate

`endX` - X-axis value for the ending coordinate

`endY` - Y-axis value for the ending coordinate

`steps` - is the number of steps for the swipe action