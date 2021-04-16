package com.kaspersky.kaspressample.upgrade

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspressample.BuildConfig
import com.kaspersky.kaspressample.R
import kotlinx.android.synthetic.main.activity_upgrade_test.*

class UpgradeTestActivity : AppCompatActivity() {

    companion object {
        private const val KEY_VALUE = "upgrade_value"
        private const val DEFAULT_VALUE = "Undefined"
    }

    private lateinit var prefs: SharedPreferences

    private var currentValue: String?
        get() = prefs.getString(KEY_VALUE, DEFAULT_VALUE)
        set(value) = prefs.edit().putString(KEY_VALUE, value).apply()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_test)

        prefs = getPreferences(Context.MODE_PRIVATE)

        // TODO: Add version
        upgrade_version.text =
            getString(R.string.upgrade_version_placeholder, BuildConfig.APPLICATION_ID)

        upgrade_value_current.text = getString(R.string.upgrade_value_placeholder, currentValue)
        upgrade_apply_btn.setOnClickListener { updateValue() }
    }

    private fun updateValue() {
        val text = upgrade_value_input.text.toString()

        if (text.isNotBlank()) {
            currentValue = text
            upgrade_value_current.text = getString(R.string.upgrade_value_placeholder, text)
        }
    }
}
