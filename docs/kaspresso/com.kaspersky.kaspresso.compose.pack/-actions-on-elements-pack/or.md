[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose.pack](../index.md) / [ActionsOnElementsPack](index.md) / [or](./or.md)

# or

`fun <Type> or(element: `[`Type`](or.md#Type)`, action: `[`Type`](or.md#Type)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`ComplexComposeBranchBuilder`](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)`<`[`Type`](or.md#Type)`> where Type : BaseActions, Type : BaseAssertions, Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction>`

Adds the [element](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Unit)))/element) of type [Type](or.md#Type) and the [action](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Unit)))/action) to [complexComposeBranchBuilders](#) and [action](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Unit)))/action) for future composing
where [Type](or.md#Type) is bounding by KBaseView (Kakao)

### Parameters

`element` - the interacted view.

`action` - actions or assertions on the interacted view.`fun <Type> or(element: `[`Type`](or.md#Type)`, action: `[`Type`](or.md#Type)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`ComplexComposeBranchBuilder`](../../com.kaspersky.kaspresso.compose.pack.branch/-complex-compose-branch-builder/index.md)`<`[`Type`](or.md#Type)`> where Type : UiBaseActions, Type : UiBaseAssertions, Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`

Adds the [element](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Unit)))/element) of type [Type](or.md#Type) and the [action](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Unit)))/action) to [complexComposeBranchBuilders](#) and [action](or.md#com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack$or(com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Function1((com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack.or.Type, kotlin.Unit)))/action) for future composing
where [Type](or.md#Type) is bounding by UiBaseView (Kautomator)

### Parameters

`element` - the interacted view.

`action` - actions or assertions on the interacted view.