package com.kaspersky.uitest_framework

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerInterface {

    /**
     * Execute RAW cmd command on server
     * @param command
     */
    @GET("cmd")
    abstract fun cmdRequest(@Query("cmd") command: String): Call<Void>

    /**
     * Execute things, that will be after ADB. On server will be executed: adb + command
     * @param command
     */
    @GET("adb")
    abstract fun adbRequest(@Query("adb") command: String): Call<Void>

}
