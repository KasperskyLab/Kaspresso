[kaspresso](../../index.md) / [com.kaspersky.kaspresso.compose.pack](../index.md) / [ActionsOnWebElementsPack](index.md) / [orWithElement](./or-with-element.md)

# orWithElement

`fun orWithElement(locator: Locator, value: String, action: KWebInteraction.() -> Unit): Unit`

Builds the lambda to add to [actions](#) that invokes the given [action](or-with-element.md#com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack$orWithElement(androidx.test.espresso.web.webdriver.Locator, kotlin.String, kotlin.Function1((com.agoda.kakao.web.WebElementBuilder.KWebInteraction, kotlin.Unit)))/action) on the web element built by
[webElementBuilder](#) with given [locator](or-with-element.md#com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack$orWithElement(androidx.test.espresso.web.webdriver.Locator, kotlin.String, kotlin.Function1((com.agoda.kakao.web.WebElementBuilder.KWebInteraction, kotlin.Unit)))/locator) and [value](or-with-element.md#com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack$orWithElement(androidx.test.espresso.web.webdriver.Locator, kotlin.String, kotlin.Function1((com.agoda.kakao.web.WebElementBuilder.KWebInteraction, kotlin.Unit)))/value).

### Parameters

`locator` - the locator type of web view element.

`value` - the value to be searched for in web view.

`action` - actions or assertions on the interacted view.