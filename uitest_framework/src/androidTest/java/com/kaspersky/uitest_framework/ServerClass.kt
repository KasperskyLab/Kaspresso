package com.kaspersky.uitest_framework

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.IOException

class ServerClass {

    companion object {
        var sTestSingleton = ServerClass()
    }

    private lateinit var testServer: ServerInterface

    private fun ServerClass() {
        val httpClient = OkHttpClient.Builder()
        // add your other interceptors …

        // add logging as last interceptor

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .client(httpClient.build())
            .build()

        testServer = retrofit.create<ServerInterface>(ServerInterface::class.java)
    }

    fun makeCmdRequest(command: String) {
        try {
            testServer.cmdRequest(command).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun makeAdbRequest(command: String) {
        try {
            testServer.adbRequest(command).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}