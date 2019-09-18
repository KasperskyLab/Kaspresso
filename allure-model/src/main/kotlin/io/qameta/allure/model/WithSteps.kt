package io.qameta.allure.model

interface WithSteps {
    var steps: MutableList<StepResult>
}