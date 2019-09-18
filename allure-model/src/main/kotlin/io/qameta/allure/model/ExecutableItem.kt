package io.qameta.allure.model

import com.google.gson.annotations.SerializedName

abstract class ExecutableItem(
    @SerializedName("name")
    open var name: String? = null,
    @SerializedName("start")
    open var start: Long? = null,
    @SerializedName("stop")
    open var stop: Long? = null,
    @SerializedName("description")
    open var description: String? = null,
    @SerializedName("descriptionHtml")
    open var descriptionHtml: String? = null,
    @SerializedName("stage")
    open var stage: Stage? = null,
    @SerializedName("status")
    override var status: Status? = null,
    @SerializedName("statusDetails")
    override var statusDetails: StatusDetails? = null,
    @SerializedName("steps")
    override var steps: MutableList<StepResult> = mutableListOf(),
    @SerializedName("attachments")
    override var attachments: MutableList<Attachment> = mutableListOf(),
    @SerializedName("parameters")
    override var parameters: MutableList<Parameter> = mutableListOf()
) : WithSteps, WithAttachments, WithParameters,
    WithStatusDetails