package io.qameta.allure.model

interface WithStatusDetails : WithStatus {
    var statusDetails: StatusDetails?
}
