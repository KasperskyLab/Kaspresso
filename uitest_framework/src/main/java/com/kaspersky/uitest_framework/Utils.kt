package com.kaspersky.uitest_framework

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.support.test.InstrumentationRegistry

@SuppressLint("WifiManagerLeak")
fun setWiFiState(enable: Boolean) {
    val wifiManager = InstrumentationRegistry.getTargetContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
    wifiManager.isWifiEnabled = enable
}

fun setWiFiAndMobileState(b: Boolean) {
    setWiFiState(b)
    ServerClass.makeAdbRequest("shell svc data ${ if (b) {
        Thread.sleep(5000)
        "enable"
    } else "disable" }")
}

fun silentlyRunAdbCommand(array: List<String>) {
    setWiFiState(enable = true)
    var count = 50
    while (count != 0) {
        Thread.sleep(200)
        count -= 1
    }
    for (str in array) {
        ServerClass.makeAdbRequest(str)
    }
    setWiFiState(enable = false)
}
