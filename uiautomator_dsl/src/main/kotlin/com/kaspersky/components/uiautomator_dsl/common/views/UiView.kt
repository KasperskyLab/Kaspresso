package com.kaspersky.components.uiautomator_dsl.common.views

import com.kaspersky.components.uiautomator_dsl.common.builders.UiViewBuilder

class UiView : UiBaseView<UiView> {
    constructor(func: UiViewBuilder.() -> Unit) : super(func)
}