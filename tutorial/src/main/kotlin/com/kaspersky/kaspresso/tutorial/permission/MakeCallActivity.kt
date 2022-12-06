package com.kaspersky.kaspresso.tutorial.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaspersky.kaspresso.tutorial.databinding.ActivityMakeCallBinding

class MakeCallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakeCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.makeCallBtn.setOnClickListener {
            if (!isPermissionGranted()) {
                requestPermission()
            } else {
                phoneCall()
            }
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(CALL_PHONE_PERMISSION),
                CALL_PHONE_PERMISSION_RC
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (
            requestCode == CALL_PHONE_PERMISSION_RC &&
            permissions[0] == android.Manifest.permission.CALL_PHONE &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            phoneCall()
        }
    }

    private fun phoneCall() {
        val number = binding.inputNumber.text.toString().trim()
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
    }

    private fun isPermissionGranted(): Boolean = packageManager.checkPermission(
        android.Manifest.permission.CALL_PHONE,
        packageName
    ) == PackageManager.PERMISSION_GRANTED

    companion object {

        private const val CALL_PHONE_PERMISSION = android.Manifest.permission.CALL_PHONE
        private const val CALL_PHONE_PERMISSION_RC = 100
    }
}
