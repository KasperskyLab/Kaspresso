package com.kaspersky.kaspresso.publication

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.Authenticator
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.IOException
import java.util.concurrent.TimeUnit

abstract class CreateStagingRepositoryTask : DefaultTask() {

    @get:Input
    abstract val stagingProfileId: Property<String>

    @get:Input
    abstract val user: Property<String>

    @get:Input
    abstract val password: Property<String>

    @get:Input
    abstract val repositoryDescription: Property<String>

    @Suppress("UnstableApiUsage")
    @get:OutputFile
    val repositoryIdFile: Property<RegularFile> = project.objects.fileProperty()

    @TaskAction
    fun doWork() {
        val authenticator = Authenticator { _, response ->
            response.request
                .newBuilder()
                .addHeader("Authorization", Credentials.basic(user.get(), password.get()))
                .build()
        }

        val okHttpClient = OkHttpClient.Builder()
            .authenticator(authenticator)
            .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()

        val url = "$SONATYPE_BASE_URL/staging/profiles/${stagingProfileId.get()}/start"

        val gson = Gson()

        val request = Request.Builder()
            .url(url)
            .post(
                """{ "data": { "description": "${repositoryDescription.get()}" } }"""
                    .toRequestBody(contentType = "application/json".toMediaType())
            )
            .build()

        val response = try {
            okHttpClient.newCall(request).execute()
        } catch (e: IOException) {
            failTask("network error", e)
        }

        val body = response.body?.use { it.string() }

        if (response.isSuccessful && !body.isNullOrBlank()) {
            try {
                val responseBody = gson.fromJson<ResponseBody>(body)
                val createdRepoId = responseBody.data.stagedRepositoryId
                logger.info("Repository $createdRepoId created")
                val repositoryUrl = "$SONATYPE_BASE_URL/staging/deployByRepositoryId/$createdRepoId"
                repositoryIdFile.get().asFile.writeText(repositoryUrl)
            } catch (e: JsonParseException) {
                failTask("can't parse sonatype response", e)
            }
        } else {
            failTask(
                "network error (responseCode=${response.code}; " +
                        "responseBody=${body?.take(RESPONSE_BODY_STRING_LIMIT)})"
            )
        }
    }

    private fun failTask(reason: String, cause: Throwable? = null): Nothing {
        throw IllegalStateException("Can't create staging repository; $reason", cause)
    }

    private data class ResponseData(val stagedRepositoryId: String)
    private data class ResponseBody(val data: ResponseData)

    private companion object {
        const val RESPONSE_BODY_STRING_LIMIT = 128
        const val TIMEOUT_SEC = 300L
        const val SONATYPE_BASE_URL = "https://oss.sonatype.org/service/local"
    }
}
