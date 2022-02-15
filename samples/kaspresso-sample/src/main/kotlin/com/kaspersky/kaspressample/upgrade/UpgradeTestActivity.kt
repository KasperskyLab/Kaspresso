package com.kaspersky.kaspressample.upgrade

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.databinding.ActivityUpgradeTestBinding

class UpgradeTestActivity : AppCompatActivity() {

    companion object {
        private const val KEY_VALUE = "upgrade_value"
        private const val DEFAULT_VALUE = "Undefined"
    }

    private val appVersion: String
        get() = packageManager.getPackageInfo(packageName, 0).versionName

    private lateinit var binding: ActivityUpgradeTestBinding
    private lateinit var prefs: SharedPreferences

    private var currentValue: String?
        get() = prefs.getString(KEY_VALUE, DEFAULT_VALUE)
        set(value) = prefs.edit { putString(KEY_VALUE, value) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpgradeTestBinding.inflate(layoutInflater)

        prefs = getPreferences(Context.MODE_PRIVATE)

        binding.upgradeVersion.text = getString(R.string.upgrade_version_placeholder, appVersion)
        binding.upgradeValueCurrent.text = getString(R.string.upgrade_value_placeholder, currentValue)
        binding.upgradeApplyBtn.setOnClickListener { updateValue() }

        setContentView(binding.root)
    }

    private fun updateValue() {
        val text = binding.upgradeValueInput.text.toString()

        if (text.isNotBlank()) {
            currentValue = text
            binding.upgradeValueCurrent.text = getString(R.string.upgrade_value_placeholder, text)
        }
    }
}
