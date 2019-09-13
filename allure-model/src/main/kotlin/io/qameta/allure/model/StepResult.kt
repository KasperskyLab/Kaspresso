package io.qameta.allure.model

import java.util.*


class StepResult(
    @Transient val uuid: String = UUID.randomUUID().toString()
) : ExecutableItem()