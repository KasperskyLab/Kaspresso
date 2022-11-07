package com.kaspersky.kaspresso.tutorial.wifi

import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.databinding.ActivityWifiBinding

class WiFiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWifiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkWifiBtn.setOnClickListener {
            if (isWiFiEnabled(application)) {
                binding.wifiStatus.setText(R.string.enabled_status)
            } else {
                binding.wifiStatus.setText(R.string.disabled_status)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_WIFI_STATUS_KEY, binding.wifiStatus.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString(EXTRA_WIFI_STATUS_KEY)?.let {
            binding.wifiStatus.text = it
        }
    }

    private fun isWiFiEnabled(context: Application): Boolean =
        (context.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.isWifiEnabled
            ?: throw IllegalStateException("WifiManager is unavailable")

    companion object {
        private const val EXTRA_WIFI_STATUS_KEY = "EXTRA_WIFI_STATUS_KEY"
    }
}
