package io.qameta.allure.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

class TestResultContainer(
    @SerializedName("uuid")
    val uuid: String = UUID.randomUUID().toString(),
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("start")
    var start: Long? = null,
    @SerializedName("stop")
    var stop: Long? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("descriptionHtml")
    var descriptionHtml: String? = null,
    @SerializedName("children")
    val children: MutableList<String> = ArrayList(),
    @SerializedName("befores")
    val befores: MutableList<FixtureResult> = ArrayList(),
    @SerializedName("afters")
    val afters: MutableList<FixtureResult> = ArrayList(),
    @SerializedName("links")
    override val links: MutableList<Link> = ArrayList()
) : WithLinks