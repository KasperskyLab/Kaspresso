[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose.pack](../index.md) / [ActionsPack](./index.md)

# ActionsPack

`class ActionsPack<T>`

The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-compose-provider/compose.md) and
[com.kaspersky.kaspresso.compose.WebComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-web-compose-provider/compose.md)] methods.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ActionsPack(element: `[`T`](index.md#T)`)`<br>The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-compose-provider/compose.md) and [com.kaspersky.kaspresso.compose.WebComposeProvider.compose](../../com.kaspersky.kaspresso.compose/-web-compose-provider/compose.md)] methods. |

### Functions

| Name | Summary |
|---|---|
| [or](or.md) | `fun or(action: `[`T`](index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Builds the lambda to add to [actions](#) that invokes the given [action](or.md#com.kaspersky.kaspresso.compose.pack.ActionsPack$or(kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsPack.T, kotlin.Unit)))/action) on the interacted view of type [T](index.md#T). |
