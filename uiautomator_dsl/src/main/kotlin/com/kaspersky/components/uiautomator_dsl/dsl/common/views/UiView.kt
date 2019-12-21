package com.kaspersky.components.uiautomator_dsl.dsl.common.views

import com.kaspersky.components.uiautomator_dsl.dsl.common.builders.UiViewBuilder

class UiView : UiBaseView<UiView> {
    constructor(func: UiViewBuilder.() -> Unit) : super(func)
}